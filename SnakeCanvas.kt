package com.example.cs193a_hw6_devansh

import android.content.Context
import android.graphics.*
import android.telephony.gsm.GsmCellLocation
import android.util.*
import android.view.*
import com.example.cs193a_hw6_devansh.R
import java.util.*
import stanford.androidlib.graphics.*
import stanford.androidlib.util.RandomGenerator
import kotlin.collections.ArrayList

class SnakeCanvas(context: Context, attrs: AttributeSet)
    : GCanvas(context, attrs)
{
    private lateinit var snakeHead: GSprite
    private lateinit var food: GSprite
    private lateinit var snakePieces: ArrayList<GSprite>
    companion object {
        // frames per second of animation
        private const val FRAMES_PER_SECOND = 3
    }
    override fun init()
    {
        var snakeHeadImage = BitmapFactory.decodeResource(resources, R.drawable.snakehead)
        snakeHeadImage = snakeHeadImage.scaleToWidth(this.width/15f)
        snakeHeadImage = snakeHeadImage.scaleToHeight(this.width/15f)
        snakeHead = GSprite(snakeHeadImage)
        snakeHead.setAcceleration(0f,0f)
        snakeHead.setVelocity(15f,0f)

        var foodImage = BitmapFactory.decodeResource(resources,R.drawable.food)
        foodImage = foodImage.scaleToWidth(this.width/15f)
        foodImage = foodImage.scaleToHeight(this.width/15f)

        food = GSprite(foodImage)

    }

    private fun doCollisions ()
    {
        if (snakeHead.collidesWith(food))
        {
            var snakeBodyImage = BitmapFactory.decodeResource(resources, R.drawable.snakebody)
            var snakeBodyPart = GSprite(snakeBodyImage)
            snakePieces.add(snakeBodyPart)
            randomLocationSetter(food)
        }
        if (!snakeHead.isInBounds) stopGame()
        if (collisionChecker(snakeHead)) stopGame()
    }

    private fun randomLocationSetter (sprite: GSprite)
    {
        val rgen = RandomGenerator.getInstance();

        var x = rgen.nextFloat(this.width/1f)
        var y = rgen.nextFloat(this.width/1f)

        while (!sprite.isInBounds || !sprite.collidesWith(snakeHead) || !collisionChecker(sprite))
        {
            sprite.setLocation(x,y)
            while (x % 7.5f == 0f && y % 7.5f == 0f)
            {
                x = rgen.nextFloat(this.width/1f)
                y = rgen.nextFloat(this.width/1f)
                sprite.setLocation(x,y)
            }
        }
    }

    private fun collisionChecker (sprite: GSprite): Boolean
    {
        var b = false
        val x = snakePieces.size-1

        for (i in 0..x)
        {
            if (sprite.collidesWith(snakePieces[i]))
            {
                b = true
                break
            }
        }
        return b
    }

    fun partLocationSetter ()
    {
        val n = snakePieces.size - 1
        var x = snakeHead.location
        for (i in 0..n)
        {
            snakePieces[i].setLocation(x)
            x = snakePieces[i].location
        }
    }

    fun moveLeft ()
    {
        snakeHead.setVelocity(-this.width/15f,0f)
        animate(FRAMES_PER_SECOND) {
            tick()
        }
    }

    fun moveRight ()
    {
        snakeHead.setVelocity(this.width/15f, 0f)
    }

    fun moveUp ()
    {
        snakeHead.setVelocity(0f, -this.width/15f)
    }

    fun moveDown ()
    {
        snakeHead.setVelocity(0f, this.width/15f)
    }

    fun startGame ()
    {
        animate(FRAMES_PER_SECOND) {
            tick()
        }
    }

    private fun tick ()
    {
        snakeHead.update()
        doCollisions()
        partLocationSetter()
    }

    fun stopGame() {
        animationStop()
    }

}