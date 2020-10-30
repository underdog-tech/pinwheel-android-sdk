package com.underdog_tech.pinwheel_android_demo.repository.network

import com.underdog_tech.pinwheel_android_demo.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class SecretInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
       val newUrl = chain.request().url
           .newBuilder()
           .build()

        val newRequestBuilder = chain.request()
            .newBuilder()
            .url(newUrl)
            .addHeader("X-API-SECRET", BuildConfig.API_SECRET)

        return chain.proceed(newRequestBuilder.build())
    }
}