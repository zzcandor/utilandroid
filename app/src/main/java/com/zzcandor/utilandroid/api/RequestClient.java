package com.zzcandor.utilandroid.api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者: 赵成柱 on 2016/7/19 0019.
 */
public class RequestClient {


    private static volatile ApiService sServerAPI;//单例模式


    public static ApiService getApiService() {
        if (sServerAPI == null) {
            synchronized (RequestClient.class) {
                if (sServerAPI == null) {
                    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
                    sServerAPI = getRetrofitBuilder("http://www.wanandroid.com", clientBuilder.build())
                            .build()
                            .create(ApiService.class);
                }
            }
        }
        return sServerAPI;
    }

    /**
     * @param url    域名
     * @param client okhttp请求客户端
     * @return retrofit的构建器
     */
    private static Retrofit.Builder getRetrofitBuilder(String url, OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(url)

                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client);
    }


    private static OkHttpClient.Builder getClientBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(7, TimeUnit.SECONDS)
                .readTimeout(7, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);//重试
        // .writeTimeout(HttpConfig.WRITE_TIME_OUT_SECONDS, TimeUnit.SECONDS);
    }
}
