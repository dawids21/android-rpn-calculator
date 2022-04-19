package com.example.rpn_calculator

class SwapCalculatorCommand(private val stack: ArrayDeque<Double>) : CalculatorCommand {

    override fun execute(): CommandResult {
        if (stack.count() < 2) {
            return FailureCommandResult("Not enough numbers on the stack")
        }

        swap()
        return SuccessCommandResult()
    }

    override fun undo() {
        swap()
    }

    private fun swap() {
        val tempA = stack.removeFirst()
        val tempB = stack.removeFirst()
        stack.addFirst(tempA)
        stack.addFirst(tempB)
    }
}