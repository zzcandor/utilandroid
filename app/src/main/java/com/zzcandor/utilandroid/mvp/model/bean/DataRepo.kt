package com.zzcandor.utilandroid.mvp.model.bean

import com.squareup.moshi.Json
import org.litepal.crud.LitePalSupport
import java.io.Serializable

/**
 * Created by chenxz on 2018/4/21.
 */
data class HttpResult<T>(@Json(name = "data") val data: T,
                         @Json(name = "errorCode") val errorCode: Int,
                         @Json(name = "errorMsg") val errorMsg: String)


// 项目
data class ProjectTreeBean(
        @Json(name = "children") val children: List<Any>,
        @Json(name = "courseId") val courseId: Int,
        @Json(name = "id") val id: Int,
        @Json(name = "name") val name: String,
        @Json(name = "order") val order: Int,
        @Json(name = "parentChapterId") val parentChapterId: Int,
        @Json(name = "visible") val visible: Int
)






