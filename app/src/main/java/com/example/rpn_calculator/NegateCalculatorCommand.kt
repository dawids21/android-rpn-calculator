package com.example.rpn_calculator

class NegateCalculatorCommand(private val stack: ArrayDeque<Double>) : CalculatorCommand {

    override fun execute(): CommandResult {

        if (stack.count() < 1) {
            return FailureCommandResult("Not enough numbers on the stack")
        }

        stack.addFirst(-stack.removeFirst())
        return SuccessCommandResult()
    }

    override fun undo() {
        stack.addFirst(-stack.removeFirst())
    }
}