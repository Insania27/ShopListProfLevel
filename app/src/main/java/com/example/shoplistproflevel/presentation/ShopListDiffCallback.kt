package com.example.shoplistproflevel.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shoplistproflevel.domain.ShopListItem


// класс для сравнения старого и нового списков
class ShopListDiffCallback(
    private val oldList: List<ShopListItem>,
    private val newList: List<ShopListItem>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}