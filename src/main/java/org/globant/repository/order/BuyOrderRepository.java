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

    /**
     * Get instance for BuyOrderRepository (Singleton)
     * @return instance
     */
    public static BuyOrderRepository getInstance() {
        if (instance == null) {
            instance = new BuyOrderRepository();
        }
        return instance;
    }

    /**
     * Obtain all buy orders
     * @return list
     */
    public List<BuyOrder> getBuyOrders() {
        return buyOrders;
    }

    /**
     * Add buy order in the instance
     * @param buyOrder buy order
     */
    public void addBuyOrder (BuyOrder buyOrder){
        this.buyOrders.add(buyOrder);
    }
}
