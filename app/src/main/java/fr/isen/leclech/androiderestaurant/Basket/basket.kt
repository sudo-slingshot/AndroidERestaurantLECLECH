package fr.isen.leclech.androiderestaurant.Basket

import fr.isen.leclech.androiderestaurant.Network.Dish
import java.io.File
import java.io.Serializable
import android.content.Context
import com.google.gson.GsonBuilder

class Basket(val items: MutableList<BasketItem>): Serializable {
    fun addItem(item: Dish, quantity: Int) {
        val existingItem = items.firstOrNull { it.dish.name==item.name }
        existingItem?.let {
            existingItem.quantity+=quantity
        }?: kotlin.run { items.add(BasketItem(item,quantity)) }
    }

    fun save(context: Context) {
        val jsonFile = File(context.cacheDir.absolutePath + BASKET_FILE)
        jsonFile.writeText(GsonBuilder().create().toJson(this))
    }


    companion object {
        fun getBasket(): Basket {
            return Basket(mutableListOf())
        }
        const val BASKET_FILE="basket.json"
    }
}

class BasketItem(val dish: Dish, var quantity: Int): Serializable {}