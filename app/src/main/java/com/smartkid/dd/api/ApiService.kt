package com.smartkid.dd.api

import com.smartkid.dd.activity.ui.category.helper.CategoryHelper
import com.smartkid.dd.activity.ui.main.tabs.helper.EducationGamesAnimalHelper
import com.smartkid.dd.activity.ui.main.tabs.models.Animal
import com.smartkid.dd.activity.ui.main.tabs.models.Video
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("categories")
    suspend fun getCategories(): Response<MutableList<CategoryHelper>>

    @GET("gameAnimal/{id}")
    suspend fun getGameAnimalByIdCategory(@Path("id") id : String?): Response<Animal>

    @GET("video-categorie/{idCategorie}")
    suspend fun getVideo(@Path("idCategorie") idCategorie: String?): Response<MutableList<Video>>

    /*@GET("comments")
    suspend fun getCommentsByPost(@Query("postId") postId : Int): Response<MutableList<Comment>>*/

    /*@POST("posts")
    suspend fun createPost(@Body post: Post): Response<Post>*/
}