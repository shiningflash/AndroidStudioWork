package com.example.diceroller

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.example.diceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // apply data binding
    private lateinit var binding : ActivityMainBinding

    // Instance of MyName data class
    private val myName : MyName = MyName("", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = myName

        binding.nicknameBtn.setOnClickListener {
            nextStep(it)
        }

        binding.rollButton.setOnClickListener {
            rollDice(it)
        }
    }

    private fun nextStep(view : View) {
        hideKeyboard(view)
        binding.apply {
            val text : String = "Hello! " + binding.nicknameEt.text.toString()
            nicknameTv.text = text
            myName?.nickname = text
            invalidateAll()
            nicknameTv.visibility = View.VISIBLE
            nicknameLl1.visibility = View.GONE
            nicknameLl2.visibility = View.VISIBLE
        }
    }

    private fun rollDice(view : View) {
        val randInt = java.util.Random().nextInt(6) + 1
        val drawableResource = when (randInt) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        val text : String = "You got " + randInt
        binding.apply {
            resultTv.text = text
            invalidateAll()
            resultTv.visibility = View.VISIBLE
            diceImage.setImageResource(drawableResource)
        }
    }

    // Hide the Keyboard
    fun hideKeyboard(view : View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}