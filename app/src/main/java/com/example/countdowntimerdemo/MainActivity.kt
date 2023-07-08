package com.example.countdowntimerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.countdowntimerdemo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    private var countDownTimer : CountDownTimer? = null//variable for timer
    private var timeDuration : Long = 60000 //Duration of timer in ms
    private var pauseOffSet : Long = 0 // pauseOffset = timeDuration - TimeLeft


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        binding?.button?.setOnClickListener{
            if(countDownTimer != null){ // in case someone presses start more than one time continuously it can freeze to stop this we resert the timer
                countDownTimer!!.cancel()
            }
            startTimer(pauseOffSet)
        }
        binding?.button2?.setOnClickListener{
            pauseTimer()
        }
        binding?.button3?.setOnClickListener{
            resetTimer()
        }

    }

    private fun startTimer(pauseOffsetL:Long){

        countDownTimer = object : CountDownTimer(timeDuration - pauseOffsetL , 1000)
        {
            override fun onTick(millisUntilFinished: Long) {
                pauseOffSet = timeDuration - millisUntilFinished
                binding?.tvTimer?.text = (millisUntilFinished/1000).toString()

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
            binding?.tvTimer?.text = (timeDuration/1000).toString()
            countDownTimer = null
            pauseOffSet = 0
        }
    }
}