package org.globant.model.order;

import org.globant.enums.Cryptos;
import org.globant.model.user.User;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Order {

    private Cryptos crypto;
    private final BigDecimal cryptoMount;
    private final BigDecimal maximumPrice;
    private User user;
    private final int userId;

    public Order(Cryptos crypto, String cryptoMount, String maximumPrice, User user, int userId) {
        this.crypto = crypto;
        this.cryptoMount = new BigDecimal(cryptoMount);
        this.maximumPrice = new BigDecimal(maximumPrice);
        this.user = user;
        this.userId = userId;
    }

    public Cryptos getCrypto() {
        return crypto;
    }

    public void setCrypto(Cryptos crypto) {
        this.crypto = crypto;
    }

    public int getUserId() {
        return userId;
    }

    public BigDecimal getCryptoMount() {
        return cryptoMount;
    }


    public BigDecimal getMaximumPrice() {
        return maximumPrice;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order" +
                "crypto=" + crypto.toString() +
                ", cryptoMount=" + cryptoMount +
                ", maximumPrice= $" + maximumPrice.setScale(2, RoundingMode.HALF_UP) +
                ", user=" + user.getUserName() +
                "\n";
    }
}
