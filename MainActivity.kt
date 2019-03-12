package com.example.cs193a_hw6_devansh

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun playGame (view: View)
    {
        val intent = Intent (this, SnakeGameActivity::class.java)
        startActivity(intent)
    }
}
