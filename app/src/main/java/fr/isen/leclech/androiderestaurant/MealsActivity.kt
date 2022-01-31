package fr.isen.leclech.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.leclech.androiderestaurant.databinding.ActivityMealsBinding

enum class MealType{
    APPETIZERS, MAIN_COURSES,DESSERTS;

    companion object {
        fun toString(mealChoice: MealType?): String {
            return when(mealChoice) {
                APPETIZERS -> "Entrées"
                MAIN_COURSES -> "Plats"
                DESSERTS -> "Desserts"
                else -> ""
            }
        }
    }
}

class MealsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealsBinding

    private fun loadMeals(){
        val fakentries = listOf<String>("Salade", "Boeuf", "Glace")
        val adapter=MealsAdapter(fakentries)
        binding.MealsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.MealsRecyclerView.adapter= adapter

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val menuchoice = intent.getSerializableExtra(HomeActivity.MEALTYPE) as? MealType ?:MealType.APPETIZERS
        binding.textView3.text=MealType.toString(menuchoice)
        loadMeals()
    }


}