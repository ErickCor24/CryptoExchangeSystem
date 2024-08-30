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
            if(flag.getCryptoMount().equals(salesOrder.getCryptoMount()) && flag.getCrypto().equals(salesOrder.getCrypto())){
                if(flag.getMaximumPrice().compareTo(salesOrder.getMaximumPrice()) >= 0){
                    switch (salesOrder.getCrypto()){
                        case BITCOIN -> {
                            userMemoryRepository.getUsers().get(flag.getUserId()).getUserWallet().addBitCoin(salesOrder.getCryptoMount());
                            userMemoryRepository.getUsers().get(salesOrder.getUserId()).getUserWallet().removeBitcoin(salesOrder.getCryptoMount());
                        }
                        case ETHEREUM -> {
                            userMemoryRepository.getUsers().get(flag.getUserId()).getUserWallet().addEthereum(salesOrder.getCryptoMount());
                            userMemoryRepository.getUsers().get(salesOrder.getUserId()).getUserWallet().removeEthereum(salesOrder.getCryptoMount());
                        }
                        case UNISWAP -> {
                            userMemoryRepository.getUsers().get(flag.getUserId()).getUserWallet().addUnisWap(salesOrder.getCryptoMount());
                            userMemoryRepository.getUsers().get(salesOrder.getUserId()).getUserWallet().removeUnisWap(salesOrder.getCryptoMount());
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
            if (flag.getCryptoMount().equals(buyOrder.getCryptoMount()) && flag.getCrypto().equals(buyOrder.getCrypto())) {
                if(flag.getMaximumPrice().compareTo(buyOrder.getMaximumPrice()) <= 0){
                    switch (buyOrder.getCrypto()){
                        case BITCOIN -> {
                            userMemoryRepository.getUsers().get(buyOrder.getUserId()).getUserWallet().addBitCoin(flag.getCryptoMount());
                            userMemoryRepository.getUsers().get(flag.getUserId()).getUserWallet().removeBitcoin(flag.getCryptoMount());
                        }
                        case ETHEREUM -> {
                            userMemoryRepository.getUsers().get(buyOrder.getUserId()).getUserWallet().addEthereum(flag.getCryptoMount());
                            userMemoryRepository.getUsers().get(flag.getUserId()).getUserWallet().removeEthereum(flag.getCryptoMount());
                        }
                        case UNISWAP -> {
                            userMemoryRepository.getUsers().get(buyOrder.getUserId()).getUserWallet().addUnisWap(flag.getCryptoMount());
                            userMemoryRepository.getUsers().get(flag.getUserId()).getUserWallet().removeUnisWap(flag.getCryptoMount());
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
