package com.example.rpn_calculator

class FailureCommandResult(private val message: String) : CommandResult {

    override fun isSuccess(): Boolean {
        return false
    }

    override fun getMessage(): String {
        return message
    }
}