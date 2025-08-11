package com.example.shoplistproflevel.domain

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): List<ShopListItem> {
        return shopListRepository.getShopList()
    }

}