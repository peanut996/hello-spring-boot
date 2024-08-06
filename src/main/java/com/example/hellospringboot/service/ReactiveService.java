package com.example.hellospringboot.service;

import io.reactivex.rxjava3.core.Observable;

public interface ReactiveService {

    public Observable<String> getObservable();
}
