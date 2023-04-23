package com.cl.mykowel.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.cl.mykowel.R

class AddNewItemBazarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_item_bazar)
        supportActionBar?.title = "Новий товар"

    }
}