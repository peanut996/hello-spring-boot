package com.example.hellospringboot.service.impl;

import com.example.hellospringboot.service.ReactiveService;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.springframework.stereotype.Service;

@Service
public class ReactiveServiceImpl implements ReactiveService {

    public Observable<Integer> getObservable() {
        // 创建两个Single，每个都调用sleep方法


//        Single<Integer> s1 = Single.fromCallable(() -> sleep(1))
//                .subscribeOn(Schedulers.io());
//        Single<Integer> s2 = Single.fromCallable(() -> sleep(3))
//                .subscribeOn(Schedulers.io());

//        Single<Integer> s1 = Single.fromCallable(() -> sleep(1));
//        Single<Integer> s2 = Single.fromCallable(() -> sleep(3));


        Single<Integer> s1 = Single.just(sleep(1));
        Single<Integer> s2 = Single.just(sleep(3));

        // 使用zip组合两个Single的结果，并在后面使用flatMap链式调用更多的操作
        return Single.zip(s1, s2, Integer::sum)
                .toObservable();  // 转换单个的结果为Observable
    }

    // 假设sleep方法定义如下，可能有返回值也可能模拟一些延时
    private int sleep(int time) {
        try {
            System.out.println("Ready for sleep seconds: " + time);
            Thread.sleep(time * 1000);  // 模拟耗时操作，时间单位为秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return time;
    }
}
