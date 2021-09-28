package com.realize.net

import retrofit2.Response

/**
 * Created by SongWenjun
 * 2021/9/12
 *       ∩ _ ∩
 *      (??ω??)っ一? ?? ???
 *        っ 丿         ? ????
 *     乚― J               ???
 * This class is ...
 */
sealed class ApiResponse<T> {
    companion object {
        fun create(t: Throwable) = BaseResponeBean().apply { error = t }

        fun <T> create(response: Response<T>): BaseResponeBean {
            return response.body() as BaseResponeBean
        }
    }
}

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()