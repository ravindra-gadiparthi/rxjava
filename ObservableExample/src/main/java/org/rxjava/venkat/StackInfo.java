package org.rxjava.venkat;

/**
 * Created by ravindra on 09/08/17.
 */
public class StackInfo {
    private final String ticker;
    private final double price;

    public StackInfo(final String ticker,final double price) {
        this.ticker = ticker;
        this.price = price;
    }

    @Override
    public String toString() {
        return "StackInfo{" +
                "ticker='" + ticker + '\'' +
                ", price=" + price +
                "}\n";
    }

    public String getTicker() {
        return ticker;
    }

    public double getPrice() {
        return price;
    }
}
