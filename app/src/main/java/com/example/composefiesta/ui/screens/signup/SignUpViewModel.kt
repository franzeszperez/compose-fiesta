package com.example.composefiesta.ui.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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
        val nameValid: Boolean? = null,
        val date: String = "",
        val dateValid: Boolean? = null,
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
        if (email.isNotEmpty()) {
            val emailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
            val allFieldsValid =
                emailValid && state.value.passwordValid == true && state.value.nameValid == true && state.value.dateValid == true && state.value.phoneValid == true
            _state.value =
                _state.value.copy(emailValid = emailValid, allFieldsValid = allFieldsValid)
        }
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun onPasswordVisibilityChange(passwordVisible: Boolean) {
        _state.value = _state.value.copy(passwordVisible = passwordVisible)
    }

    fun validatePassword(password: String) {
        if (password.isNotEmpty()) {
            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)
            val passwordValid = passwordMatcher.find(password) != null
            val allFieldsValid =
                state.value.emailValid == true && passwordValid && state.value.nameValid == true && state.value.dateValid == true && state.value.phoneValid == true

            _state.value =
                _state.value.copy(passwordValid = passwordValid, allFieldsValid = allFieldsValid)
        }
    }

    fun onNameChange(name: String) {
        _state.value = _state.value.copy(name = name)
    }

    fun validateName(name: String) {
        val nameValid = name.length > 1
        val allFieldsValid =
            state.value.emailValid == true && state.value.passwordValid == true && nameValid && state.value.phoneValid == true
        _state.value =
            _state.value.copy(nameValid = nameValid, allFieldsValid = allFieldsValid)
    }

    fun onDateChange(date: String) {
        _state.value = _state.value.copy(date = date)
    }

    fun validateDate(date: String) {
        if (date.isNotEmpty()) {
            val day = date.substring(0, 2)
            val month = date.substring(2, 4)
            val year = date.substring(4)

            val dateValid = when {
                day.isEmpty() || month.isEmpty() || year.isEmpty() -> false
                day.length != 2 || day.toLong() > 31 -> false
                month.length != 2 || month.toLong() > 12 -> false
                year.length != 4 -> false
                else -> true
            }
            val allFieldsValid =
                state.value.emailValid == true && state.value.passwordValid == true && state.value.nameValid == true && dateValid && state.value.phoneValid == true

            _state.value =
                _state.value.copy(dateValid = dateValid, allFieldsValid = allFieldsValid)
        }
    }

    fun onPhoneChange(phone: String) {
        _state.value = _state.value.copy(phone = phone)
    }

    fun validatePhone(phone: String) {
        if (phone.isNotEmpty()) {
            val phoneValid = phone.length == 9
            val allFieldsValid =
                state.value.emailValid == true && state.value.passwordValid == true && state.value.nameValid == true && state.value.dateValid == true && phoneValid
            _state.value =
                _state.value.copy(phoneValid = phoneValid, allFieldsValid = allFieldsValid)
        }
    }

    fun validateFieldsAndSubmit(
        email: String,
        password: String,
        name: String,
        date: String,
        phone: String
    ) {
        validateEmail(email = email)
        validatePassword(password = password)
        validateName(name = name)
        validateDate(date = date)
        validatePhone(phone = phone)
        val allFieldsValid =
            state.value.emailValid == true && state.value.passwordValid == true && state.value.nameValid == true && state.value.dateValid == true && state.value.phoneValid == true
        _state.value =
            _state.value.copy(allFieldsValid = allFieldsValid)
        if (_state.value.allFieldsValid) {
            submitForm()
        }
    }

    private fun submitForm() {
        viewModelScope.launch {
            _state.value = _state.value.copy(submitting = true)
            delay(1500)
            _state.value = _state.value.copy(submitting = false, successSubmitted = true)
            delay(4000)
            _state.value = _state.value.copy(successSubmitted = false)
        }
    }
}