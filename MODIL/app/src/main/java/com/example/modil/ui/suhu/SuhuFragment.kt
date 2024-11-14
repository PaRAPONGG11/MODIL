package com.example.modil.ui.suhu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.modil.R

class SuhuFragment : Fragment() {

    // Variabel untuk elemen UI
    private lateinit var inputTemperatures: EditText
    private lateinit var spinnerFromUnits: Spinner
    private lateinit var spinnerToUnits: Spinner
    private lateinit var buttonConverts: Button
    private lateinit var buttonSwaps: Button
    private lateinit var textResults: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_suhu, container, false)

        // Inisialisasi elemen UI
        inputTemperatures = view.findViewById(R.id.input_temperatures)
        spinnerFromUnits = view.findViewById(R.id.spinner_from_units)
        spinnerToUnits = view.findViewById(R.id.spinner_to_units)
        buttonConverts = view.findViewById(R.id.button_converts)
        buttonSwaps = view.findViewById(R.id.button_swaps)
        textResults = view.findViewById(R.id.text_results)

        // Menyiapkan opsi pada Spinner dengan adapter (untuk satuan suhu)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.temperature_units_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFromUnits.adapter = adapter
            spinnerToUnits.adapter = adapter
        }

        // Mengatur klik tombol konversi
        buttonConverts.setOnClickListener {
            convertTemperature()
        }

        // Mengatur klik tombol swap untuk menukar satuan suhu
        buttonSwaps.setOnClickListener {
            swapUnits()
        }

        return view
    }

    // Fungsi untuk mengonversi suhu
    private fun convertTemperature() {
        val input = inputTemperatures.text.toString()
        if (input.isEmpty()) {
            textResults.text = "Please enter a temperature."
            return
        }

        val temperature = input.toDouble()
        val fromUnit = spinnerFromUnits.selectedItem.toString()
        val toUnit = spinnerToUnits.selectedItem.toString()

        val result = when (fromUnit to toUnit) {
            "Celsius" to "Fahrenheit" -> (temperature * 9/5) + 32
            "Celsius" to "Kelvin" -> temperature + 273.15
            "Fahrenheit" to "Celsius" -> (temperature - 32) * 5/9
            "Fahrenheit" to "Kelvin" -> (temperature - 32) * 5/9 + 273.15
            "Kelvin" to "Celsius" -> temperature - 273.15
            "Kelvin" to "Fahrenheit" -> (temperature - 273.15) * 9/5 + 32
            else -> temperature // Jika satuan sama, tidak ada perubahan
        }

        textResults.text = "Converted Temperature: %.2f".format(result)
    }

    // Fungsi untuk menukar satuan suhu
    private fun swapUnits() {
        val fromPosition = spinnerFromUnits.selectedItemPosition
        val toPosition = spinnerToUnits.selectedItemPosition

        spinnerFromUnits.setSelection(toPosition)
        spinnerToUnits.setSelection(fromPosition)
    }
}
