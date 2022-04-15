package com.example.rpn_calculator

import android.util.Log

class CalculatorCommandHandler {

    companion object {
        private const val TAG = "CommandHandler"
    }

    private val commands: ArrayDeque<CalculatorCommand> = ArrayDeque()

    fun handle(command: CalculatorCommand) {
        val result = command.execute()
        if (result.isSuccess()) {
            commands.addLast(command)
        }
        Log.e(TAG, "Command failure: $command, message: ${result.getMessage()}")
    }

    fun undo() {
        if (commands.none()) {
            return
        }
        val commandToUndo = commands.removeLast()
        commandToUndo.undo()
    }
}