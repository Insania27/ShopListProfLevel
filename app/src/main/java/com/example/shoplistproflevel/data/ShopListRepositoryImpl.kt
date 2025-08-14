package com.example.shoplistproflevel.data

import com.example.shoplistproflevel.domain.ShopListItem
import com.example.shoplistproflevel.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {

    private val shopList = mutableListOf<ShopListItem>()

    private var autoincrementId = 0

    init {
        for (i in 1..10){
            val item = ShopListItem("Name $i", i, true)
            addShopListItem(item)
        }
    }

    override fun getShopList(): List<ShopListItem> {
        return shopList.toList()
    }

    override fun getShopListItemById(shopListItemId: Int): ShopListItem {
        return shopList.find { it.id == shopListItemId }
            ?: throw RuntimeException("Element with id $shopListItemId is not found")
    }

    override fun editShopListItem(shopListItem: ShopListItem) {
        val oldItem = getShopListItemById(shopListItem.id)
        shopList.remove(oldItem)
        shopList.add(shopListItem)
    }

    override fun deleteShopListItem(shopListItem: ShopListItem) {
        shopList.remove(shopListItem)
    }

    override fun addShopListItem(shopListItem: ShopListItem) {
        if (shopListItem.id == ShopListItem.UNDEFINED_ID){
            shopListItem.id == autoincrementId++
        }
        shopList.add(shopListItem)
    }
}