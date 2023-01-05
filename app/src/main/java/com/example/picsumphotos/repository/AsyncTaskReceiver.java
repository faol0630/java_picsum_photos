package com.example.picsumphotos.repository;

public interface AsyncTaskReceiver<T> {
    void onSuccess(T result);
    void onFailure(Throwable throwable);
}
