package com.baymax.navigithubapp.di

import android.app.Application
import android.content.Context
import com.baymax.navigithubapp.BuildConfig
import com.baymax.navigithubapp.api.ApiService
import com.baymax.navigithubapp.ui.activity.main_activity.data.RemoteDataSource
import com.baymax.navigithubapp.ui.activity.main_activity.data.Repository
import com.baymax.navigithubapp.utils.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideConvertorFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(Gson())
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private fun createRetrofit(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }

    private fun <T> provideApiServices(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
        clazz: Class<T>
    ): T {
        return createRetrofit(okHttpClient, converterFactory).create(clazz)
    }

    @Singleton
    @Provides
    fun provideGithubApiServices(
        okHttpClient: OkHttpClient,
        converterFactory: GsonConverterFactory,
    ) = provideApiServices(
        okHttpClient,
        converterFactory,
        ApiService::class.java
    )

    @Singleton
    @Provides
    fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource): Repository {
        return Repository(remoteDataSource)
    }
}