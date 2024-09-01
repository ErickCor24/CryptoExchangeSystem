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

//    public void setCrypto(Cryptos crypto) {
//        this.crypto = crypto;
//    }

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
