package com.example.rpn_calculator

class CalculatorCommandHandler {

    private val commands: ArrayDeque<CalculatorCommand> = ArrayDeque()
    private val stack: ArrayDeque<Int> = ArrayDeque()

    fun handle(command: CalculatorCommand) {
        val result = command.execute(stack)
        if (result.isSuccess()) {
            commands.addLast(command)
        }
        //TODO handle when not success
    }

    fun undo() {
        val commandToUndo = commands.removeLast()
        commandToUndo.undo(stack)
    }
}