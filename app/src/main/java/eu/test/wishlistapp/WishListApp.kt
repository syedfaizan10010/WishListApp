package eu.test.wishlistapp

import android.app.Application
import eu.test.wishlistapp.Data.Graph

class WishListApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}