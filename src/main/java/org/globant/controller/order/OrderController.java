package org.globant.controller.order;

import org.globant.controller.history.HistoryController;
import org.globant.enums.Cryptos;
import org.globant.enums.Transaction;
import org.globant.model.order.BuyOrder;
import org.globant.model.order.SalesOrder;
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

    private final BuyOrderPort buyOrder = new BuyOrderServiceImpl(salesOrderRepository, buyOrderRepository, loginUserRepository, userMemoryRepository);
    private final SaleOrderPort saleOrder = new BuyOrderServiceImpl(salesOrderRepository, buyOrderRepository, loginUserRepository, userMemoryRepository);

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
        } catch (NumberFormatException e) {
            return "Do not enter empty fields";
        }

        if (controlFiatBuyOrder(price)) {
            if (price.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(BigDecimal.ZERO) > 0) {
                if (loginUserRepository.getUserLogin().getUserWallet().getFiat().compareTo(price) >= 0) {
                    var buy = buyOrder.addBuyOrder(cryptos, cryptoAmount, maximumPrice);
                    if (buyOrder.searchSaleOrder(buy)) {
                        historyController.addTransactionToUser(cryptos, price, amount, Transaction.BUY_ORDER);
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
        } else
            return "Yor account not have the fiat assigned, check your fiat";
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

        if (controlCryptoBuyOrder(cryptos, amount)) {
            boolean flag = isFlag(cryptos, amount);
            if (price.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(BigDecimal.ZERO) > 0) {
                if (flag) {
                    var sale = saleOrder.addSalesOrder(cryptos, cryptoAmount, maximumPrice);
                    if (saleOrder.searchBuyOrder(sale)) {
                        historyController.addTransactionToUser(cryptos, price, amount, Transaction.SELL_ORDER);
                        return "The system found a order buy for you order";
                    } else {
                        historyController.addTransactionToUser(cryptos, price, amount, Transaction.SELL_ORDER);
                        return "The system is waiting for a order buy for you order";
                    }
                } else
                    return "Yor account not have the crypto mount assigned";
            } else
                return "Enter a amount greater than 0";
        } else
            return "Yor account not have the crypto mount assigned, check your crypto amount";
    }

    private boolean isFlag(Cryptos cryptos, BigDecimal amount) {
        boolean flag = false;
        switch (cryptos) {
            case BITCOIN ->
                    flag = loginUserRepository.getUserLogin().getUserWallet().getBitcoinCrypto().getAmountCrypto().compareTo(amount) >= 0;
            case ETHEREUM ->
                    flag = loginUserRepository.getUserLogin().getUserWallet().getEthereumCrypto().getAmountCrypto().compareTo(amount) >= 0;
            case UNISWAP ->
                    flag = loginUserRepository.getUserLogin().getUserWallet().getUnisWapCrypto().getAmountCrypto().compareTo(amount) >= 0;
        }
        return flag;
    }

    private boolean controlFiatBuyOrder(BigDecimal maximumPrice) {
        BigDecimal fiatOrderMount = BigDecimal.ZERO;
        BigDecimal userFiat = loginUserRepository.getUserLogin().getUserWallet().getFiat();
        for (BuyOrder flag : buyOrderRepository.getBuyOrders()) {
            if (flag.getUserId() == loginUserRepository.getUserId()) {
                fiatOrderMount = fiatOrderMount.add(flag.getMaximumPrice());
            }
        }
        fiatOrderMount = fiatOrderMount.add(maximumPrice);
        return !(fiatOrderMount.compareTo(userFiat) > 0);
    }

    private boolean controlCryptoBuyOrder(Cryptos cryptos, BigDecimal cryptoAmount) {
        BigDecimal cryptoSaleOrder = BigDecimal.ZERO;
        BigDecimal userCryptoAmount = getCryptoAmount(cryptos);

        for (SalesOrder flag : salesOrderRepository.getSalesOrders()) {
            if (loginUserRepository.getUserId() == flag.getUserId()) {
                if (cryptos == flag.getCrypto()) {
                    cryptoSaleOrder = cryptoSaleOrder.add(flag.getCryptoAmount());
                }
            }
        }

        cryptoSaleOrder = cryptoSaleOrder.add(cryptoAmount);
        return userCryptoAmount.compareTo(cryptoSaleOrder) >= 0;
    }

    private BigDecimal getCryptoAmount(Cryptos cryptos) {
        BigDecimal userCryptoAmount = null;
        switch (cryptos) {
            case BITCOIN ->
                    userCryptoAmount = loginUserRepository.getUserLogin().getUserWallet().getBitcoinCrypto().getAmountCrypto();
            case ETHEREUM ->
                    userCryptoAmount = loginUserRepository.getUserLogin().getUserWallet().getEthereumCrypto().getAmountCrypto();
            case UNISWAP ->
                    userCryptoAmount = loginUserRepository.getUserLogin().getUserWallet().getUnisWapCrypto().getAmountCrypto();
        }
        return userCryptoAmount;
    }

    public void ordersScreen() {
        System.out.println("BUYS");
        System.out.println(buyOrderRepository.getBuyOrders());

        System.out.println("SALES");
        System.out.println(salesOrderRepository.getSalesOrders());

    }

}
