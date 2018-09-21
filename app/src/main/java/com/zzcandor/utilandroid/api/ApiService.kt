package com.zzcandor.utilandroid.api

import com.squareup.moshi.Json
import com.zzcandor.utilandroid.mvp.model.bean.ProjectTreeBean
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {


    /**
     * 项目数据
     * http://www.wanandroid.com/project/tree/json
     */
    @GET("project/tree/json")
    fun getProjectTree(): Observable<HttpResult<List<ProjectTreeBean>>>


    data class HttpResult<T>(@Json(name = "data") val data: T,
                             @Json(name = "errorCode") val errorCode: Int,
                             @Json(name = "errorMsg") val errorMsg: String)

}