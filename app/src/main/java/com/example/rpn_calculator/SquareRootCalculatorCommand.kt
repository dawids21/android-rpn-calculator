package com.example.rpn_calculator

import kotlin.math.sqrt

class SquareRootCalculatorCommand(private val stack: ArrayDeque<Double>) : CalculatorCommand {

    private var a = 0.0

    override fun execute(): CommandResult {
        if (stack.count() < 1) {
            return FailureCommandResult("Not enough numbers on the stack")
        }

        a = stack.removeFirst()
        stack.addFirst(sqrt(a))
        return SuccessCommandResult()
    }

    override fun undo() {
        stack.removeFirst()
        stack.addFirst(a)
    }
}