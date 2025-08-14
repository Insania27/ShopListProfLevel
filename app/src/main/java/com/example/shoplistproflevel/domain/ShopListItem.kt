package com.example.shoplistproflevel.domain

data class ShopListItem(
    val name: String,
    val count: Int,
    var enabled: Boolean,
    var id: Int = UNDEFINED_ID,
){
    companion object { const val UNDEFINED_ID = -1 }
}
