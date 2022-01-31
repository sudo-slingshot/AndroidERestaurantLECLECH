package fr.isen.leclech.androiderestaurant;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView;
import fr.isen.leclech.androiderestaurant.databinding.CellMealBinding;

class MealsAdapter(private val entries: List<String>): RecyclerView.Adapter<MealsAdapter.MealsViewHolder>() {
    class MealsViewHolder(binding:CellMealBinding): RecyclerView.ViewHolder(binding.root){
        val title=binding.mealTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val binding = CellMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.title.text = entries[position]
    }

    override fun getItemCount(): Int = entries.count()

}
