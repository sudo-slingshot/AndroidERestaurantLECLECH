package fr.isen.leclech.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.leclech.androiderestaurant.databinding.ActivityDetailsBinding
import fr.isen.leclech.androiderestaurant.Dish

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var currentDish: Dish? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        currentDish = intent.getSerializableExtra(MealsActivity.SELECTED_ITEM) as? Dish
        showcontent()
    }

    private fun showcontent() {
        binding.title.text = currentDish?.name
        binding.ingredients.text = currentDish?.ingredients?.map{ it.name }?.joinToString(", ")
    }
}