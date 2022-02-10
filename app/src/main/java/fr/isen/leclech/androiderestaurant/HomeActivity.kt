package fr.isen.leclech.androiderestaurant

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import fr.isen.leclech.androiderestaurant.Basket.BasketActivity
import fr.isen.leclech.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.appetizers.setOnClickListener {
            //Toast.makeText(this, "Vous avez sélectionné : Menu d'entrées ", Toast.LENGTH_SHORT).show()
            changeActivity(MealType.APPETIZERS)
            Log.e("Closure tag", "Home Activity Closed")
        }

        binding.mainCourses.setOnClickListener {
            //Toast.makeText(this, "Vous avez sélectionné : Menu des plats principaux", Toast.LENGTH_SHORT).show()
            changeActivity(MealType.MAIN_COURSES)
            Log.e("Closure tag", "Home Activity Closed")
        }

        binding.desserts.setOnClickListener {
            //Toast.makeText(this, "Vous avez sélectionné : Menu des desserts", Toast.LENGTH_SHORT).show()
            changeActivity(MealType.DESSERTS)
            Log.e("Closure tag", "Home Activity Closed")
        }

        binding.basket.setOnClickListener(){
            val count= getItemsCount()
            if (count==0) {
                Snackbar.make(binding.root, R.string.emptycart, Snackbar.LENGTH_LONG).show()
            }else {
                val intent = Intent(this, BasketActivity::class.java)
                startActivity(intent)
            }
        }

        binding.mapsbutton.setOnClickListener(){
            // Create a Uri from an intent string. Use the result to create an Intent.
            val gmmIntentUri = Uri.parse("https://www.google.com/maps/place/ISEN+Yncr%C3%A9a+M%C3%A9diterran%C3%A9e+-+Campus+de+Toulon/@43.1206241,5.9374718,17z/data=!3m1!4b1!4m5!3m4!1s0x12c91b0a44cc26c9:0x30eab5841931dc29!8m2!3d43.1206202!4d5.9396605")

            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            // Make the Intent explicit by setting the Google Maps package
            mapIntent.setPackage("com.google.android.apps.maps")

            // Attempt to start an activity that can handle the Intent
            startActivity(mapIntent)
        }

    }

    private fun changeActivity (category: MealType){
        val changePage = Intent(this, MealsActivity::class.java)
        changePage.putExtra(MEALTYPE, category)
        Log.i("INFO", "End of HomeActivity")
        startActivity(changePage)
    }
    companion object{
        const val MEALTYPE= "MEALTYPE"
    }
}