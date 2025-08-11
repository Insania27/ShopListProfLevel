package com.example.shoplistproflevel.domain

class AddShopListItemUseCase(private val shopListRepository: ShopListRepository) {

    fun addShopListItem(shopListItem: ShopListItem) {
        shopListRepository.addShopListItem(shopListItem)
    }

}