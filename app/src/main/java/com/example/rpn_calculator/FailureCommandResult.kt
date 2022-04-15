package com.example.rpn_calculator

class FailureCommandResult:CommandResult {

    override fun isSuccess(): Boolean {
        return false
    }
}