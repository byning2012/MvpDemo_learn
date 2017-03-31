package com.learn.lisen.mvpdemo_learn.base;

/**
 * Created by Administrator on 2017/3/29.
 */

public interface BasePresenter<T> {
    void attachView(T view);

    void detachView();
}
