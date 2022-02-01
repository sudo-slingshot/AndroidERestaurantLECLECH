package fr.isen.leclech.androiderestaurant.Basket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.leclech.androiderestaurant.R
import fr.isen.leclech.androiderestaurant.databinding.ActivityBasketBinding

class BasketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBasketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadlist()
    }

    private fun loadlist(){
        val items=Basket.getBasket(this).items
        binding.recyclerview.layoutManager=LinearLayoutManager(this)
        binding.recyclerview.adapter=BasketAdapter(items)
    }
}