package com.example.rpn_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.rpn_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private const val COMMAND_TAG = "CalculatorCommand"
        private const val STACK_TAG = "Stack"
    }

    private val stack: ArrayDeque<Int> = ArrayDeque()
    private val commandHandler = CalculatorCommandHandler()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            val command = AddToStackCalculatorCommand(stack, 5)
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
}