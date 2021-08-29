package com.baymax.navigithubapp.di

import com.baymax.navigithubapp.ui.activity.main_activity.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}