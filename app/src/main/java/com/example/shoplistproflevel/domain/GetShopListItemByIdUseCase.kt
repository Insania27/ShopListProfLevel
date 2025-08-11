package com.example.shoplistproflevel.domain

class GetShopListItemByIdUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopListItemById(shopListItemId: Int): ShopListItem {
        return shopListRepository.getShopListItemById(shopListItemId)
    }

}