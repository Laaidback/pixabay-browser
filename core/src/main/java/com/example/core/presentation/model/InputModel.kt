package com.example.core.presentation.model

data class InputModel(
    val value: String,
    val onInputChange: (String) -> Unit,
) {

    fun withNewInput(newInput: String) = copy(value = newInput)
}
