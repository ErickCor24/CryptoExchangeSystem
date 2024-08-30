package org.globant.model.order;

import org.globant.enums.Cryptos;
import org.globant.model.user.User;

import java.math.BigDecimal;

public abstract class Order {

    private Cryptos crypto;
    private BigDecimal cryptoMount;
    private BigDecimal maximumPrice;
    private User user;
    private int userId;

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

    public void setCryptoMount(BigDecimal cryptoMount) {
        this.cryptoMount = cryptoMount;
    }

    public BigDecimal getMaximumPrice() {
        return maximumPrice;
    }

    public void setMaximumPrice(BigDecimal maximumPrice) {
        this.maximumPrice = maximumPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "crypto=" + crypto.toString() +
                ", cryptoMount=" + cryptoMount +
                ", maximumPrice=" + maximumPrice +
                ", user=" + user.getUserName() +
                '}';
    }
}
