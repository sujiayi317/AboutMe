package com.android.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.doneButton.setOnClickListener {
            // it refers to the done_button, which is the view passed as the argument.
            addNickname()
        }

        binding.nicknameText.setOnClickListener {
            updateNickname()
        }
    }

    // The view parameter is the View on which the function is called.
    // In this case, view will be an instance of your DONE button.
    private fun addNickname() {
        with(binding) {
            // Set the text in the nicknameTextView text view to the text that the user entered in
            // the editText, getting it from the text property.
            nicknameText.text = nicknameEdit.text

            // change the visibility
            nicknameEdit.visibility = View.GONE

            // Hide the DONE button by setting the visibility property to View.GONE.
            // You already have the button's reference as the function's input parameter, view
            doneButton.visibility = View.GONE

            // At the end of the addNickname function, make the nickname TextView view visible
            nicknameText.visibility = View.VISIBLE
        }

        // Hide the keyboard.
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.doneButton.windowToken, 0)
    }

    private fun updateNickname() {
        with(binding) {
            nicknameEdit.visibility = View.VISIBLE
            doneButton.visibility = View.VISIBLE
            nicknameText.visibility = View.GONE              // hide the text view
            // Set the focus to the edit text.
            nicknameEdit.requestFocus()
        }

        // Show the keyboard.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.nicknameEdit, 0)
    }
}
