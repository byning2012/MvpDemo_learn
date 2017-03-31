package com.learn.lisen.mvpdemo_learn.presenter;

import com.learn.lisen.mvpdemo_learn.base.RxPresenter;
import com.learn.lisen.mvpdemo_learn.model.bean.RobotModel;
import com.learn.lisen.mvpdemo_learn.model.http.ApiManager;
import com.learn.lisen.mvpdemo_learn.presenter.contract.LoginConstract;
import android.support.annotation.NonNull;
import android.util.Log;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/29.
 */

public class LoginPresenter extends RxPresenter<LoginConstract.View> implements LoginConstract.Presenter {

    private LoginConstract.View mView;
    public LoginPresenter(LoginConstract.View mView) {
        this.mView =mView;
    }

    @Override
    public void login(String question, String key) {
        Subscription subscription = ApiManager.getInstance().getRobotApiService()
                .getRobotInfo(question,key)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                    mView.showProgress();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RobotModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    mView.showErrorMsg();
                    }

                    @Override
                    public void onNext(RobotModel robotModel) {
                        mView.hideProgress();
                        mView.onLoginSucess(robotModel);//就是说这里是不执行的
                    }
                });
        addSubscribe(subscription);
    }
}
