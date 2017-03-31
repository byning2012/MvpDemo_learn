package com.learn.lisen.mvpdemo_learn.model.http;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.learn.lisen.mvpdemo_learn.BuildConfig;
import com.learn.lisen.mvpdemo_learn.app.Constants;
import com.learn.lisen.mvpdemo_learn.utils.LogUtil;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/3/29.
 */

public class ApiManager {

    private static ApiManager apiManager;

    public static ApiManager getInstance(){
        synchronized (ApiManager.class){
            if (apiManager == null){
                apiManager = new ApiManager();
            }
        }
        return apiManager;
    }
    RobotApi robotApi;
    private Object object = new Object();
    public RobotApi getRobotApiService(){
        OkHttpClient.Builder client = new OkHttpClient.Builder()
         .addInterceptor(new LogingInterceptor())//添加请求拦截
         .connectTimeout(10000, TimeUnit.SECONDS)//设置超时时间
         .retryOnConnectionFailure(true);
        if (BuildConfig.DEBUG){
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(
                    new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            if (TextUtils.isEmpty(message)) return;
                            //如果收到想响应是json才打印
                            try {
                                StringReader reader = new StringReader(message);
                                Properties properties = new Properties();
                                properties.load(reader);
                                properties.list(System.out);
                                LogUtil.e(properties.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //     LogUtil.e("收到响应: " + message);
                        }
                    });
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(logging);
        }
        if (robotApi ==null){
            synchronized ((object.getClass())){
                if (robotApi==null){
                    robotApi = new Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .client(client.build())
                            .baseUrl(Constants.HOST)
                            .build()
                            .create(RobotApi.class);
                }
            }
        }
        return  robotApi;
    }

    /**
     * 拦截器
     */
    public class LogingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (BuildConfig.DEBUG){
                LogUtil.e(String.format("发送请求 %s on %s%n%s",
                        request.url(),chain.connection(),request.headers()));
            }
            return chain.proceed(request);
        }
    }
}
