package fr.isen.leclech.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.leclech.androiderestaurant.databinding.ActivityMealsBinding
import org.json.JSONObject

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
        val adapter=MealsAdapter(fakentries) { selectedItem ->
            Log.d("Meals", "GG!")
        }
        binding.MealsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.MealsRecyclerView.adapter= adapter

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val menuchoice = intent.getSerializableExtra(HomeActivity.MEALTYPE) as? MealType ?:MealType.APPETIZERS
        binding.textView3.text=MealType.toString(menuchoice)
        makeRequest()
        loadMeals()
    }

    private fun makeRequest() {
        val queue = Volley.newRequestQueue(this)
        val url = NetworkConstants.BASE_URL + NetworkConstants.MENU
        val parameters = JSONObject()
        parameters.put(NetworkConstants.KEY_SHOP, NetworkConstants.SHOP)

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            parameters,
            {
                Log.d("volley", "${it.toString(2)}")
            },
            {
                Log.d("Volley error", "$it")
            }
        )

        queue.add(request)
    }


}