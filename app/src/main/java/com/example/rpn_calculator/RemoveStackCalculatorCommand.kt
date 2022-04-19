package com.example.rpn_calculator

class RemoveStackCalculatorCommand(private val stack: ArrayDeque<Double>) : CalculatorCommand {

    private var number = 0.0

    override fun execute(): CommandResult {
        if (stack.count() < 1) {
            return FailureCommandResult("Not enough numbers on the stack")
        }
        number = stack.removeFirst()
        return SuccessCommandResult()
    }

    override fun undo() {
        stack.addFirst(number)
    }
}