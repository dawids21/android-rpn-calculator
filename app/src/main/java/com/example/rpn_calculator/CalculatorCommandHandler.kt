package com.example.rpn_calculator

class CalculatorCommandHandler {

    private val commands: ArrayList<CalculatorCommand> = ArrayList()

    fun handle(command: CalculatorCommand) {

        val result = command.execute()
        if (result.isSuccess()) {
            commands.add(command)
        }
        //TODO handle when not success

    }

}