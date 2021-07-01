package edu.utxaustin.counter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    //call textViews to be used everywhere within the class
    //they are defined in onCreate
    private lateinit var mainNumTextView: TextView
    private lateinit var randomPlusMinusTextView: TextView
    private lateinit var triesTextView: TextView

    //call editedText
    private lateinit var targetEditedText: EditText

    //set target
    private var targetInt: Int? = null
    private var triesCounter: Int = 0
    private var currentState: Int = 0

    //set img view
    private lateinit var diceImgView:  ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //call buttons
        val submitTargetButton:Button = findViewById(R.id.main_activity_btn_submit)
        val randomButton:Button = findViewById(R.id.main_activity_btn_random_num)
        val plusButton:Button = findViewById(R.id.btn_plus)
        val minusButton:Button = findViewById(R.id.btn_minus)

        // set clickListeners for each button
        submitTargetButton.setOnClickListener{setTargetNumber()}
        randomButton.setOnClickListener{setRandomNumber()}
        plusButton.setOnClickListener{plusANumber()}
        minusButton.setOnClickListener{minusANumber()}

        //set textViews initilized lateinit
        mainNumTextView = findViewById(R.id.activity_main_text_view_number)
        randomPlusMinusTextView = findViewById(R.id.plus_minus_text_view)
        triesTextView = findViewById(R.id.textViewTries)

        //set EditedText initilized lateinit
        targetEditedText=findViewById(R.id.main_activity_edited_txt_num_input)

        //get the img view
        diceImgView = findViewById(R.id.main_activity_img_view_dice)
    }

    private fun setTargetNumber(){
        targetInt=targetEditedText.text.toString().toIntOrNull()
        resetState()
    }


    private fun setRandomNumber(){
        //generate a rand num from -100 to 100 included
        val randNum =(-10..10).random()
        currentState=randNum
        displayCurrentState()
        resetState()

    }
    private fun plusANumber(){
        updateTries()

        val plusARandNum =(0..10).random()
        currentState = currentState + plusARandNum
        randomPlusMinusTextView.text = "+".plus(plusARandNum.toString())
        displayCurrentState()
        checkWin()

    }
    private fun minusANumber(){
        updateTries()

        val minusARandNum =(-10..0).random()
        currentState = currentState + minusARandNum
        randomPlusMinusTextView.text = minusARandNum.toString()
        displayCurrentState()
        checkWin()
    }

    private fun displayCurrentState(){
        mainNumTextView.text=currentState.toString()
    }

    private fun updateTries(){
        triesCounter = triesCounter+1
        triesTextView.text =triesCounter.toString()
    }

    @SuppressLint("ResourceType")
    private fun resetState(){
        triesCounter=0
        triesTextView.text =triesCounter.toString()
        //randomPlusMinusTextView.text =  "Plus \uD83C\uDFB0 Minus \uD83C\uDFB1"
        randomPlusMinusTextView.text=getString(R.string.plus_minus_text_view_placeholder)
        diceImgView.setImageResource(R.drawable.ic_dice)
    }

    private fun checkWin(){
        if  (currentState == targetInt) {
            diceImgView.setImageResource(R.mipmap.thums_up_foreground)
            //resetState()
        }
    }


}


