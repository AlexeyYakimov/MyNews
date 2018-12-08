package ru.polosatuk.mycard.network.api;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.polosatuk.mycard.BuildConfig;

public class NytClient {
    private static final String NYT_BASE_URI = "https://api.nytimes.com/svc/topstories/v2/";
    private static final int TIMEOUT_IN_SECONDS = 15;
    private static final String API_KEY = ApiKey.getApiKey();

    private static NytClient nytClient;
    private static NytApi nytApi;

    private NytClient() {
        final OkHttpClient okHttpClient = buildOkhttpClient();
        final Retrofit retrofit = buildRetrofit(okHttpClient);

        nytApi = retrofit.create(NytApi.class);
    }

    public static synchronized NytClient getInstance() {
        if (nytClient == null) {
            nytClient = new NytClient();
        }
        return nytClient;
    }

    private OkHttpClient buildOkhttpClient() {
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor networkLogInterceptor = new HttpLoggingInterceptor();
            networkLogInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            return new OkHttpClient.Builder()
                    .addInterceptor(ApiInterceptor.create(API_KEY))
                    .addInterceptor(networkLogInterceptor)
                    .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .build();
        } else {
            return new OkHttpClient.Builder()
                    .addInterceptor(ApiInterceptor.create(API_KEY))
                    .connectTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                    .build();
        }
    }

    private Retrofit buildRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(NYT_BASE_URI)
                .build();
    }

    public NytApi getNews() {
        return nytApi;
    }

}
