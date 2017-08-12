package org.rxjava.venkat;

import rx.Observable;
import rx.Subscriber;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by ravindra on 10/08/17.
 */
public class EventProcessing {

    public static void main(String[] args) {

        List<String> symbols = Arrays.asList("Google", "Yahoo", "twitter", "INTC");
        //System.out.println(stocks.stream().map(String::toLowerCase).collect(Collectors.toList()));

        //it doesn't process when you request it.
        Observable<StackInfo> feed = StockServer.getFeed(symbols);


        feed.subscribe(new Subscriber<StackInfo>() {
            @Override
            public void onCompleted() {
                System.out.println("Completed processing");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("Error occured " + throwable);
            }

            @Override
            public void onNext(StackInfo stackInfo) {
                System.out.println("subscriber 1 " + stackInfo);
                if ("Google".equalsIgnoreCase(stackInfo.getTicker()) && stackInfo.getPrice() > 800) {
                    System.out.println("Thanks you ,I dont want any more data " + stackInfo);
                    unsubscribe();
                }

            }
        });

        feed.subscribe(e -> {
            System.out.println("subscriber 2 " + e);
        });


    }
}
