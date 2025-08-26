package com.example.shoplistproflevel.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.shoplistproflevel.R
import com.example.shoplistproflevel.domain.ShopListItem

class ShopListAdapter: RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var count = 0
    var shopList = listOf<ShopListItem>()
        set(value) {
            val callback = ShopListDiffCallback(shopList, value) // сравнение

            val diffResult = DiffUtil.calculateDiff(callback) // определение изменений

            diffResult.dispatchUpdatesTo(this) // сообщение адаптеру о необходимых
                                                        // изменениях, методы он вызывает сам
            field = value
        }

    var onShopItemLongClickListener: ((ShopListItem) -> Unit)? = null

    var onShopItemClickListener: ((ShopListItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType){
            VIEW_TYPE_DISABLED -> R.layout.item_shop_disabled
            VIEW_TYPE_ENABLED -> R.layout.item_shop_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false
        )
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        Log.d("ShopListAdapter", "onCreateViewHolder, count: ${++count}")
        val shopListItem = shopList[position]
        holder.tvName.text = shopListItem.name
        holder.tvCount.text = shopListItem.count.toString()
        holder.itemView.setOnLongClickListener{
            onShopItemLongClickListener?.invoke(shopListItem) // invoke нужен, тк может быть null
            true
        }
        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(shopListItem)
        }
    }

    // метод вызывается для view, которые исчезают с экрана
    override fun onViewRecycled(viewHolder: ShopItemViewHolder) {
        super.onViewRecycled(viewHolder)
        viewHolder.tvName.text = ""
        viewHolder.tvCount.text = ""
        viewHolder.tvName.setTextColor(ContextCompat.getColor(
            viewHolder.itemView.context,
            android.R.color.white
        ))
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    // метод для определения типа view
    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
        return if (item.enabled) VIEW_TYPE_ENABLED
        else VIEW_TYPE_DISABLED
    }


    class ShopItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCount = view.findViewById<TextView>(R.id.tv_count)
    }

    companion object{
        const val VIEW_TYPE_DISABLED = 1
        const val VIEW_TYPE_ENABLED = 2

        const val MAX_POOL_SIZE = 30
    }

}