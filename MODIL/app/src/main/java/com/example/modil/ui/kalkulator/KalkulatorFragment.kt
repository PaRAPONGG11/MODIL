package com.example.modil.ui.kalkulator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.modil.databinding.FragmentKalkulatorBinding

class KalkulatorFragment : Fragment() {

    private var _binding: FragmentKalkulatorBinding? = null
    private val binding get() = _binding!!

    private var currentInput = ""
    private var operator: String? = null
    private var firstOperand: Double? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKalkulatorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupButtons()
        return root
    }

    private fun setupButtons() {
        // Digit buttons (0-9)
        binding.button0.setOnClickListener { appendToInput("0") }
        binding.button1.setOnClickListener { appendToInput("1") }
        binding.button2.setOnClickListener { appendToInput("2") }
        binding.button3.setOnClickListener { appendToInput("3") }
        binding.button4.setOnClickListener { appendToInput("4") }
        binding.button5.setOnClickListener { appendToInput("5") }
        binding.button6.setOnClickListener { appendToInput("6") }
        binding.button7.setOnClickListener { appendToInput("7") }
        binding.button8.setOnClickListener { appendToInput("8") }
        binding.button9.setOnClickListener { appendToInput("9") }

        // Operation buttons
        binding.buttonAdd.setOnClickListener { setOperation("+") }
        binding.buttonSqrt.setOnClickListener { calculateSquareRoot() }
        binding.buttonSquare.setOnClickListener { calculateSquare() }
        binding.buttonLog.setOnClickListener { calculateLog() }
        binding.buttonSin.setOnClickListener { calculateSin() }
        binding.buttonCos.setOnClickListener { calculateCos() }
        binding.buttonTan.setOnClickListener { calculateTan() }

        // Equals button
        binding.buttonEqual.setOnClickListener { calculateResult() }

        // Clear button
        binding.buttonClear.setOnClickListener { clearInput() }
    }

    private fun appendToInput(digit: String) {
        currentInput += digit
        updateDisplay()
    }

    private fun setOperation(op: String) {
        firstOperand = currentInput.toDoubleOrNull()
        operator = op
        currentInput = ""
    }

    private fun calculateSquareRoot() {
        val value = currentInput.toDoubleOrNull()
        if (value != null) {
            currentInput = Math.sqrt(value).toString()
            updateDisplay()
        }
    }

    private fun calculateSquare() {
        val value = currentInput.toDoubleOrNull()
        if (value != null) {
            currentInput = (value * value).toString()
            updateDisplay()
        }
    }

    private fun calculateLog() {
        val value = currentInput.toDoubleOrNull()
        if (value != null && value > 0) {
            currentInput = Math.log10(value).toString()
            updateDisplay()
        }
    }

    private fun calculateSin() {
        val value = currentInput.toDoubleOrNull()
        if (value != null) {
            currentInput = Math.sin(Math.toRadians(value)).toString()
            updateDisplay()
        }
    }

    private fun calculateCos() {
        val value = currentInput.toDoubleOrNull()
        if (value != null) {
            currentInput = Math.cos(Math.toRadians(value)).toString()
            updateDisplay()
        }
    }

    private fun calculateTan() {
        val value = currentInput.toDoubleOrNull()
        if (value != null) {
            currentInput = Math.tan(Math.toRadians(value)).toString()
            updateDisplay()
        }
    }

    private fun calculateResult() {
        val secondOperand = currentInput.toDoubleOrNull()
        if (firstOperand != null && secondOperand != null && operator != null) {
            currentInput = when (operator) {
                "+" -> (firstOperand!! + secondOperand).toString()
                // Add cases for other operators if needed
                else -> currentInput
            }
            updateDisplay()
            firstOperand = null
            operator = null
        }
    }

    private fun clearInput() {
        currentInput = ""
        firstOperand = null
        operator = null
        updateDisplay()
    }

    private fun updateDisplay() {
        binding.result.text = currentInput
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
