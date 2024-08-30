package org.globant.repository.order;

import org.globant.model.order.BuyOrder;

import java.util.ArrayList;
import java.util.List;

public class BuyOrderRepository {
    private static BuyOrderRepository instance;
    private final List<BuyOrder> buyOrders;

    private BuyOrderRepository(){
        this.buyOrders = new ArrayList<>();
    }

    public static BuyOrderRepository getInstance() {
        if (instance == null) {
            instance = new BuyOrderRepository();
        }
        return instance;
    }

    public List<BuyOrder> getBuyOrders() {
        return buyOrders;
    }

    public void addBuyOrder (BuyOrder buyOrder){
        this.buyOrders.add(buyOrder);
    }
}
