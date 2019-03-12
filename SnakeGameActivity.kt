package com.example.cs193a_hw6_devansh

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View

import kotlinx.android.synthetic.main.activity_snake_game.*
import kotlinx.android.synthetic.main.content_snake_game.*

class SnakeGameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snake_game)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        mycanvas.startGame()
    }

    fun upClick (view: View)
    {
        mycanvas.moveUp()
    }

    fun downClick (view: View)
    {
        mycanvas.moveDown()
    }

    fun rightClick (view: View)
    {
        mycanvas.moveRight()

    }

    fun leftClick (view: View)
    {
        mycanvas.moveLeft()
    }


    fun mainmenu (view: View)
    {
        mycanvas.stopGame()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
