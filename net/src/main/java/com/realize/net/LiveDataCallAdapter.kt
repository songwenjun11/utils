package com.realize.net

import androidx.lifecycle.LiveData
import retrofit2.*
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by SongWenjun
 * 2021/9/12
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...
 */
class LiveDataCallAdapter<R, B>(val type: Type) : CallAdapter<R, LiveData<BaseResponeBean>> {
    override fun responseType(): Type = type

    override fun adapt(call: Call<R>): LiveData<BaseResponeBean> {
        return object : LiveData<BaseResponeBean>() {
            val started = AtomicBoolean(false)

            override fun onActive() {
                super.onActive()
                if (started.compareAndSet(false, true)) {
                    call.enqueue(object : Callback<R> {
                        override fun onResponse(call: Call<R>, response: Response<R>) {
                            postValue(ApiResponse.create(response))
                        }

                        override fun onFailure(call: Call<R>, t: Throwable) {
                            postValue(ApiResponse.create(t))
                        }
                    })
                }
            }
        }
    }

    class Factory : CallAdapter.Factory() {
        override fun get(
            returnType: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            val rawType = getRawType(returnType)
            if (rawType != LiveData::class.java) {
                throw IllegalStateException("asdasdas")
            }
            val observableType = getParameterUpperBound(0, returnType as ParameterizedType)
            val rawObservableType = getRawType(observableType)
            if (rawObservableType != ApiResponse::class.java) {
                throw IllegalStateException("asdasdas")
            }
            if (observableType !is ParameterizedType) {
                throw IllegalStateException("asdasdas")
            }
            val bodyType = getParameterUpperBound(0, observableType)
            return LiveDataCallAdapter<String, String>(bodyType)
        }

    }
}