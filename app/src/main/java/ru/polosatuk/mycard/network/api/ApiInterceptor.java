package ru.polosatuk.mycard.network.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

final class ApiInterceptor implements Interceptor {
    private static final String HEADER_API_PARAM = "api-key";
    private String apiKey;

    private ApiInterceptor(String apiKey) {
        this.apiKey=apiKey;
    }

    public static Interceptor create(String apiKey) {
        return new ApiInterceptor(apiKey);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
       final Request requestWithOutApiKey = chain.request();

       final HttpUrl url = requestWithOutApiKey.url()
               .newBuilder()
               .addQueryParameter(HEADER_API_PARAM, apiKey)
               .build();
        final Request requestWithApiKey = requestWithOutApiKey
                .newBuilder()
                .url(url)
                .build();


        return chain.proceed(requestWithApiKey);
    }
}
