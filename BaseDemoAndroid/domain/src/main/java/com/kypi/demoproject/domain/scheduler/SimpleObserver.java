package com.kypi.demoproject.domain.scheduler;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Khoa on 8/10/2017.
 */

public abstract class SimpleObserver<T> extends DisposableObserver<T> {

    public SimpleObserver(){
    }

    @Override
    public void onNext(@NonNull T t) {
        try {
            onResponse(t);
        }
        catch (Exception e){
            onError(e);

        }
    }

    @Override
    public void onError(@NonNull Throwable throwable) {
        try {
            onResponseError(throwable);
            throwable.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public abstract void onResponse(@NonNull T t) throws Exception;
    public abstract void onResponseError(@NonNull Throwable throwable) throws Exception;

    @Override
    public void onComplete() {
    }
}
