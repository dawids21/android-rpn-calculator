package com.example.rpn_calculator

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rpn_calculator.databinding.ActivityMainBinding
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {

    companion object {
        private const val COMMAND_TAG = "CalculatorCommand"
        private const val STACK_TAG = "Stack"
    }

    private val stack: ArrayDeque<Double> = ArrayDeque()
    private val commandHandler = CalculatorCommandHandler()
    private val appSettings = AppSettings()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        title = "RPN Calculator"
        refreshStackViews()

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
            if (binding.editorView.text.isEmpty()) {
                return@setOnClickListener
            }
            binding.editorView.text =
                binding.editorView.text.subSequence(0, binding.editorView.text.length - 1)
        }

        binding.addButton.setOnClickListener {
            sendCommand(
                AddCalculatorCommand(stack)
            )
        }

        binding.subtractButton.setOnClickListener {
            sendCommand(
                SubtractCalculatorCommand(stack)
            )
        }

        binding.multiplyButton.setOnClickListener {
            sendCommand(
                MultiplyCalculatorCommand(stack)
            )
        }

        binding.divideButton.setOnClickListener {
            sendCommand(
                DivideCalculatorCommand(stack)
            )
        }

        binding.exponentButton.setOnClickListener {
            sendCommand(
                ExponentCalculatorCommand(stack)
            )
        }

        binding.squareRootButton.setOnClickListener {
            sendCommand(
                SquareRootCalculatorCommand(stack)
            )
        }

        binding.dropButton.setOnClickListener {
            sendCommand(
                RemoveStackCalculatorCommand(stack)
            )
        }

        binding.swapButton.setOnClickListener {
            sendCommand(
                SwapCalculatorCommand(stack)
            )
        }

        binding.enterButton.setOnClickListener {
            if (binding.editorView.text.isBlank()) {
                return@setOnClickListener
            }
            sendCommand(
                AddToStackCalculatorCommand(
                    stack,
                    binding.editorView.text.toString().toDouble()
                )
            )
            binding.editorView.text = ""
        }

        binding.allClearButton.setOnClickListener {
            sendCommand(
                ClearCalculatorCommand(stack)
            )
        }

        binding.negateButton.setOnClickListener {
            sendCommand(
                NegateCalculatorCommand(stack)
            )
        }

        binding.settingsButton.setOnClickListener {
            showSettings()
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

    private fun sendCommand(command: CalculatorCommand) {
        Log.i(COMMAND_TAG, "Sending $command")
        commandHandler.handle(command)
        Log.i(STACK_TAG, "Current stack: $stack")
        refreshStackViews()
    }

    private fun refreshStackViews() {
        val stackViews = listOf(
            binding.firstStack, binding.secondStack,
            binding.thirdStack, binding.fourthStack
        )

        stackViews.forEach { it.text = "" }

        val start = if (stack.count() > 4) stack.count() - 4 else 0

        for (index in start until start + 4) {
            val number = if (stack.count() - 1 >= index)
                BigDecimal(stack[index]).setScale(appSettings.decimalPlaces, RoundingMode.HALF_UP)
                    .toString() else ""
            stackViews[index - start].text =
                getString(R.string.stackViewPlaceholder, index + 1, number)
        }
    }

    private fun showSettings() {
        val intent = Intent(this, SettingsActivity::class.java)
        intent.putExtra(SettingsActivity.DECIMAL_PLACES_EXTRA_NAME, appSettings.decimalPlaces)
        startActivity(intent)
    }
}