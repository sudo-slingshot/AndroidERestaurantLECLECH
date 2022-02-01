package fr.isen.leclech.androiderestaurant

import android.content.Context
import android.util.Log
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import fr.isen.leclech.androiderestaurant.Basket.Basket

open class BaseActivity: AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val menuView= menu?.findItem(R.id.basket)?.actionView
        val countText=menuView?.findViewById(R.id.itemCount) as? TextView
        val count = getItemsCount()
        countText?.isVisible=count>0
        Log.d("Count", count.toString())
        countText?.text=count.toString()
        return true
    }

    override fun onResume() {
        super.onResume()
        invalidateOptionsMenu()
    }

    private fun getItemsCount(): Int {
        val sharedPreferences = getSharedPreferences(Basket.USER_PREFERENCES_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getInt(Basket.ITEMS_COUNT, 0)
    }
}