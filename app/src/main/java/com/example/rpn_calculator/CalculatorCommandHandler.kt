package com.example.rpn_calculator

class CalculatorCommandHandler {

    private val commands: ArrayList<CalculatorCommand> = ArrayList()
    private val stack: ArrayDeque<Int> = ArrayDeque()

    fun handle(command: CalculatorCommand) {

        val result = command.execute(stack)
        if (result.isSuccess()) {
            commands.add(command)
        }
        //TODO handle when not success

    }

}