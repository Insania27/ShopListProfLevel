package com.example.shoplistproflevel.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shoplistproflevel.domain.ShopListItem


// 2 способ для сравнения элементов, более эффективен, тк работает в фоновом потоке

class ShopItemDiffCallback: DiffUtil.ItemCallback<ShopListItem>() {
    override fun areItemsTheSame(
        oldItem: ShopListItem,
        newItem: ShopListItem
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ShopListItem,
        newItem: ShopListItem
    ): Boolean {
        return oldItem == newItem
    }
}