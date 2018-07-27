package hu.autsoft.example.dogcatalog.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import hu.autsoft.example.dogcatalog.R
import hu.autsoft.example.dogcatalog.data.Dog
import kotlinx.android.synthetic.main.item_dog.view.*

class DogAdapter : RecyclerView.Adapter<DogAdapter.DogViewHolder>() {

    private val dogs = mutableListOf(
            Dog("first"),
            Dog("second"),
            Dog("third")
    )

    var listener: Listener? = null

    override fun getItemCount() = dogs.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val dog = dogs[position]

        holder.tvBreed.text = dog.breed
    }

    fun setDogs(newDogs: List<Dog>) {
        dogs.clear()
        dogs.addAll(newDogs)
        notifyDataSetChanged()
    }

    inner class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvBreed: TextView = itemView.tvBreed

        init {
            itemView.setOnClickListener {
                listener?.onDogSelected(dogs[adapterPosition].breed)
            }
        }
    }

    interface Listener {
        fun onDogSelected(breed: String)
    }

}
