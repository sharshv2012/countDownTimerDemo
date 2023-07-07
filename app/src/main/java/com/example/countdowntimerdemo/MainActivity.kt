package com.example.countdowntimerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast



class MainActivity : AppCompatActivity() {
    private var countDownTimer : CountDownTimer? = null//variable for timer
    private var timeDuration : Long = 60000 //Duration of timer in ms
    private var pauseOffSet : Long = 0 // pauseOffset = timeDuration - TimeLeft
    private val tvTimer:TextView = findViewById(R.id.tvTimer)
    private val btnStart:Button = findViewById(R.id.button)
    private val btnPause:Button = findViewById(R.id.button2)
    private val btnStop:Button = findViewById(R.id.button3)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvTimer.text = (timeDuration/1000).toString()
        btnStart.setOnClickListener{
            startTimer(pauseOffSet)
        }
        btnPause.setOnClickListener{
            pauseTimer()
        }
        btnStop.setOnClickListener{
            resetTimer()
        }

    }

    private fun startTimer(pauseOffsetL:Long){

        countDownTimer = object : CountDownTimer(timeDuration - pauseOffsetL , 1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                pauseOffSet = timeDuration - millisUntilFinished
                tvTimer.text = (millisUntilFinished/1000).toString()

            }
            override fun onFinish() {
                Toast.makeText(this@MainActivity , "Timer Has Finished" , Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
    private fun pauseTimer(){
        if(countDownTimer != null){
            countDownTimer!!.cancel()
        }
    }
    private fun resetTimer(){
        if(countDownTimer != null){
            countDownTimer!!.cancel()
            tvTimer.text = (timeDuration/1000).toString()
            countDownTimer = null
            pauseOffSet = 0
        }
    }
}