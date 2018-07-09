package hu.aut.android.kotlinshoppinglist.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.aut.android.kotlinshoppinglist.R
import hu.aut.android.kotlinshoppinglist.adapter.ShoppingAdapter.ViewHolder
import hu.aut.android.kotlinshoppinglist.data.ShoppingItem
import kotlinx.android.synthetic.main.row_item.view.*

class ShoppingAdapter : RecyclerView.Adapter<ViewHolder>() {
    private val items = mutableListOf<ShoppingItem>(
            ShoppingItem("milk", 200, false),
            ShoppingItem("battery", 500, false),
            ShoppingItem("pizza", 300, true)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.row_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = items[position].name
        holder.tvPrice.text = items[position].price.toString()
        holder.cbBought.isChecked = items[position].bought
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.tvName
        val tvPrice = itemView.tvPrice
        val cbBought = itemView.cbBought
    }
}