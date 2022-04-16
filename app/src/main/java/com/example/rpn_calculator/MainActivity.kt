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

        binding.backButton.setOnClickListener {
            binding.editorView.text =
                binding.editorView.text.subSequence(0, binding.editorView.text.length - 1)
        }

        binding.enterButton.setOnClickListener {
            val command =
                AddToStackCalculatorCommand(stack, binding.editorView.text.toString().toDouble())
            Log.i(COMMAND_TAG, "Sending $command")
            commandHandler.handle(command)
            Log.i(STACK_TAG, "Current stack: $stack")
            binding.editorView.text = ""
            refreshStackViews()
        }

        binding.undoButton.setOnClickListener {
            Log.i(COMMAND_TAG, "Undo command")
            commandHandler.undo()
            Log.i(STACK_TAG, "Current stack: $stack")
            refreshStackViews()
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

    private fun refreshStackViews() {
        val stackViews = listOf(
            binding.firstStack, binding.secondStack,
            binding.thirdStack, binding.fourthStack
        )

        if (stack.count() <= 4) {
            for ((index, number) in stack.withIndex()) {
                stackViews[index].text =
                    getString(R.string.stackViewPlaceholder, index + 1, number.toString())
            }
            return
        }

        val start = stack.count() - 4

        for (index in start until stack.count()) {
            stackViews[index - start].text =
                getString(R.string.stackViewPlaceholder, index + 1, stack[index].toString())
        }
    }
}