package com.example.rpn_calculator

class DivideCalculatorCommand(private val stack: ArrayDeque<Double>) : CalculatorCommand {

    private var a = 0.0
    private var b = 0.0

    override fun execute(): CommandResult {
        if (stack.count() < 2) {
            return FailureCommandResult("Not enough numbers on the stack")
        }

        b = stack.removeFirst()
        if (b == 0.0) {
            stack.addFirst(b)
            return FailureCommandResult("Can not divide by zero")
        }
        a = stack.removeFirst()
        stack.addFirst(a / b)
        return SuccessCommandResult()
    }

    override fun undo() {
        stack.removeFirst()
        stack.addFirst(a)
        stack.addFirst(b)
    }
}