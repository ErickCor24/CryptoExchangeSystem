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

        cryptoAmount = cryptoAmount.replace(",", ".");
        maximumPrice = maximumPrice.replace(",", ".");
        BigDecimal price = new BigDecimal(maximumPrice);
        BigDecimal amount = new BigDecimal(cryptoAmount);
        if (loginUserRepository.getUserLogin().getUserWallet().getFiat().compareTo(price) >= 0) {
            var buy = buyOrder.addBuyOrder(cryptos, cryptoAmount, maximumPrice);
            if (buyOrder.searchSaleOrder(buy)) {
                historyController.addTransactionToUser(cryptos,amount, Transaction.BUY_ORDER);
                return "The system found a order sale for you order";
            } else {
                historyController.addTransactionToUser(cryptos,amount, Transaction.BUY_ORDER);
                return "The system is waiting for a order sale for you order";
            }
        } else {
            return "Yor account not have the fiat assigned";
        }
    }

    public String makeSalesOrder(Cryptos cryptos, String cryptoAmount, String maximumPrice) {
        cryptoAmount = cryptoAmount.replace(",", ".");
        maximumPrice = maximumPrice.replace(",", ".");
        BigDecimal price = new BigDecimal(cryptoAmount);
        BigDecimal amount = new BigDecimal(cryptoAmount);
        boolean flag = false;
        switch (cryptos) {
            case BITCOIN -> flag = loginUserRepository.getUserLogin().getUserWallet().getBitCoin().compareTo(price) >= 0;
            case ETHEREUM ->flag = loginUserRepository.getUserLogin().getUserWallet().getEthereum().compareTo(price) >= 0;
            case UNISWAP -> flag = loginUserRepository.getUserLogin().getUserWallet().getUnisWap().compareTo(price) >= 0;
        }
        if (flag) {
            var sale = saleOrder.addSalesOrder(cryptos, cryptoAmount, maximumPrice);
            if (saleOrder.searchBuyOrder(sale)) {
                historyController.addTransactionToUser(cryptos, amount, Transaction.BUY_ORDER);
                return "The system found a order buy for you order";
            } else {
                historyController.addTransactionToUser(cryptos, amount, Transaction.BUY_ORDER);
                return "The system is waiting for a order buy for you order";
            }
        } else
            return "Yor account not have the crypto mount assigned";
    }

    public void ordersScreen (){
        System.out.println("BUYS");
        System.out.println(buyOrderRepository.getBuyOrders());

        System.out.println("SALES");
        System.out.println(salesOrderRepository.getSalesOrders());

    }

}
