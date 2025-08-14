package com.example.shoplistproflevel.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun getShopList(): LiveData<List<ShopListItem>>

    fun getShopListItemById(shopListItemId: Int): ShopListItem

    fun editShopListItem(shopListItem: ShopListItem)

    fun deleteShopListItem(shopListItem: ShopListItem)

    fun addShopListItem(shopListItem: ShopListItem)

}