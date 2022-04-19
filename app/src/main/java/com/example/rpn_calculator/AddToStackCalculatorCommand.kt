package com.example.rpn_calculator

class AddToStackCalculatorCommand(
    private val stack: ArrayDeque<Double>,
    private val number: Double?
) :
    CalculatorCommand {

    override fun execute(): CommandResult {
        if (number == null) {
            if (stack.count() == 0) {
                return FailureCommandResult("Not enough numbers on the stack")
            }
            stack.addFirst(stack.first())
        } else {
            stack.addFirst(number)
        }
        return SuccessCommandResult()
    }

    override fun undo() {
        stack.removeFirst()
    }
}