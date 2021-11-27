package com.example.composefiesta.ui.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignUpViewModel : ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    data class UiState(
        val email: String = "",
        val emailValid: Boolean? = null,
        val password: String = "",
        val passwordValid: Boolean? = null,
        val passwordVisible: Boolean = false,
        val name: String = "",
        val date: String = "",
        val phone: String = "",
        val phoneValid: Boolean? = null,
        val allFieldsValid: Boolean = false,
        val submitting: Boolean = false,
        val successSubmitted: Boolean = false,
    )

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun validateEmail(email: String) {
        val emailValid =
            email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val allFieldsValid =
            emailValid && state.value.passwordValid == true && state.value.phoneValid == true
        _state.value = _state.value.copy(emailValid = emailValid, allFieldsValid = allFieldsValid)
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun onPasswordVisibilityChange(passwordVisible: Boolean) {
        _state.value = _state.value.copy(passwordVisible = passwordVisible)
    }

    fun validatePassword(password: String) {
        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"
        val passwordMatcher = Regex(passwordPattern)
        val passwordValid = passwordMatcher.find(password) != null
        val allFieldsValid =
            state.value.emailValid == true && passwordValid && state.value.phoneValid == true

        _state.value =
            _state.value.copy(passwordValid = passwordValid, allFieldsValid = allFieldsValid)
    }

    fun onNameChange(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun onDateChange(date: String) {
        _state.value = _state.value.copy(date = date)
    }

    fun onDatePicked(date: Long?) {

    }

    fun onPhoneChange(phone: String) {
        _state.value = _state.value.copy(phone = phone)
    }

    fun validatePhone(phone: String) {
        val phoneValid = phone.length == 9
        val allFieldsValid =
            state.value.emailValid == true && state.value.passwordValid == true && phoneValid
        _state.value = _state.value.copy(phoneValid = phoneValid, allFieldsValid = allFieldsValid)
    }

    fun submitForm() {
        viewModelScope.launch {
            _state.value = _state.value.copy(submitting = true)
            delay(1000)
            _state.value = _state.value.copy(submitting = false, successSubmitted = true)
        }
    }
}