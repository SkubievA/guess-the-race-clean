package skubyev.anton.guesstherace.model.data.storage

import io.requery.Entity
import io.requery.Generated
import io.requery.Key
import io.requery.Persistable

@Entity
interface Notification: Persistable {
    @get:Key
    @get:Generated
    var id: Int
    var title: String
    var message: String
    var show: Boolean
}