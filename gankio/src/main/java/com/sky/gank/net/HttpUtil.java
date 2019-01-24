package com.sky.gank.net;


import android.arch.lifecycle.Lifecycle;

import com.sky.gank.BuildConfig;
import com.sky.gank.net.rxutil.DataCallback;
import com.sky.gank.net.rxutil.RxHelper;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 类名称：
 * 类功能：
 * 类作者：Sky
 * 类日期：2018/12/11 0011.
 **/
public class HttpUtil {

    private Retrofit mRetrofit;
    private static volatile HttpUtil INSTANCE;

    public static HttpUtil getInstance() {
        if (INSTANCE == null) {
            synchronized (HttpUtil.class) {
                if (INSTANCE == null) {
                    INSTANCE = new HttpUtil();
                }
            }
        }
        return INSTANCE;
    }

    private HttpUtil() {
        OkHttpClient httpClient = provideHttpClient();
        mRetrofit = provideRetrofit(httpClient);
    }

    private OkHttpClient provideHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 连接超时设置
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }
        return builder.build();
    }

    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

    private Retrofit provideRetrofit(OkHttpClient httpClient) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(httpClient);
        //ConverterFactory 对请求和返回的字符串进行解析，可自定义ConverterFactory
        builder.addConverterFactory(GsonConverterFactory.create());
        //CallAdapterFactory 把字符串转换为对象，可自定义CallAdapterFactory
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        builder.baseUrl(Urls.GANK_API_BASE);
        return builder.build();
    }

    public Retrofit setBaseUrl(String url){
        Retrofit.Builder builder = mRetrofit.newBuilder();
        builder.baseUrl(url);
        return builder.build();
    }

    /**
     * 添加线程管理并订阅
     * 作者 @link{https://github.com/Hemumu/Template/blob/master/app/src/main/java/com/helin/template/utils/network/HttpUtil.java}
     * @param ob
     * @param subscriber
     * @param event Activity 生命周期
     * @param lifecycleSubject
     */
    public void toSubscribe(Observable ob, final Lifecycle.Event event, final PublishSubject<Lifecycle.Event> lifecycleSubject, final DataCallback subscriber) {
        //数据预处理
        ObservableTransformer<String, Object> result = RxHelper.handleResult(event, lifecycleSubject);
        Observable observable = ob.compose(result);
        //不需要缓存
        observable.subscribe(subscriber);
        //缓存
//        RetrofitCache.load(cacheKey, observable, isSave, forceRefresh).subscribe(subscriber);
    }

}
