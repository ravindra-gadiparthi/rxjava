package org.rxjava.venkat;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by ravindra on 09/08/17.
 */
public class Sample {

    public static void main(String[] args) {

        List<String> symbols= Arrays.asList("Google","Yahoo","twitter","INTC");
        //System.out.println(stocks.stream().map(String::toLowerCase).collect(Collectors.toList()));

        //it doesn't process when you request it.
        Observable<StackInfo> feed=StockServer.getFeed(symbols);

        System.out.println("got observable");


        //java 8
        //feed.subscribe(System.out::println);

         feed.subscribe(System.out::println,System.out::println,()->{
            System.out.println("Done");
        });



    }


}
