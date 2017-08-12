package org.javacafe.rxjava.observable.creation;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
//import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created by ravindra on 29/07/17.
 */
public class ObservableCreationTwo {

    public static void main(String[] args) {

        //creating a future task
        FutureTask<List<Integer>> future=new FutureTask<List<Integer>>(()-> Arrays.asList(1,1,2,3,5,8,13));


        Observable<List<Integer>> observableFutureList=Observable.from(future);

        Schedulers.computation().createWorker().schedule(()->future.run());

        observableFutureList.subscribe((list)->{list.forEach(System.out::println);});




    }

}
