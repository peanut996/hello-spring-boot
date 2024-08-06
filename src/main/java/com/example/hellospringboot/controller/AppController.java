package com.example.hellospringboot.controller;

import com.example.hellospringboot.service.ReactiveService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AppController {


    private final ReactiveService reactiveService;


    @GetMapping("/reactive")
    public Observable<String> getReactive() {
        return reactiveService.getObservable()
                .subscribeOn(Schedulers.io());
    }
}
