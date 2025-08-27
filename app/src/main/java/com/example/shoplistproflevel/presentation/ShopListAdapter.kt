package com.example.shoplistproflevel.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoplistproflevel.R
import com.example.shoplistproflevel.domain.ShopListItem

class ShopListAdapter : ListAdapter<ShopListItem, ShopItemViewHolder>(
    ShopItemDiffCallback()
) {

    var onShopItemLongClickListener: ((ShopListItem) -> Unit)? = null

    var onShopItemClickListener: ((ShopListItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout = when (viewType) {
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
        val shopListItem = getItem(position)
        holder.tvName.text = shopListItem.name
        holder.tvCount.text = shopListItem.count.toString()
        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(shopListItem) // invoke нужен, тк может быть null
            true
        }
        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(shopListItem)
        }
    }

    // метод для определения типа view
    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.enabled) VIEW_TYPE_ENABLED
        else VIEW_TYPE_DISABLED
    }

    companion object {
        const val VIEW_TYPE_DISABLED = 1
        const val VIEW_TYPE_ENABLED = 2

        const val MAX_POOL_SIZE = 30
    }

}