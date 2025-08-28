package com.example.shoplistproflevel.presentation

import androidx.lifecycle.ViewModel
import com.example.shoplistproflevel.data.ShopListRepositoryImpl
import com.example.shoplistproflevel.domain.AddShopListItemUseCase
import com.example.shoplistproflevel.domain.EditShopListItemUseCase
import com.example.shoplistproflevel.domain.GetShopListItemByIdUseCase
import com.example.shoplistproflevel.domain.ShopListItem
import com.example.shoplistproflevel.domain.ShopListRepository

class ShopItemViewModel : ViewModel() {

    val repository = ShopListRepositoryImpl

    val getShopItemUseCase = GetShopListItemByIdUseCase(repository)
    val addShopListItemUseCase = AddShopListItemUseCase(repository)
    val editShopListItemUseCase = EditShopListItemUseCase(repository)

    fun getShopItem(shopListItem: ShopListItem) {
        val item = getShopItemUseCase.getShopListItemById(shopListItem.id)
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsAreValid = validateInput(name, count)
        if (fieldsAreValid) {
            val shopItem = ShopListItem(name, count, true)
            addShopListItemUseCase.addShopListItem(shopItem)
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsAreValid = validateInput(name, count)
        if (fieldsAreValid) {
            val shopItem = ShopListItem(name, count, true)
            editShopListItemUseCase.editShopListItem(shopItem)
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        // TODO: show error input name
        if (name.isBlank()) result = false
        // TODO: show error input count
        if (count <= 0) result = false
        return result
    }

}