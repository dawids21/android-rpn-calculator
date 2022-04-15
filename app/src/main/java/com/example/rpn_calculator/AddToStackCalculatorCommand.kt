package com.example.rpn_calculator

class AddToStackCalculatorCommand(private val stack: ArrayDeque<Int>, private val number: Int) :
    CalculatorCommand {

    override fun execute(): CommandResult {
        stack.addLast(number)
        return SuccessCommandResult()
    }

    override fun undo() {
        stack.removeLast()
    }
}