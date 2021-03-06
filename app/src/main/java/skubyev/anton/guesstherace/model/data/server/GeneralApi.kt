package skubyev.anton.guesstherace.model.data.server

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import skubyev.anton.guesstherace.entity.*

interface GeneralApi {
    companion object {
        const val API_PATH = "/android/determinace/backend"
    }

    @GET("$API_PATH/handler.php?type=registration")
    fun registration(
            @Query("login") login: String
    ): Single<ProfileResponse>

    @GET("$API_PATH/handler.php?type=test_login")
    fun testLogin(
            @Query("login") login: String
    ): Single<BaseResponse>

    @GET("$API_PATH/handler.php?type=appendrating")
    fun appendRating(
            @Query("guessed") guessed: Boolean
    ): Single<ProfileResponse>

    @GET("$API_PATH/handler.php?type=images")
    fun getImages(): Single<List<ImagesResponse>>

    @GET("handler.php?type=view_comment")
    fun getComments(
            @Query("id") id: Int
    ): Single<List<CommentResponse>>

    @GET("handler.php?type=add_comment")
    fun addComment(
            @Query("comment") comment: String,
            @Query("idImage") idImage: Int,
            @Query("idAuthor") idAuthor: Int
    ): Single<BaseResponse>

    @GET("$API_PATH/handler.php?type=profile")
    fun getProfile(): Single<ProfileResponse>

    @GET("$API_PATH/handler.php?type=rating")
    fun getUsersRating(): Single<List<RatingResponse>>
}