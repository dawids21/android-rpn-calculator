package com.example.rpn_calculator

class SuccessCommandResult : CommandResult {

    override fun isSuccess(): Boolean {
        return true
    }

    override fun getMessage(): String {
        return ""
    }
}