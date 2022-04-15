package com.example.rpn_calculator

interface CalculatorCommand {

    fun execute(stack: ArrayDeque<Int>): CommandResult

    fun undo(stack: ArrayDeque<Int>)

}