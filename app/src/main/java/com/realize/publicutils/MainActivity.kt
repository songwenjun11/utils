package com.realize.publicutils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.realize.router_note.Router
import com.realize.router_note.RouterManager

@Router("app/MainActivity")
open class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RouterManager.log()
    }
}