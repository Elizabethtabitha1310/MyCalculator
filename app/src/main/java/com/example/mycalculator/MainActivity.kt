package com.example.mycalculator

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mycalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var displayText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            arrayOf(
                txtZero, txtOne, txtTwo, txtThree, txtFour,
                txtFive, txtSix, txtSeven, txtEight, txtNine,
                txtAdd, txtSubtract, txtMutiply, txtDivide,
                txtOpenBracket, txtClosedBracket, txtPercentage, txtEqual, txtClear
            ).forEach { textView ->
                textView.setOnClickListener { onButtonClick(textView) }
            }
            imgBackspace.setOnClickListener { onBackspaceClick() }
        }
    }

    private fun onButtonClick(view: TextView) {
        when (view.id) {
            R.id.txtClear -> clearDisplay()
            R.id.txtEqual -> calculateResult()
            else -> {
                displayText += view.text
                binding.txtDisplay.text = displayText
            }
        }
    }

    private fun onBackspaceClick() {
        if (displayText.isNotEmpty()) {
            displayText = displayText.dropLast(1)
            binding.txtDisplay.text = displayText
        }
    }

    private fun clearDisplay() {
        displayText = ""
        binding.txtDisplay.text = displayText
        binding.txtResults.text = ""
    }

    private fun calculateResult() {
        // Here you would implement the logic to calculate the result
try {
    val expression = ExpressionBuilder(displayText).build()
    val result = expression.evaluate()
    binding.txtResults.text = result.toString()
}catch (e: Exception){
    binding.txtResults.text ="Error"
}

    }
}
