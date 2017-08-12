package org.rxjava.venkat;

import java.util.Random;

/**
 * Created by ravindra on 09/08/17.
 */
public class StockPriceServer {
    public static Random random = new Random();

    public static float getPrice() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return random.nextFloat() * 1000;
    }
}
