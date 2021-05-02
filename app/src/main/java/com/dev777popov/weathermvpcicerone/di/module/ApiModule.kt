package com.dev777popov.weathermvpcicerone.di.module

import com.dev777popov.weathermvpcicerone.App
import com.dev777popov.weathermvpcicerone.mvp.api.IDataSource
import com.dev777popov.weathermvpcicerone.mvp.model.network.INetworkStatus
import com.dev777popov.weathermvpcicerone.ui.network.AndroidNetworkStatus
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Named("baseUrl")
    fun baseUrl(): String = "https://api.github.com"

    @Provides
    @Singleton
    fun api(@Named("baseUrl") baseUrl: String, gson: Gson): IDataSource = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(IDataSource::class.java)

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .excludeFieldsWithoutExposeAnnotation()
        .create()

    @Provides
    @Singleton
    fun networkStatus(app: App): INetworkStatus = AndroidNetworkStatus(app)

    @Provides
    @Named("io")
    fun schedulerIO(): Scheduler = Schedulers.io()

    @Provides
    @Named("main")
    fun schedulerMainThread(): Scheduler = AndroidSchedulers.mainThread()
}