package com.example.shoplistproflevel.domain

interface ShopListRepository {

    fun getShopList(): List<ShopListItem>

    fun getShopListItemById(shopListItemId: Int): ShopListItem

    fun editShopListItem(shopListItem: ShopListItem)

    fun deleteShopListItem(shopListItem: ShopListItem)

    fun addShopListItem(shopListItem: ShopListItem)

}