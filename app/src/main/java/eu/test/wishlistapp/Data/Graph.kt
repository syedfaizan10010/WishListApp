package eu.test.wishlistapp.Data

import android.content.Context
import androidx.room.Room

object Graph  {
    lateinit var database:WishRoomDB //promised to be initialized before it used and hence avoids null value exception

    val wishRepo by lazy { //Lazy initializaiton is done whenever it is required - only once it is accessed
        WishRepo(wishDAO = database.wishDAO())
    }

    fun provide(context: Context){
        database = Room.databaseBuilder(context,WishRoomDB::class.java, "wishlist.db").build()
    }
}