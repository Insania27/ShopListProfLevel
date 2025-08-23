package com.example.shoplistproflevel.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplistproflevel.R
import com.example.shoplistproflevel.domain.ShopListItem

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopListItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_shop_disabled, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopListItem = shopList[position]
        val status = if (shopListItem.enabled) {
            "Active"
        } else {
            "Not active"
        }
        holder.tvName.text = shopListItem.name
        holder.tvCount.text = shopListItem.count.toString()
        holder.itemView.setOnLongClickListener{
            true
        }
        if (shopListItem.enabled){
            holder.tvName.text = "${shopListItem.name} $status"
            holder.tvCount.text = shopListItem.count.toString()
            holder.tvName.setTextColor(ContextCompat.getColor(holder.itemView.context, android.R.color.holo_red_light))
        }
    }

    override fun onViewRecycled(viewHolder: ShopItemViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.tvName.text = ""
        viewHolder.tvCount.text = ""
        viewHolder.tvName.setTextColor(ContextCompat.getColor(viewHolder.itemView.context, android.R.color.white))
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    class ShopItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

}