package org.globant.services.orderservice;

import org.globant.enums.Cryptos;
import org.globant.model.order.SalesOrder;

public interface SaleOrderPort {
    SalesOrder addSalesOrder(Cryptos crypto, String amountCrypto, String maximumPrice);

    boolean searchBuyOrder(SalesOrder salesOrder);
}
