package com.example.rpn_calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rpn_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val COMMAND_TAG = "CalculatorCommand"
        private const val STACK_TAG = "Stack"
    }

    private val stack: ArrayDeque<Double> = ArrayDeque()
    private val commandHandler = CalculatorCommandHandler()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val numberButtons = listOf(
            binding.zeroButton, binding.oneButton, binding.twoButton, binding.threeButton,
            binding.fourButton, binding.fiveButton, binding.sixButton, binding.sevenButton,
            binding.eightButton, binding.nineButton
        )

        for ((index, button) in numberButtons.withIndex()) {
            button.setOnClickListener { putInEditor(index.toString()) }
        }

        binding.dotButton.setOnClickListener { putInEditor(".") }

        binding.enterButton.setOnClickListener {
            val command = AddToStackCalculatorCommand(stack, 5.0)
            Log.i(COMMAND_TAG, "Sending $command")
            commandHandler.handle(command)
            Log.i(STACK_TAG, "Current stack: $stack")
        }

        binding.undoButton.setOnClickListener {
            Log.i(COMMAND_TAG, "Undo command")
            commandHandler.undo()
            Log.i(STACK_TAG, "Current stack: $stack")
        }
    }

    private fun putInEditor(text: String) {

        val editorView = binding.editorView
        if (text == "0" && editorView.text.isEmpty()) {
            return
        }
        if (text == "." && (editorView.text.isBlank() || editorView.text.contains("."))) {
            return
        }
        editorView.append(text)
    }
}