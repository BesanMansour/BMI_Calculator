package com.project.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.project.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.MainBtnCal.setOnClickListener{
            val weight = binding.MainEtWeight.text.toString()
            val height = binding.MainEtHeight.text.toString()
            if (validateInput(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                // get result with two decimal places
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }
    }

    fun validateInput(weight:String? , height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()->{
                binding.MainEtWeight.error = "Weight is empty"
                return false
            } height.isNullOrEmpty()->{
                binding.MainEtHeight.error = "Height is empty"
                return false
            }else->{
                return true
            }
        }
    }

    fun displayResult(bmi:Float){

        binding.MainTvResultNum.text = bmi.toString()
        binding.MainTvRange.text = "(normal range is 18.5-24.9)"

        var resultTxt = ""
        var color = 0

        when{
            bmi < 18.50 ->{
                resultTxt = "UnderWeight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99 -> {
               resultTxt = "Healthy"
               color = R.color.normal
            }
            bmi in 25.00..29.99 -> {
                resultTxt = "OverWeight"
                color = R.color.over_weight
            }
            bmi > 29.99 ->{
                resultTxt = "Obese"
                color = R.color.obese
            }

        }

        binding.MainTvInfo.setTextColor(ContextCompat.getColor(this,color))
        binding.MainTvInfo.text = resultTxt
    }
}