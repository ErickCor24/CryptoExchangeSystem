package org.globant.model.order;

import org.globant.enums.Cryptos;
import org.globant.model.user.User;

public class SalesOrder extends Order{

    /**
     * Constructor of BuyOrder extends of Order
     * @param crypto enum crypto
     * @param cryptoAmount crypto amount
     * @param maximumPrice maximum price paid
     * @param user user order
     * @param userId user id order
     */
    public SalesOrder(Cryptos crypto, String cryptoAmount, String maximumPrice, User user, int userId) {
        super(crypto, cryptoAmount, maximumPrice, user, userId);
    }
}

