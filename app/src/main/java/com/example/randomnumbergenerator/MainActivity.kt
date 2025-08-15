package com.example.randomnumbergenerator // Replace with your actual package name

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var randomNumberTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var generateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Make sure this layout has both TextViews
        randomNumberTextView = findViewById(R.id.randomNumberTextView) // <
        descriptionTextView = findViewById(R.id.descriptionTextView)
        generateButton = findViewById(R.id.generateButton)

        generateButton.setOnClickListener {
            generateAndDisplayNumberWithDescription()
        }
    }

    private fun generateAndDisplayNumberWithDescription() {
        val randomNumber = Random.nextInt(1, 100) // Generates 0-100

        randomNumberTextView.text = randomNumber.toString()

        // Format the filename (e.g., 1 -> "001.txt", 10 -> "010.txt", 100 -> "100.txt")
        // If your random numbers are 0-99, adjust the formatting accordingly.
        // The problem description says 0-99 but then "if 100 pls print content of 100.txt"
        // I'll assume 0-100 is the range for file naming. If it's 0-99, then the max filename would be "099.txt"
        // For numbers 1-99, you want 3 digits with leading zeros. For 100, it's just "100.txt".
        // If the generated number is 0, what file should it look for? "000.txt"? "001.txt"?
        // Let's assume random number 1 maps to "001.txt", up to 100 mapping to "100.txt".
        // And if the random number is 0, we'll use "default.txt" or you can define "000.txt".

        val filename: String = when (randomNumber) {
            0 -> "default.txt" // Or "000.txt" if you have it
            in 1..99 -> String.format("%03d.txt", randomNumber) // e.g., 1 -> "001.txt", 12 -> "012.txt"
            100 -> "100.txt"
            else -> "default.txt" // Should not happen with Random.nextInt(0, 101)
        }

        descriptionTextView.text = readFileContentFromAssets(filename)
    }

    private fun readFileContentFromAssets(filename: String): String {
        val stringBuilder = StringBuilder()
        try {
            assets.open(filename).use { inputStream -> // assets.open() will look in all registered asset folders
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        stringBuilder.append(line).append('\n')
                    }
                }
            }
        } catch (e: IOException) {
            Log.e("MainActivity", "Error reading asset file: $filename", e)
            // File not found, try to read default.txt
            if (filename != "default.txt") { // Avoid infinite recursion if default.txt is also missing
                return readFileContentFromAssets("default.txt")
            } else {
                return "Error: Default description file (default.txt) not found."
            }
        }
        // Remove the last newline character if the file is not empty
        return if (stringBuilder.isNotEmpty()) stringBuilder.toString().trimEnd('\n') else "File content is empty."
    }
}
