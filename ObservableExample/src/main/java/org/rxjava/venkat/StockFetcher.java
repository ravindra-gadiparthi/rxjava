package org.rxjava.venkat;

import java.util.Random;

/**
 * Created by ravindra on 09/08/17.
 */
public class StockFetcher {
    public static StackInfo getStockInfo(String symbol) {

        //enable this to test error handling
        /*if (Math.random() < 0.2)
            throw new RuntimeException("failed to process request");
*/
        return new StackInfo(symbol, StockPriceServer.getPrice());
    }


}
