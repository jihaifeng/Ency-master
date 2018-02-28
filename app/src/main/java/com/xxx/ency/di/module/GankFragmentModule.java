package com.xxx.ency.di.module;

import com.xxx.ency.di.qualifier.GankURL;
import com.xxx.ency.di.scope.FragmentScope;
import com.xxx.ency.model.http.GankApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiarh on 2017/11/27.
 */

@Module
public class GankFragmentModule {

    @GankURL
    @Provides
    @FragmentScope
    Retrofit provideGankRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return builder
                .baseUrl(GankApi.HOST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @FragmentScope
    GankApi provideGankApi(@GankURL Retrofit retrofit) {
        return retrofit.create(GankApi.class);
    }
}
