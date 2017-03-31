package com.learn.lisen.mvpdemo_learn;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.learn.lisen.mvpdemo_learn.base.BaseActivity;
import com.learn.lisen.mvpdemo_learn.model.bean.RobotModel;
import com.learn.lisen.mvpdemo_learn.presenter.LoginPresenter;
import com.learn.lisen.mvpdemo_learn.presenter.contract.LoginConstract;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<LoginPresenter> implements LoginConstract.View {

    @BindView(R.id.my_tv)
    TextView myTv;
    @BindView(R.id.my_btn)
    Button myBtn;

    ProgressDialog mProgressDialog;
    //
    @BindString(R.string.key)
    String key;

    @Override
    protected void initInject() {
        mPresenter = new LoginPresenter(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setTitle("sssss");

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
      
    }

    @OnClick(R.id.my_btn)
    public void onViewClicked() {
        //  myBtn.setText(R.string.content);
        //myTv.setText(key);
        mPresenter.login("深圳", key);//这是执行的！
    }


    @Override
    public void showError(String msg) {

    }

    @Override
    public void showErrorMsg() {

    }

    @Override
    public void showProgress() {
        myTv.setText(1 + "2");
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.hide();
    }

    @Override
    public void onLoginSucess(RobotModel robotModel) {
        myTv.setText(robotModel.getHeWeather5().get(0).getBasic().getCnty());

    }

    @Override
    public void onLoginError() {

    }

}
