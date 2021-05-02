package com.dev777popov.weathermvpcicerone.di.module

import com.dev777popov.weathermvpcicerone.mvp.navigation.IScreens
import com.dev777popov.weathermvpcicerone.ui.navigation.AndroidScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CiceroneModule {

    val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Provides
    fun router(): Router = cicerone.router

    @Provides
    @Singleton
    fun screens(): IScreens = AndroidScreens()
}