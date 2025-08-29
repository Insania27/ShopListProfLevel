package com.example.shoplistproflevel.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _shopItem = MutableLiveData<ShopListItem>()
    val shopItem: LiveData<ShopListItem>
        get() = _shopItem

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun getShopItem(shopListItem: ShopListItem) {
        val item = getShopItemUseCase.getShopListItemById(shopListItem.id)
        _shopItem.value = item
    }

    fun addShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsAreValid = validateInput(name, count)
        if (fieldsAreValid) {
            val shopItem = ShopListItem(name, count, true)
            addShopListItemUseCase.addShopListItem(shopItem)
            finishWork()

        }
    }

    fun editShopItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsAreValid = validateInput(name, count)
        if (fieldsAreValid) {
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count)
                editShopListItemUseCase.editShopListItem(item)
                finishWork()
            }
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
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }

}