package com.example.shoplistproflevel.domain

class DeleteShopListItemUseCase(private val shopListRepository: ShopListRepository) {

    fun deleteShopListItem(shopListItem: ShopListItem){
        shopListRepository.deleteShopListItem(shopListItem)
    }

}