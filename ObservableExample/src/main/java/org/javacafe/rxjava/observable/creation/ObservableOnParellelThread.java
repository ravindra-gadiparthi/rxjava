package org.javacafe.rxjava.observable.creation;

import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Arrays;

/**
 * Created by ravindra on 29/07/17.
 */
public class ObservableOnParellelThread {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("creating a observable without subscriberOn and with observableOn");
        System.out.println("***********************************************");
        System.out.println("Current Thread :"+ThreadUtils.getCurrentThreadName());
        System.out.println("***********************************************");



        Object monitor=new Object();

        //making sure we are giving chance to execute all threads
        synchronized (monitor) {

            Observable<Integer> observable=Observable.from(Arrays.asList(1,1,2,3,5,8,13,21,34));
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
                                synchronized (monitor)
                                {
                                    //notifying thread to wake up on completion
                                    monitor.notify();
                                }
                            });

            //making thread to wait to finish all the operations
            monitor.wait();
        }
        System.exit(0);

    }
}
