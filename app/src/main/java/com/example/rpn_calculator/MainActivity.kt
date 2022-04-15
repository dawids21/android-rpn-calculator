package com.example.rpn_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rpn_calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val stack: ArrayDeque<Int> = ArrayDeque()
    private val commandHandler = CalculatorCommandHandler()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.button.setOnClickListener {
            commandHandler.handle(AddToStackCalculatorCommand(stack, 5))
            println(stack)
        }

        binding.undoButton.setOnClickListener {
            commandHandler.undo()
            println(stack)
        }
    }
}