package org.javacafe.rxjava.observable.creation;

/**
 * Created by ravindra on 29/07/17.
 */
public class ThreadUtils {
    public static String getCurrentThreadName() {
        if(Thread.currentThread().getName()==null)
        {
            Thread.currentThread().setName("My Thread");
        }
        return Thread.currentThread().getName();
    }
}
