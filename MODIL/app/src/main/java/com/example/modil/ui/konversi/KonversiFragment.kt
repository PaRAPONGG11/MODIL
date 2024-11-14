package com.example.modil.ui.konversi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.modil.R

class KonversiFragment : Fragment() {

    private lateinit var inputAmount: EditText
    private lateinit var spinnerFromCurrency: Spinner
    private lateinit var spinnerToCurrency: Spinner
    private lateinit var buttonConvert: Button
    private lateinit var textResult: TextView

    // Contoh kurs sementara. Anda bisa menggantinya dengan API nilai tukar sebenarnya.
    private val exchangeRates = mapOf(
        "USD_IDR" to 15000.0,
        "IDR_USD" to 0.000067,
        "USD_EUR" to 0.85,
        "EUR_USD" to 1.18,
        "IDR_EUR" to 0.000057,
        "EUR_IDR" to 17500.0
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout
        val view = inflater.inflate(R.layout.fragment_konversi, container, false)

        // Initialize UI components
        inputAmount = view.findViewById(R.id.input_amount)
        spinnerFromCurrency = view.findViewById(R.id.spinner_from_currency)
        spinnerToCurrency = view.findViewById(R.id.spinner_to_currency)
        buttonConvert = view.findViewById(R.id.button_convert)
        textResult = view.findViewById(R.id.text_result)

        // Set up spinners with currency array from strings.xml
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.currency_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFromCurrency.adapter = adapter
            spinnerToCurrency.adapter = adapter
        }

        // Set button click listener
        buttonConvert.setOnClickListener {
            convertCurrency()
        }

        return view
    }

    private fun convertCurrency() {
        val amountText = inputAmount.text.toString()

        // Check if input is valid
        if (amountText.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter an amount", Toast.LENGTH_SHORT).show()
            return
        }

        val amount = amountText.toDouble()
        val fromCurrency = spinnerFromCurrency.selectedItem.toString()
        val toCurrency = spinnerToCurrency.selectedItem.toString()

        // Handle case if both currencies are the same
        if (fromCurrency == toCurrency) {
            textResult.text = "Converted Amount: $amount $toCurrency"
            return
        }

        // Get the exchange rate key (e.g., "USD_IDR")
        val rateKey = "${fromCurrency}_${toCurrency}"
        val exchangeRate = exchangeRates[rateKey]

        if (exchangeRate != null) {
            // Calculate converted amount
            val convertedAmount = amount * exchangeRate
            textResult.text = "Converted Amount: %.2f %s".format(convertedAmount, toCurrency)
        } else {
            // Handle case if exchange rate is not found
            textResult.text = "Exchange rate not available for $fromCurrency to $toCurrency"
        }
    }
}
