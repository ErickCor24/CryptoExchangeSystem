package org.globant.repository.order;

import org.globant.model.order.SalesOrder;

import java.util.ArrayList;
import java.util.List;

public class SalesOrderRepository {

    private static SalesOrderRepository instance;
    private final List<SalesOrder> salesOrders;

    private SalesOrderRepository() {
        this.salesOrders = new ArrayList<>();
    }

    /**
     * Get instance for SalesOrderRepository (Singleton)
     * @return instance
     */
    public static SalesOrderRepository getInstance(){
        if (instance == null){
            instance = new SalesOrderRepository();
        }
        return instance;
    }

    /**
     * Get all sell orders
     * @return list
     */
    public List<SalesOrder> getSalesOrders(){
        return this.salesOrders;
    }

    /**
     * Add sell order in the instance
     * @param salesOrder sell order
     */
    public void addSalesOrder(SalesOrder salesOrder) {
        salesOrders.add(salesOrder);
    }
}
