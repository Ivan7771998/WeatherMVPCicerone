package com.dev777popov.weathermvpcicerone.di.module

import com.dev777popov.weathermvpcicerone.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App = app
}