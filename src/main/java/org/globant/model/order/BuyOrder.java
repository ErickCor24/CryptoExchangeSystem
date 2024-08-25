package org.globant.model.order;

import java.math.BigDecimal;

public class BuyOrder extends Order {

    public BuyOrder(String cryptoName, BigDecimal cryptoMount, BigDecimal maximumPrice, int userId) {
        super(cryptoName, cryptoMount, maximumPrice, userId);
    }
}
