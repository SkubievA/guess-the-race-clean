package skubyev.anton.guesstherace.model.repository.comments

import skubyev.anton.guesstherace.model.data.server.GeneralApi
import skubyev.anton.guesstherace.model.system.SchedulersProvider
import javax.inject.Inject

class CommentsRepository @Inject constructor(
        private val api: GeneralApi,
        private val schedulers: SchedulersProvider
) {

    fun getComments(idImage: Int) = api.getComments(idImage)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())

    fun addComment(
            message: String,
            idImage: Int,
            idAuthor: Int
    ) = api.addComment(
            message,
            idImage,
            idAuthor
    )
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
}