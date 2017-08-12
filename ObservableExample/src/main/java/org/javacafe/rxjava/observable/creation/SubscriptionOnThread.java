package org.javacafe.rxjava.observable.creation;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Arrays;

/**
 * Created by ravindra on 29/07/17.
 */
public class SubscriptionOnThread {

    public static void main(String[] args) {

        System.out.println("creating a observable with subscriberOn and without observableOn");
        System.out.println("***********************************************");
        System.out.println("Current Thread :"+ThreadUtils.getCurrentThreadName());
        System.out.println("***********************************************");

        Observable observable=Observable.from(()->Arrays.asList(1,1,2,3,5,8,13,21,34).iterator());

        synchronized (new Object()) {

            //running each subscriber thread on new thread
            observable.subscribeOn(Schedulers.newThread())
                    .subscribe(
                            ((item) ->
                            {
                                System.out.println("On Next :thread enter" + ThreadUtils.getCurrentThreadName());
                                System.out.println(" value " + item);
                                System.out.println("On Next :thred exit" + ThreadUtils.getCurrentThreadName());

                            }), (throwable -> {
                                System.out.print(throwable);
                            }), () -> {
                                System.out.print("On Complete");
                            });

        }
        System.exit(0);
    }
}
