package com.akarbowy.epoxyexample.data;


import com.akarbowy.epoxyexample.data.model.Article;
import com.akarbowy.epoxyexample.data.model.ArticlesListing;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ZalandoService {

    private static Api serviceInstance = null;

    public static Api getApi() {
        return serviceInstance == null ? createService() : serviceInstance;
    }

    private static Api createService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(createHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ZalandoService.Api.class);
    }

    private static OkHttpClient createHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("accept-language", "en-GB")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
    }

    public interface Api {
        String BASE_URL = "https://api.zalando.com/";

        @GET("articles")
        Call<ArticlesListing> getArticles();

        @GET("articles")
        Call<ArticlesListing> getArticlesByBrand(@Query("brand") String brand);

        @GET("recommendations/{id}")
        Call<List<Article>> getRecommendations(@Path("id") String articleId, @Query("fields") String fields);

    }
}

