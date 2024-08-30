package org.globant.services.orderservice;

import org.globant.enums.Cryptos;
import org.globant.model.order.BuyOrder;

public interface BuyOrderPort {
    BuyOrder addBuyOrder(Cryptos crypto, String amountCrypto, String maximumPrice);

    boolean searchSaleOrder(BuyOrder buyOrder);
}
