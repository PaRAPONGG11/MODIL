package com.example.modil.ui.bmi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.modil.databinding.FragmentBmiBinding

class BmiFragment : Fragment() {

    private var _binding: FragmentBmiBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBmiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupButtons()
        return root
    }

    private fun setupButtons() {
        // Tombol untuk menghitung BMI
        binding.calculateButton.setOnClickListener { calculateBMI() }

        // Tombol untuk membersihkan input dan hasil
        binding.clearButton.setOnClickListener { clearInput() }
    }

    private fun calculateBMI() {
        val heightText = binding.heightInput.text.toString()
        val weightText = binding.weightInput.text.toString()

        // Validasi input
        if (heightText.isEmpty() || weightText.isEmpty()) {
            Toast.makeText(requireContext(), "Masukkan tinggi dan berat badan Anda!", Toast.LENGTH_SHORT).show()
            return
        }

        val height = heightText.toFloatOrNull()
        val weight = weightText.toFloatOrNull()

        if (height != null && weight != null) {
            // Menghitung BMI (dengan tinggi dalam meter)
            val bmi = weight / ((height / 100) * (height / 100))
            displayBMI(bmi)
        } else {
            Toast.makeText(requireContext(), "Input tidak valid.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun displayBMI(bmi: Float) {
        // Menentukan kategori BMI berdasarkan nilai BMI
        val bmiResult = when {
            bmi < 18.5 -> "Kurus"
            bmi < 24.9 -> "Normal"
            bmi < 29.9 -> "Gemuk"
            else -> "Obesitas"
        }

        // Menampilkan hasil dan kategori BMI
        binding.result.text = "Hasil BMI Anda: %.2f (%s)".format(bmi, bmiResult)
    }

    private fun clearInput() {
        // Menghapus input dan hasil
        binding.heightInput.text.clear()
        binding.weightInput.text.clear()
        binding.result.text = ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}