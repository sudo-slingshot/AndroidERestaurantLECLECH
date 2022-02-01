package fr.isen.leclech.androiderestaurant.Basket

import fr.isen.leclech.androiderestaurant.Network.Dish
import java.io.Serializable

class Basket(val items: MutableList<BasketItem>): Serializable {
    fun addItem(item: Dish, quantity: Int) {

    }

    fun save() {

    }

    companion object {
        fun getBasket(): Basket {
            return Basket(mutableListOf())
        }
    }
}

class BasketItem(val dish: Dish, var quantity: Int): Serializable {}