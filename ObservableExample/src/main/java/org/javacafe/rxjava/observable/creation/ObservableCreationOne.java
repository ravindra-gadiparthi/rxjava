package org.javacafe.rxjava.observable.creation;

import rx.Observable;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class ObservableCreationOne
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );

        System.out.println("Creating observable with one instance");

        Observable observable=Observable.just(Integer.valueOf(42));

        observable.subscribe(System.out::println);

        System.out.println("Creating observable with iterator");

        observable=Observable.from(()-> Arrays.asList(1,1,2,3,5,8,13).iterator());

        observable.subscribe((item)->{
            System.out.println("generationg from iterator "+item);
        });


        System.out.println("Creating observable with array");


        observable=Observable.from(new Integer[]{1,1,2,3,5,8,13});

        observable.subscribe((item)->{
            System.out.println("generationg from array "+item);
        });
    }


}
