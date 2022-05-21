package com.example.mozpertest.features.networking.di.modules

import com.example.mozpertest.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    private const val TIMEOUT = 60L
    private const val LONG_TIMEOUT = 100L

    const val EMPLOYEES_RETROFIT = "EMPLOYEES_RETROFIT"

    val module by lazy {
        module {
            single(named(EMPLOYEES_RETROFIT)) {
                provideRetrofit()
            }
        }
    }

    private fun provideRetrofit(): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        return builder.build()
    }

    private fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        val clientBuilder = OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            callTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(LONG_TIMEOUT, TimeUnit.SECONDS)
        }

        return clientBuilder.build()
    }
}
