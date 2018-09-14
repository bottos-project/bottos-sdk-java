package com.bottos.botc.sdk.net.api;

import android.util.LruCache;

import com.bottos.botc.sdk.Config.Config;
import com.bottos.botc.sdk.log.BtoLog;
import com.bottos.botc.sdk.net.interceptor.HttpRequestInterceptor;
import com.bottos.botc.sdk.net.response.CommonResponse;
import com.bottos.botc.sdk.utils.GJsonUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by xionglh on 2018/9/4.
 */
public abstract class RetrofitClient {

    public static final int SUCCESS_REQUEST_CODE = 0;
    private static final String BASE_URL = Config.BASE_NET_URL;
    private static Retrofit retrofit;
    private static final String MEDIA_TYPE = "application/json;charset=utf-8";
    private static LruCache<String, Object> apiLruCache = new LruCache<>(Integer.MAX_VALUE);

    protected <T> T getService(Class<T> cls) {
        Object o = apiLruCache.get(cls.getName());
        T t;
        if (o != null) {
            t = (T) o;
        } else {
            t = getRetrofit().create(cls);
            apiLruCache.put(cls.getName(), t);
        }
        return t;
    }

    private Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            if (Config.DEBUG_MODE)
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            else
                interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            HttpRequestInterceptor.Builder httpCommonIntercepoter = new HttpRequestInterceptor.Builder();
            OkHttpClient client = new OkHttpClient.Builder().retryOnConnectionFailure(true).connectTimeout(15, TimeUnit.SECONDS).addNetworkInterceptor(interceptor).addInterceptor(httpCommonIntercepoter.build()).build();
            retrofit = new Retrofit.Builder().client(client).baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        }
        return retrofit;
    }

    protected RequestBody toRequestBody(Object object) {
        String json = GJsonUtil.toJson(object, Object.class);
        return RequestBody.create(MediaType.parse(MEDIA_TYPE), json);
    }

//    protected <T> Observable applySchedulersRestful(Observable<T> responseObservable) {
//        return responseObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(new Func1<T, Observable<?>>() {
//            @Override
//            public Observable<?> call(final T t) {
//                return Observable.create(new Observable.OnSubscribe<T>() {
//                    @Override
//                    public void call(Subscriber<? super T> subscriber) {
//                        if (t != null && !subscriber.isUnsubscribed()) {
//                            subscriber.onNext(t);
//                        } else {
//                            subscriber.onError(new NetRequestException(SUCCESS_REQUEST_CODE, "Network request failed"));
//                            return;
//                        }
//                        if (!subscriber.isUnsubscribed()) {
//                            subscriber.onCompleted();
//                        }
//                    }
//                });
//            }
//        });
//    }

    protected <T> Observable applySchedulers(Observable<CommonResponse<T>> responseObservable) {
        return responseObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(new Func1<CommonResponse<T>, Observable<?>>() {
            @Override
            public Observable<?> call(final CommonResponse<T> tCommonResponse) {
                return Observable.create(new Observable.OnSubscribe<T>() {
                    @Override
                    public void call(Subscriber<? super T> subscriber) {
                        if (tCommonResponse != null && !subscriber.isUnsubscribed()) {
                            if (tCommonResponse.getErrcode() == SUCCESS_REQUEST_CODE) {
                                subscriber.onNext(tCommonResponse.getResult());
                            } else {
                                subscriber.onError(new NetRequestException(tCommonResponse.getErrcode(), tCommonResponse.getMsg()));
                            }
                        } else {
                            if (!subscriber.isUnsubscribed()) {
                                subscriber.onError(new NetRequestException(SUCCESS_REQUEST_CODE, "Network request failed"));
                                return;
                            }
                        }
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onCompleted();
                        }
                    }
                });
            }
        });
    }

    protected <T> Subscriber newMySubscriber(final RequestCallBackImp requestCallBack) {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                requestCallBack.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof NetRequestException) {
                    NetRequestException exception = (NetRequestException) e;
                    requestCallBack.onError(exception);
                    BtoLog.e("ApiException", exception.getMessage());
                } else {
                    BtoLog.e("Net Exception", e.getMessage());
                }
            }

            @Override
            public void onNext(T t) {
                requestCallBack.onNext(t);
            }
        };
    }


}



