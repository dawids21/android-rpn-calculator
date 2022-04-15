package com.example.rpn_calculator

class CalculatorCommandHandler {

    private val commands: ArrayDeque<CalculatorCommand> = ArrayDeque()

    fun handle(command: CalculatorCommand) {
        val result = command.execute()
        if (result.isSuccess()) {
            commands.addLast(command)
        }
        //TODO handle when not success
    }

    fun undo() {
        if (commands.none()) {
            return
        }
        val commandToUndo = commands.removeLast()
        commandToUndo.undo()
    }
}