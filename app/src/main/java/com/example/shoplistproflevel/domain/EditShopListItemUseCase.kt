package com.example.shoplistproflevel.domain

class EditShopListItemUseCase(private val shopListRepository: ShopListRepository) {

    fun editShopListItem(shopListItem: ShopListItem) {
        shopListRepository.editShopListItem(shopListItem)
    }

}