package org.globant.model.order;

import java.math.BigDecimal;

import org.globant.enums.Cryptos;
import org.globant.model.user.User;

public class BuyOrder extends Order {

    public BuyOrder(Cryptos crypto, String cryptoMount, String maximumPrice, User user, int userId) {
        super(crypto, cryptoMount, maximumPrice, user, userId);
    }
}
