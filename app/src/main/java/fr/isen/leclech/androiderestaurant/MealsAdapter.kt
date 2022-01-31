package fr.isen.leclech.androiderestaurant;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView;
import fr.isen.leclech.androiderestaurant.databinding.CellMealBinding;


class MealsAdapter(private val entries: List<Dish>, val itemClickListener: (Dish) -> Unit): RecyclerView.Adapter<MealsAdapter.MealsViewHolder>() {
    class MealsViewHolder(binding:CellMealBinding): RecyclerView.ViewHolder(binding.root){
        val title=binding.mealTitle
        val layout = binding.root
        fun bind (meal:Dish){
            title.text=meal.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val binding = CellMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        val item = entries[position]
        //holder.title.text = item.name
        holder.layout.setOnClickListener {
            itemClickListener.invoke(item)
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int = entries.count()

}
