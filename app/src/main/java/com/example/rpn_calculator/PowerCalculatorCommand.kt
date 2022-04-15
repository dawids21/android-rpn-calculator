package com.example.rpn_calculator

import kotlin.math.pow

class PowerCalculatorCommand(private val stack: ArrayDeque<Double>) : CalculatorCommand {

    private var a = 0.0
    private var b = 0.0

    override fun execute(): CommandResult {
        if (stack.count() < 2) {
            return FailureCommandResult("Not enough numbers on the stack")
        }

        b = stack.removeLast()
        a = stack.removeLast()
        stack.addLast(a.pow(b))
        return SuccessCommandResult()
    }

    override fun undo() {
        stack.removeLast()
        stack.addLast(a)
        stack.addLast(b)
    }
}