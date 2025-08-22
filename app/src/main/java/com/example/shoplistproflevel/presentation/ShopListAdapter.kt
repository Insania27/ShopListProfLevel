package com.example.shoplistproflevel.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplistproflevel.R
import com.example.shoplistproflevel.domain.ShopListItem

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    val list = listOf<ShopListItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_shop_disabled, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopListItem = list[position]
        holder.tvName.text = shopListItem.name
        holder.tvCount.text = shopListItem.count.toString()
        holder.itemView.setOnLongClickListener{
            true
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ShopItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

}