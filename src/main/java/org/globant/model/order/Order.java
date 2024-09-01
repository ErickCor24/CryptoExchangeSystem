package org.globant.model.order;

import org.globant.enums.Cryptos;
import org.globant.model.user.User;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Order {

    private final Cryptos crypto;
    private final BigDecimal cryptoAmount;
    private final BigDecimal maximumPrice;
    private User user;
    private final int userId;

    /**
     * Constructor f Order
     * @param crypto enum crypto
     * @param cryptoAmount crypto amount
     * @param maximumPrice maximum price paid
     * @param user user order
     * @param userId user id order
     */
    public Order(Cryptos crypto, String cryptoAmount, String maximumPrice, User user, int userId) {
        this.crypto = crypto;
        this.cryptoAmount = new BigDecimal(cryptoAmount);
        this.maximumPrice = new BigDecimal(maximumPrice);
        this.user = user;
        this.userId = userId;
    }

    public Cryptos getCrypto() {
        return crypto;
    }

    public int getUserId() {
        return userId;
    }

    public BigDecimal getCryptoAmount() {
        return cryptoAmount;
    }

    public BigDecimal getMaximumPrice() {
        return maximumPrice;
    }

    @Override
    public String toString() {
        return "Order" +
                "crypto=" + crypto.toString() +
                ", cryptoMount=" + cryptoAmount +
                ", maximumPrice= $" + maximumPrice.setScale(2, RoundingMode.HALF_UP) +
                ", user=" + user.getUserName() +
                "\n";
    }
}
