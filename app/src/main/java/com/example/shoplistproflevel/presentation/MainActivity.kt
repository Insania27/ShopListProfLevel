package com.example.shoplistproflevel.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.shoplistproflevel.R
import com.example.shoplistproflevel.domain.ShopListItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var llShopList: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        llShopList = findViewById(R.id.ll_shop_list)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this){
            showList(it)
        }
    }

    private fun showList(list: List<ShopListItem>){
        llShopList.removeAllViews()
        for (shopListItem in list){
            val layoutId = if (shopListItem.enabled){
                R.layout.item_shop_enabled
            } else {
                R.layout.item_shop_disabled
            }
            val view = LayoutInflater
                .from(this)
                .inflate(layoutId, llShopList, false)
            val tvName = view.findViewById<TextView>(R.id.tv_name)
            val tvCount = view.findViewById<TextView>(R.id.tv_count)
            tvName.text = shopListItem.name
            tvCount.text = shopListItem.count.toString()
            view.setOnLongClickListener {
                viewModel.changeEnableState(shopListItem)
                true
            }
            llShopList.addView(view)
        }
    }
}