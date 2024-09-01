package org.globant.services.orderservice;

import org.globant.enums.Cryptos;
import org.globant.model.order.BuyOrder;
import org.globant.model.order.SalesOrder;
import org.globant.model.user.User;
import org.globant.repository.LoginUserRepository;
import org.globant.repository.UserMemoryRepository;
import org.globant.repository.order.BuyOrderRepository;
import org.globant.repository.order.SalesOrderRepository;

import java.util.List;

public class BuyOrderServiceImpl implements BuyOrderPort, SaleOrderPort{

    private final BuyOrderRepository buyOrderRepository;
    private final SalesOrderRepository salesOrderRepository;
    private final LoginUserRepository loginUserRepository;
    private final UserMemoryRepository userMemoryRepository;

    public BuyOrderServiceImpl(SalesOrderRepository salesOrderRepository, BuyOrderRepository buyOrderRepository, LoginUserRepository loginUserRepository, UserMemoryRepository userMemoryRepository) {
        this.salesOrderRepository = salesOrderRepository;
        this.buyOrderRepository = buyOrderRepository;
        this.loginUserRepository = loginUserRepository;
        this.userMemoryRepository = userMemoryRepository;
    }

    @Override
    public BuyOrder addBuyOrder(Cryptos crypto, String amountCrypto, String maximumPrice) {
        User user = loginUserRepository.getUserLogin();
        int userId = loginUserRepository.getUserId();
        BuyOrder buyOrder = new BuyOrder(crypto,amountCrypto,maximumPrice,user,userId);
        buyOrderRepository.addBuyOrder(buyOrder);
        return buyOrder;
    }


    @Override
    public SalesOrder addSalesOrder(Cryptos crypto, String amountCrypto, String maximumPrice) {
        User user = loginUserRepository.getUserLogin();
        int userId = loginUserRepository.getUserId();
        SalesOrder salesOrder = new SalesOrder(crypto,amountCrypto,maximumPrice,user,userId);
        salesOrderRepository.addSalesOrder(salesOrder);
        return salesOrder;
    }

    @Override
    public boolean searchBuyOrder(SalesOrder salesOrder) {
        List<BuyOrder> buys = buyOrderRepository.getBuyOrders();
        for (BuyOrder flag : buys) {
            if(flag.getCryptoAmount().equals(salesOrder.getCryptoAmount()) && flag.getCrypto().equals(salesOrder.getCrypto())){
                if(flag.getMaximumPrice().compareTo(salesOrder.getMaximumPrice()) >= 0){
                    switch (salesOrder.getCrypto()){
                        case BITCOIN -> {
                            userMemoryRepository.getUsers().get(flag.getUserId()).getUserWallet().getBitcoinCrypto().addAmountCrypto(salesOrder.getCryptoAmount());
                            userMemoryRepository.getUsers().get(salesOrder.getUserId()).getUserWallet().getBitcoinCrypto().removeAmountCrypto(salesOrder.getCryptoAmount());
                        }
                        case ETHEREUM -> {
                            userMemoryRepository.getUsers().get(flag.getUserId()).getUserWallet().getEthereumCrypto().addAmountCrypto(salesOrder.getCryptoAmount());
                            userMemoryRepository.getUsers().get(salesOrder.getUserId()).getUserWallet().getEthereumCrypto().removeAmountCrypto(salesOrder.getCryptoAmount());
                        }
                        case UNISWAP -> {
                            userMemoryRepository.getUsers().get(flag.getUserId()).getUserWallet().getUnisWapCrypto().addAmountCrypto(salesOrder.getCryptoAmount());
                            userMemoryRepository.getUsers().get(salesOrder.getUserId()).getUserWallet().getUnisWapCrypto().removeAmountCrypto(salesOrder.getCryptoAmount());
                        }
                    }
                    userMemoryRepository.getUsers().get(salesOrder.getUserId()).getUserWallet().addFiat(flag.getMaximumPrice());
                    userMemoryRepository.getUsers().get(flag.getUserId()).getUserWallet().removeFiat(flag.getMaximumPrice());

                    salesOrderRepository.getSalesOrders().remove(salesOrder);
                    buyOrderRepository.getBuyOrders().remove(flag);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean searchSaleOrder(BuyOrder buyOrder) {
        List<SalesOrder> sales = salesOrderRepository.getSalesOrders();
        for (SalesOrder flag : sales) {
            if (flag.getCryptoAmount().equals(buyOrder.getCryptoAmount()) && flag.getCrypto().equals(buyOrder.getCrypto())) {
                if(flag.getMaximumPrice().compareTo(buyOrder.getMaximumPrice()) <= 0){
                    switch (buyOrder.getCrypto()){
                        case BITCOIN -> {
                            userMemoryRepository.getUsers().get(buyOrder.getUserId()).getUserWallet().getBitcoinCrypto().addAmountCrypto(flag.getCryptoAmount());
                            userMemoryRepository.getUsers().get(flag.getUserId()).getUserWallet().getBitcoinCrypto().removeAmountCrypto(flag.getCryptoAmount());
                        }
                        case ETHEREUM -> {
                            userMemoryRepository.getUsers().get(buyOrder.getUserId()).getUserWallet().getEthereumCrypto().addAmountCrypto(flag.getCryptoAmount());
                            userMemoryRepository.getUsers().get(flag.getUserId()).getUserWallet().getEthereumCrypto().removeAmountCrypto(flag.getCryptoAmount());
                        }
                        case UNISWAP -> {
                            userMemoryRepository.getUsers().get(buyOrder.getUserId()).getUserWallet().getUnisWapCrypto().addAmountCrypto(flag.getCryptoAmount());
                            userMemoryRepository.getUsers().get(flag.getUserId()).getUserWallet().getUnisWapCrypto().removeAmountCrypto(flag.getCryptoAmount());
                        }
                    }

                    userMemoryRepository.getUsers().get(buyOrder.getUserId()).getUserWallet().removeFiat(flag.getMaximumPrice());
                    userMemoryRepository.getUsers().get(flag.getUserId()).getUserWallet().addFiat(flag.getMaximumPrice());

                    salesOrderRepository.getSalesOrders().remove(flag);
                    buyOrderRepository.getBuyOrders().remove(buyOrder);
                    return true;
                }
            }
        }
        return false;
    }

}
