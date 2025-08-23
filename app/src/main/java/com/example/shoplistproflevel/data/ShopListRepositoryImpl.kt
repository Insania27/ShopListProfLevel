package com.example.shoplistproflevel.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoplistproflevel.domain.ShopListItem
import com.example.shoplistproflevel.domain.ShopListRepository
import kotlin.random.Random

object ShopListRepositoryImpl: ShopListRepository {

    private val shopList = sortedSetOf<ShopListItem>({o1, o2 -> o1.id.compareTo(o2.id)})

    private val shopListLD = MutableLiveData<List<ShopListItem>>()

    private var autoincrementId = 0

    init {
        for (i in 1..1000){
            val item = ShopListItem("Name $i", i, Random.nextBoolean())
            addShopListItem(item)
        }
    }

    override fun getShopList(): LiveData<List<ShopListItem>> {
        return shopListLD
    }

    override fun getShopListItemById(shopListItemId: Int): ShopListItem {
        return shopList.find { it.id == shopListItemId }
            ?: throw RuntimeException("Element with id $shopListItemId is not found")
    }

    override fun editShopListItem(shopListItem: ShopListItem) {
        val oldItem = getShopListItemById(shopListItem.id)
        shopList.remove(oldItem)
        addShopListItem(shopListItem)
    }

    override fun deleteShopListItem(shopListItem: ShopListItem) {
        shopList.remove(shopListItem)
        updateList()
    }

    override fun addShopListItem(shopListItem: ShopListItem) {
        if (shopListItem.id == ShopListItem.UNDEFINED_ID){
            shopListItem.id = autoincrementId++
        }
        shopList.add(shopListItem)
        updateList()
    }

    private fun updateList(){
        shopListLD.value = shopList.toList()
    }
}