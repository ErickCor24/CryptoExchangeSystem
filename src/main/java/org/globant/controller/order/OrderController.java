package org.globant.controller.order;

import org.globant.controller.history.HistoryController;
import org.globant.enums.Cryptos;
import org.globant.enums.Transaction;
import org.globant.repository.LoginUserRepository;
import org.globant.repository.UserMemoryRepository;
import org.globant.repository.order.BuyOrderRepository;
import org.globant.repository.order.SalesOrderRepository;
import org.globant.services.orderservice.BuyOrderPort;
import org.globant.services.orderservice.BuyOrderServiceImpl;
import org.globant.services.orderservice.SaleOrderPort;

import java.math.BigDecimal;

public class OrderController {

    private final BuyOrderRepository buyOrderRepository = BuyOrderRepository.getInstance();
    private final LoginUserRepository loginUserRepository = LoginUserRepository.getInstance();
    private final SalesOrderRepository salesOrderRepository = SalesOrderRepository.getInstance();
    private final UserMemoryRepository userMemoryRepository = UserMemoryRepository.getInstance();
    private final HistoryController historyController;

    private final BuyOrderPort buyOrder = new BuyOrderServiceImpl(salesOrderRepository,buyOrderRepository,loginUserRepository, userMemoryRepository);
    private final SaleOrderPort saleOrder = new BuyOrderServiceImpl(salesOrderRepository,buyOrderRepository,loginUserRepository, userMemoryRepository);

    public OrderController(HistoryController historyController) {
        this.historyController = historyController;
    }

    public String makeBuyOrder(Cryptos cryptos, String cryptoAmount, String maximumPrice) {
        BigDecimal price;
        BigDecimal amount;
        try {
            cryptoAmount = cryptoAmount.replace(",", ".");
            maximumPrice = maximumPrice.replace(",", ".");
            price = new BigDecimal(maximumPrice);
            amount = new BigDecimal(cryptoAmount);
        } catch (NumberFormatException e){
            return "Do not enter empty fields";
        }
        if(price.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(BigDecimal.ZERO) > 0) {
            if (loginUserRepository.getUserLogin().getUserWallet().getFiat().compareTo(price) >= 0) {
                var buy = buyOrder.addBuyOrder(cryptos, cryptoAmount, maximumPrice);
                if (buyOrder.searchSaleOrder(buy)) {
                    historyController.addTransactionToUser(cryptos, price,amount, Transaction.BUY_ORDER);
                    return "The system found a order sale for you order";
                } else {
                    historyController.addTransactionToUser(cryptos, price, amount, Transaction.BUY_ORDER);
                    return "The system is waiting for a order sale for you order";
                }
            } else {
                return "Yor account not have the fiat assigned";
            }
        } else
            return "Enter a amount greater than 0";
    }

    public String makeSalesOrder(Cryptos cryptos, String cryptoAmount, String maximumPrice) {
        BigDecimal price;
        BigDecimal amount;
        try {
            cryptoAmount = cryptoAmount.replace(",", ".");
            maximumPrice = maximumPrice.replace(",", ".");
            price = new BigDecimal(maximumPrice);
            amount = new BigDecimal(cryptoAmount);
        } catch (NumberFormatException e) {
            return "Do not enter empty fields";
        }

        boolean flag = isFlag(cryptos, amount);
        if (flag) {
            var sale = saleOrder.addSalesOrder(cryptos, cryptoAmount, maximumPrice);
            if (saleOrder.searchBuyOrder(sale)) {
                historyController.addTransactionToUser(cryptos, price, amount ,Transaction.SELL_ORDER);
                return "The system found a order buy for you order";
            } else {
                historyController.addTransactionToUser(cryptos, price, amount ,Transaction.SELL_ORDER);
                return "The system is waiting for a order buy for you order";
            }
        } else
            return "Yor account not have the crypto mount assigned";
    }

    private boolean isFlag(Cryptos cryptos, BigDecimal amount) {
        boolean flag = false;
        switch (cryptos) {
            case BITCOIN -> flag = loginUserRepository.getUserLogin().getUserWallet().getBitcoinCrypto().getAmountCrypto().compareTo(amount) >= 0;
            case ETHEREUM ->flag = loginUserRepository.getUserLogin().getUserWallet().getEthereumCrypto().getAmountCrypto().compareTo(amount) >= 0;
            case UNISWAP -> flag = loginUserRepository.getUserLogin().getUserWallet().getUnisWapCrypto().getAmountCrypto().compareTo(amount) >= 0;
        }
        return flag;
    }

    public void ordersScreen() {
        System.out.println("BUYS");
        System.out.println(buyOrderRepository.getBuyOrders());

        System.out.println("SALES");
        System.out.println(salesOrderRepository.getSalesOrders());

    }

}
