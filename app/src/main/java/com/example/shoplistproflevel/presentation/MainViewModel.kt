package com.example.shoplistproflevel.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoplistproflevel.data.ShopListRepositoryImpl
import com.example.shoplistproflevel.domain.DeleteShopListItemUseCase
import com.example.shoplistproflevel.domain.EditShopListItemUseCase
import com.example.shoplistproflevel.domain.GetShopListUseCase
import com.example.shoplistproflevel.domain.ShopListItem

class MainViewModel: ViewModel() {

    private val repository = ShopListRepositoryImpl

    private val getShopListUseCase = GetShopListUseCase(repository)
    private val deleteShopListItemUseCase = DeleteShopListItemUseCase(repository)
    private val editShopListItemUseCase = EditShopListItemUseCase(repository)

    val shopList = MutableLiveData<List<ShopListItem>>()

    fun getShopList(){
        val list = getShopListUseCase.getShopList()
        shopList.value = list
    }

    fun deleteShopListItem(shopListItem: ShopListItem){
        deleteShopListItemUseCase.deleteShopListItem(shopListItem)
        getShopList()
    }

    fun changeEnableState(shopListItem: ShopListItem){
        val newItem = shopListItem.copy(enabled = !shopListItem.enabled)
        editShopListItemUseCase.editShopListItem(newItem)
        getShopList()
    }

}