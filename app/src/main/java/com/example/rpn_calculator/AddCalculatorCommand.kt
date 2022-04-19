package com.example.rpn_calculator

class AddCalculatorCommand(private val stack: ArrayDeque<Double>) : CalculatorCommand {

    private var a = 0.0
    private var b = 0.0

    override fun execute(): CommandResult {
        if (stack.count() < 2) {
            return FailureCommandResult("Not enough numbers on the stack")
        }

        a = stack.removeFirst()
        b = stack.removeFirst()
        stack.addFirst(a + b)
        return SuccessCommandResult()
    }

    override fun undo() {
        stack.removeFirst()
        stack.addFirst(b)
        stack.addFirst(a)
    }
}