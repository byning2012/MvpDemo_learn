package com.learn.lisen.mvpdemo_learn.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/30.
 */

public abstract class BaseActivity <T extends BasePresenter> extends AppCompatActivity implements BaseView{

    protected T mPresenter;
    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        mActivity = this;
        initInject();
//        if (mPresenter!=null){
//            mPresenter.attachView(this);
//        }
        initEventAndData();
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
     }
    protected  abstract void initInject();
    protected abstract int getLayout();
    protected abstract void initEventAndData();
}
