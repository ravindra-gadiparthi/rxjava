package org.rxjava.venkat;

import com.sun.deploy.util.StringUtils;
import rx.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ravindra on 12/08/17.
 */
public class TransformingObservables {
    public static void main(String[] args) {

        List<String> symbols = Arrays.asList("Google", "Yahoo", "twitter", "INTC");
        Observable<StackInfo> feed = StockServer.getFeed(symbols);


        /**
         * Using take you can limit the number of resulting objects
         * Using filter you can make decisions.
         * Using the map you can transform result into any other object.
         **/

        feed.take(4)
                .filter(stackInfo -> "Google".equalsIgnoreCase(stackInfo.getTicker()))
                .map(stackInfo -> new StackInfo(stackInfo.getTicker(), stackInfo.getPrice() * 0.9))
                .subscribe(System.out::println, System.out::println, () -> {
                    System.out.println("on complete execution is Done ontake ");
                });


        /**
         * takeWhile helps you to mirror items emitted by an Observable until a specified condition becomes false.
         */
        feed.takeWhile(stackInfo -> stackInfo.getPrice() > 500)
                .subscribe(System.out::println, System.out::println, () -> {
                    System.out.println("on complete execution is Done on takeWhile");
                });


        /**
         * discard items emitted by an Observable until a specified condition becomes false
         */
        feed.skipWhile(stackInfo -> stackInfo.getPrice() > 400)
                .subscribe(System.out::println, System.out::println, () -> {
                    System.out.println("on complete execution is Done on skipWhile");
                });


    }
}
