package com.realize.net

//import io.reactivex.internal.schedulers.RxThreadFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by SongWenjun
 * 2021/9/12
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...
 */
object RetorfitTools {
    lateinit var retrofit: Retrofit

    fun init(baseUrl: String) {
        retrofit = Retrofit.Builder()
            .client(getOkhttpCliient())
            .baseUrl(baseUrl)
            .addCallAdapterFactory(LiveDataCallAdapter.Factory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getOkhttpCliient() = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(LoggingInterceptor())
        .build()
}