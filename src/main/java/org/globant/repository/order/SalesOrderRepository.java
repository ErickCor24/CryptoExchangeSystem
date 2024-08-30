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

    public static SalesOrderRepository getInstance(){
        if (instance == null){
            instance = new SalesOrderRepository();
        }
        return instance;
    }

    public List<SalesOrder> getSalesOrders(){
        return this.salesOrders;
    }

    public void addSalesOrder(SalesOrder salesOrder) {
        salesOrders.add(salesOrder);
    }
}
