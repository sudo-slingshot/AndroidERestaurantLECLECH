package fr.isen.leclech.androiderestaurant.Basket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.leclech.androiderestaurant.R
import fr.isen.leclech.androiderestaurant.databinding.ActivityBasketBinding
import fr.isen.leclech.androiderestaurant.Basket.Basket

class BasketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBasketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadlist()
    }


    private fun loadlist(){
        val basket = Basket.getBasket(this)
        val items=basket.items
        binding.recyclerview.layoutManager=LinearLayoutManager(this)
        binding.recyclerview.adapter=BasketAdapter(items) {
            basket.removeItem(it)
            basket.save(this)
            loadlist()
        }
    }
}