package com.example.rpn_calculator

class ClearCalculatorCommand(private val stack: ArrayDeque<Double>) : CalculatorCommand {

    private val clearedNumbers: ArrayList<Double> = ArrayList()

    override fun execute(): CommandResult {
        stack.forEach { clearedNumbers.add(it) }
        stack.clear()
        return SuccessCommandResult()
    }

    override fun undo() {
        clearedNumbers.forEach { stack.addLast(it) }
    }
}