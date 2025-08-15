package com.example.randomnumbergenerator // Replace with your actual package name

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random // Import Random

class MainActivity : AppCompatActivity() {

    private lateinit var randomNumberTextView: TextView
    private lateinit var generateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        randomNumberTextView = findViewById(R.id.randomNumberTextView)
        generateButton = findViewById(R.id.generateButton)

        // Set a click listener for the button
        generateButton.setOnClickListener {
            generateRandomNumber()
        }
    }

    private fun generateRandomNumber() {
        // Generate a random number between 0 (inclusive) and 100 (exclusive)
        val randomNumber = Random.nextInt(0, 100) // Generates 0-99
        randomNumberTextView.text = randomNumber.toString()
    }
}