package org.globant.model.order;

import org.globant.model.user.User;

import java.math.BigDecimal;

public abstract class Order {

    private String cryptoName;
    private BigDecimal cryptoMount;
    private BigDecimal maximumPrice;
    private int userId;

    public Order(String cryptoName, BigDecimal cryptoMount, BigDecimal maximumPrice, int userId) {
        this.cryptoName = cryptoName;
        this.cryptoMount = new BigDecimal(0);
        this.maximumPrice = new BigDecimal(0);
        this.userId = userId;
    }

}
