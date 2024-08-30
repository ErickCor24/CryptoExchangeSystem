package org.globant.model.order;

import java.math.BigDecimal;

import org.globant.enums.Cryptos;
import org.globant.model.user.User;

public class SalesOrder extends Order{

    public SalesOrder(Cryptos crypto, String cryptoMount, String maximumPrice, User user, int userId) {
        super(crypto, cryptoMount, maximumPrice, user, userId);
    }
}

