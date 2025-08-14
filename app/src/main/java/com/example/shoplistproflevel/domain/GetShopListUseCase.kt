package com.example.shoplistproflevel.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase(private val shopListRepository: ShopListRepository) {

    fun getShopList(): LiveData<List<ShopListItem>> {
        return shopListRepository.getShopList()
    }

}