package org.rxjava.venkat;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by ravindra on 12/08/17.
 */
public class RXSchedulers {
    public static void main(String[] args) throws InterruptedException {

        List<String> symbols = Arrays.asList("Google", "Yahoo", "twitter", "INTC");

        //it doesn't process when you request it.
        Observable<StackInfo> feed = StockServer.getFeed(symbols);

        //feed.subscribeOn(Schedulers.io()).subscribe(RXSchedulers::printStackInfo);

        //Thread.sleep(10000);

        //feed.subscribeOn(Schedulers.computation()).subscribe(RXSchedulers::printStackInfo);

        //Thread.sleep(10000);

        feed.subscribeOn(Schedulers.from(Executors.newFixedThreadPool(3))).subscribe(RXSchedulers::printStackInfo);

        Thread.sleep(10000);

    }

    public static void printStackInfo(StackInfo stackInfo) {
        System.out.println("executing the task from thread " + Thread.currentThread().getName());
        System.out.println(stackInfo);
    }

}
