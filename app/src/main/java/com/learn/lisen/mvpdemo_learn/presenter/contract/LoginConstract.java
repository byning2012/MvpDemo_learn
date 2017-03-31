package com.learn.lisen.mvpdemo_learn.presenter.contract;

import com.learn.lisen.mvpdemo_learn.base.BasePresenter;
import com.learn.lisen.mvpdemo_learn.base.BaseView;
import com.learn.lisen.mvpdemo_learn.model.bean.RobotModel;

/**
 * Created by Administrator on 2017/3/29.
 *关于activty里面的view和逻辑方法
 */

public interface LoginConstract {
        //view接口负责显示和view相关的方法（显示土司，登录失败成功 的逻辑，显示diglog）
    interface View extends BaseView{
            void showErrorMsg();
            void showProgress();
            void hideProgress();
            void onLoginSucess(RobotModel robotModel);
            void onLoginError();

        }
    //presenter接口负责登录请求方法，被view持用
    interface Presenter extends BasePresenter<View>{
        void login(String question,String key);
    }
}
