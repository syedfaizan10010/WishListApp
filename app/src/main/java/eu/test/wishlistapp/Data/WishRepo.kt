package eu.test.wishlistapp.Data

import kotlinx.coroutines.flow.Flow


class WishRepo(private val wishDAO: WishDAO) {
    suspend fun addWish(wish: Wish){
        wishDAO.addWish(wish)
    }
    fun getWishes(): Flow<List<Wish>> = wishDAO.getAllWish()

    fun getWishById(id: Long) : Flow<Wish>{
        return  wishDAO.getWishById(id)
    }

    suspend fun updateWish(wish: Wish){
        wishDAO.updateWish(wish)
    }
    suspend fun deleteWish(wish: Wish){
        wishDAO.deleteWish(wish)
    }
}