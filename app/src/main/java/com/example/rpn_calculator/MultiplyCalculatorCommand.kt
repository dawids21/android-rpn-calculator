package com.example.rpn_calculator

class MultiplyCalculatorCommand(private val stack: ArrayDeque<Int>) : CalculatorCommand {

    private var a = 0
    private var b = 0

    override fun execute(): CommandResult {
        if (stack.count() < 2) {
            return FailureCommandResult("Not enough numbers on the stack")
        }

        a = stack.removeLast()
        b = stack.removeLast()
        stack.addLast(a * b)
        return SuccessCommandResult()
    }

    override fun undo() {
        stack.removeLast()
        stack.addLast(b)
        stack.addLast(a)
    }
}