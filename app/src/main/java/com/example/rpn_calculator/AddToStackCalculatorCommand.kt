package com.example.rpn_calculator

class AddToStackCalculatorCommand(
    private val stack: ArrayDeque<Double>,
    private val number: Double?
) :
    CalculatorCommand {

    override fun execute(): CommandResult {
        if (number == null) {
            stack.addLast(stack.last())
        } else {
            stack.addLast(number)
        }
        return SuccessCommandResult()
    }

    override fun undo() {
        stack.removeLast()
    }
}