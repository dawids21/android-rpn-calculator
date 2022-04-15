package com.example.rpn_calculator

interface CommandResult {

    fun isSuccess(): Boolean

    fun getMessage(): String

}