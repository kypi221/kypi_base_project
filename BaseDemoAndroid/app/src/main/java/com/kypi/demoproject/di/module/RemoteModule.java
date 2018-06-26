package com.kypi.demoproject.di.module;

import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.kypi.demoproject.BuildConfig;
import com.kypi.demoproject.app.constants.ApiConstant;
import com.kypi.demoproject.app.gson.GsonBooleanAdapter;
import com.kypi.demoproject.data.remote.IReadDemoApiService;
import com.kypi.demoproject.domain.debugs.DebugConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class RemoteModule  {
    private static final long TIME_OUT = 10 * 1000;
    private static final String UserAgent = "Android/" + Build.VERSION.SDK_INT +
            " (" + Build.BRAND + "; " +
            Build.MODEL + "; " +
            BuildConfig.VERSION_NAME + "; " +
            BuildConfig.VERSION_CODE + ")";


    private static int SDK = Build.VERSION.SDK_INT;
    private static String Device = Build.DEVICE;
    private static String Model = Build.MODEL;
    private static String Product = Build.PRODUCT;


    /**
     * Config API cho các API demo của IRead
     * @param debugConfig
     * @return
     */
    @Provides
    @Singleton
    public IReadDemoApiService provideDemoApiService(DebugConfig debugConfig) {

        OkHttpClient okHttpClient = createOkHttp(debugConfig);
        GsonConverterFactory converterFactory = createGsonConverterFactoryMobile();
        String apiEndpoint = ApiConstant.IREAD_DEV_END_POINT_MOBILE;

        Retrofit restAdapter = createRetrofit(
                okHttpClient,
                converterFactory,
                apiEndpoint);

        return restAdapter.create(IReadDemoApiService.class);
    }













    /**
     * Khởi tạo OkHttpClient
     * @param debugConfig
     * @return
     */
    private OkHttpClient createOkHttp(DebugConfig debugConfig){
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (debugConfig.isShowLogAPI()) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }

        if (debugConfig.isSimulateSlowNetWork()) {
            builder.addInterceptor(chain -> {
                try {
                    Thread.sleep(debugConfig.getSimulateNetWorkTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return chain.proceed(chain.request());
            });
        }

        builder.addInterceptor(chain -> {
            Request original = chain.request();

            Request request = original.newBuilder()
                    .header("User-Agent", UserAgent)
                    .method(original.method(), original.body())
                    .build();

            Response response = null;
            try {
                response = chain.proceed(request);
            } catch (Exception e) {
                return chain.proceed(original);
            }
            return response;
        });

        builder.connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIME_OUT, TimeUnit.MILLISECONDS);

        return builder.build();
    }



    private GsonConverterFactory createGsonConverterFactoryMobile() {
        GsonBooleanAdapter serializer = new GsonBooleanAdapter();
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Boolean.class, serializer)
                .registerTypeAdapter(boolean.class, serializer);

        // Adding custom deserializers
        Gson myGson = gsonBuilder.create();
        return GsonConverterFactory.create(myGson);
    }

    private Retrofit createRetrofit(OkHttpClient okHttpClient,
                                   GsonConverterFactory gsonConverterFactory,
                                   String endPoint) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
                .baseUrl(endPoint)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory);
        return builder.build();
    }

}
