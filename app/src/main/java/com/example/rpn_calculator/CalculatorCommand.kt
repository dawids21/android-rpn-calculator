package com.example.rpn_calculator

interface CalculatorCommand {

    fun execute(): CommandResult

    fun undo()

}