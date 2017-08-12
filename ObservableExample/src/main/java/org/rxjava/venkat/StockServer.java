package org.rxjava.venkat;

import rx.Observable;
import rx.Subscriber;

import java.util.List;

/**
 * Created by ravindra on 09/08/17.
 */
public class StockServer {

    public static Observable<StackInfo> getFeed(List<String> symbols) {
        return Observable.create(subscriber -> processRequest(subscriber,symbols));
    }

    private static void processRequest(Subscriber<? super StackInfo> subscriber, List<String> symbols) {
        System.out.println("Processing requst on subscriber");

        //controlling iteration and unsubscribe to the channel
        while(!subscriber.isUnsubscribed())
        {
            //generates stock data from stock
            symbols.stream()
                    .map(StockFetcher::getStockInfo)//fetch stock for each symbol
                    .filter(e->!subscriber.isUnsubscribed())
                    .forEach(subscriber::onNext);//pass it to the data channel
        }
    }
}
