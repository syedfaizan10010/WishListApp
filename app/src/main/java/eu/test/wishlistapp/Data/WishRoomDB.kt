package eu.test.wishlistapp.Data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Wish::class],
    version =1,
    exportSchema = false
)
abstract class WishRoomDB: RoomDatabase() {

    abstract fun wishDAO() :WishDAO
}