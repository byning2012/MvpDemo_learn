package com.learn.lisen.mvpdemo_learn.model.http;

import com.learn.lisen.mvpdemo_learn.model.bean.RobotModel;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/29.
 */

public interface RobotApi {
    //https://api.heweather.com/v5/search?city=%E6%B7%B1%E5%9C%B3&key=c872461e27c34713ab67c1ce22041423

    @FormUrlEncoded
    @POST("search?")
    Observable<RobotModel> getRobotInfo(
            @Field("city") String city,
            @Field("key") String key);
}
