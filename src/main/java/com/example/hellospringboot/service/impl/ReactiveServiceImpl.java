package com.example.hellospringboot.service.impl;

import com.example.hellospringboot.service.ReactiveService;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.stereotype.Service;

@Service
public class ReactiveServiceImpl implements ReactiveService {

    public Observable<String> getObservable() {
        return Observable.just("Hello", "World");
    }
}
