package org.globant.model.order;

import java.math.BigDecimal;

public class SellOrder extends Order{

    public SellOrder(String cryptoName, BigDecimal cryptoMount, BigDecimal maximumPrice, int userId) {
        super(cryptoName, cryptoMount, maximumPrice, userId);
    }
}

