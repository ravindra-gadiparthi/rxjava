package org.rxjava.venkat;

import rx.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ravindra on 12/08/17.
 */
public class ErrorHandling {
    public static void main(String[] args) {

        List<String> symbols= Arrays.asList("Google","Yahoo","twitter","INTC");
        //System.out.println(stocks.stream().map(String::toLowerCase).collect(Collectors.toList()));

        //it doesn't process when you request it.
        Observable<StackInfo> feed=StockServer.getFeed(symbols);

        /**
         * Approach 1
         * Using this ,you will be to handle to errors ,but immediatly data channel gets closed.
         */
        feed.subscribe(System.out::println,ErrorHandling::handlingError);

        /**
         * Approach 2
         * When there is requirement of calling back up service on system failures,
         * use onErrorResumeNext to provide back up
         *
         *
         */
        feed.onErrorResumeNext((throwable -> ErrorHandling.callForBackUp(throwable,symbols)))
                .subscribe(System.out::println,ErrorHandling::handlingError);

    }

    public static Observable<StackInfo> callForBackUp(Throwable throwable,List<String> symbols)
    {
        System.out.println("Handling error on backup"+throwable);

        //you can use any other service on failure, to get the stock info.
        return StockServer.getFeed(symbols);
    }

    public static void handlingError(Throwable throwable)
    {
        System.out.println("Handling error "+throwable);
    }
}
