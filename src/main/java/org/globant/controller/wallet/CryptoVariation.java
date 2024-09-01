package org.globant.controller.wallet;

import org.globant.repository.ExchangeWalletRepository;

import java.math.BigDecimal;
import java.util.Random;

public class CryptoVariation implements Runnable {
    ExchangeWalletRepository exchangeWalletRepository = ExchangeWalletRepository.getInstance();
    Random random = new Random();

    /**
     * Method for make the price of cryptos fluctuate
     */
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(20000);
                int number = random.nextInt(0, 7);
                double btcVariation = random.nextDouble(0,0.7);
                double ethVariation = random.nextDouble(0,0.3);
                double uniVariation = random.nextDouble(0, 0.08);
                switch (number) {
                    case 0:
                        exchangeWalletRepository.getExchangeWallet().getBitcoinCrypto().addPriceCrypto(new BigDecimal(btcVariation));
                        exchangeWalletRepository.getExchangeWallet().getEthereumCrypto().subtractPriceCrypto(new BigDecimal(ethVariation));
                        break;
                    case 1:
                        exchangeWalletRepository.getExchangeWallet().getEthereumCrypto().addPriceCrypto(new BigDecimal(ethVariation));
                        exchangeWalletRepository.getExchangeWallet().getBitcoinCrypto().subtractPriceCrypto(new BigDecimal(btcVariation));
                        break;
                    case 2:
                        exchangeWalletRepository.getExchangeWallet().getUnisWapCrypto().addPriceCrypto(new BigDecimal(uniVariation));
                        exchangeWalletRepository.getExchangeWallet().getEthereumCrypto().subtractPriceCrypto(new BigDecimal(ethVariation));
                        break;
                    case 3:
                        exchangeWalletRepository.getExchangeWallet().getBitcoinCrypto().addPriceCrypto(new BigDecimal(btcVariation));
                        exchangeWalletRepository.getExchangeWallet().getUnisWapCrypto().subtractPriceCrypto(new BigDecimal(uniVariation));
                        break;
                    case 4:
                        exchangeWalletRepository.getExchangeWallet().getEthereumCrypto().addPriceCrypto(new BigDecimal(ethVariation));
                        exchangeWalletRepository.getExchangeWallet().getUnisWapCrypto().subtractPriceCrypto(new BigDecimal(uniVariation));
                        break;
                    case 5:
                        exchangeWalletRepository.getExchangeWallet().getBitcoinCrypto().subtractPriceCrypto(new BigDecimal(btcVariation));
                        exchangeWalletRepository.getExchangeWallet().getUnisWapCrypto().addPriceCrypto(new BigDecimal(uniVariation));
                        break;
                }
            } catch (InterruptedException e) {
                System.out.println("Error has occurred");
                break;
            }
        }

    }
}
