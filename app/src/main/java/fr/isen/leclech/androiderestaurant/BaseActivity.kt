package fr.isen.leclech.androiderestaurant

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import fr.isen.leclech.androiderestaurant.Basket.Basket
import fr.isen.leclech.androiderestaurant.Basket.BasketActivity
import fr.isen.leclech.androiderestaurant.databinding.ActivityHomeBinding

open class BaseActivity: AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        val menuView= menu?.findItem(R.id.basket)?.actionView
        val countText=menuView?.findViewById(R.id.itemCount) as? TextView
        val count = getItemsCount()
        countText?.isVisible=count>0
        Log.d("Count", count.toString())
        countText?.text=count.toString()

        menuView?.setOnClickListener {
            if (count > 0) {
                val intent = Intent(this, BasketActivity::class.java)
                startActivity(intent)
            }else{
                Snackbar.make(menuView, R.string.emptycart, Snackbar.LENGTH_LONG).show()
            }
        }

        return true
    }

    override fun onResume() {
        super.onResume()
        invalidateOptionsMenu()
    }

    fun getItemsCount(): Int {
        val sharedPreferences = getSharedPreferences(Basket.USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(Basket.ITEMS_COUNT, 0)
    }
}