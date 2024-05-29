package com.example.mycalculator.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorViewModel : ViewModel() {

    private val _displayText = MutableLiveData<String>()
    val displayText: LiveData<String> get() = _displayText

    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String> get() = _resultText

    private val _historyList = MutableLiveData<MutableList<HistoryItem>>()
    val historyList: LiveData<MutableList<HistoryItem>> get() = _historyList

    init {
        _displayText.value = ""
        _resultText.value = ""
        _historyList.value = mutableListOf()
    }

    fun onButtonClick(value: String) {
        when (value) {
            "C" -> clearDisplay()
            "=" -> calculateResult()
            else -> {
                _displayText.value += value
            }
        }
    }

    fun onBackspaceClick() {
        _displayText.value = _displayText.value?.dropLast(1)
    }

    private fun clearDisplay() {
        _displayText.value = ""
        _resultText.value = ""
    }

    private fun calculateResult() {
        try {
            val expression = ExpressionBuilder(_displayText.value).build()
            val result = expression.evaluate().toString()
            _resultText.value = result
            addToHistory(_displayText.value.orEmpty(), result)
            _displayText.value = result
        } catch (e: Exception) {
            _resultText.value = "Error"
        }
    }

    private fun addToHistory(expression: String, result: String) {
        val historyItem = HistoryItem(expression, result)
        _historyList.value?.add(0, historyItem)
        _historyList.value = _historyList.value // Trigger LiveData update
    }
}
