package cn.n0texpecterr0r.timemovie.base.rx;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class RxSchedulers {

    public static Scheduler io() {
        return Schedulers.io();
    }

    public static Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    public static Scheduler computation() {
        return Schedulers.computation();
    }

    public static Scheduler newThread() {
        return Schedulers.newThread();
    }

    public static Scheduler single() {
        return Schedulers.single();
    }
}
