package org.globant.model.order;

import java.math.BigDecimal;

public class SalesOrder extends Order{

    public SalesOrder(String cryptoName, BigDecimal cryptoMount, BigDecimal maximumPrice, int userId) {
        super(cryptoName, cryptoMount, maximumPrice, userId);
    }
}

