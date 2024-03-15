package eu.test.wishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.test.wishlistapp.Data.Graph
import eu.test.wishlistapp.Data.Graph.wishRepo
import eu.test.wishlistapp.Data.Wish
import eu.test.wishlistapp.Data.WishRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class WishViewModel(
    wishRepo: WishRepo = Graph.wishRepo
):ViewModel() {
    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onWishTitleChanged(newString: String){
        wishTitleState = newString
    }
    fun onWishDescriptionChanged(newString: String){
        wishDescriptionState = newString
    }

    lateinit var getAllWishes: Flow<List<Wish>>
    init {
        viewModelScope.launch {
            getAllWishes = wishRepo.getWishes()
        }
    }

        fun addWish(wish: Wish){
            viewModelScope.launch(Dispatchers.IO) {
                wishRepo.addWish(wish)
            }
        }
        fun updateWish(wish: Wish){
            viewModelScope.launch(Dispatchers.IO) {
                wishRepo.updateWish(wish)
            }
        }

        fun getWishById(id:Long):Flow<Wish>{
            return wishRepo.getWishById(id)
        }
        fun deleteWish(wish: Wish){
            viewModelScope.launch(Dispatchers.IO) {
                wishRepo.deleteWish(wish)
            }
        }
}
