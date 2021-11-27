package com.example.composefiesta.ui.screens.signup

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composefiesta.ComposeFiestaApp
import com.example.composefiesta.R
import com.example.composefiesta.ui.common.AppBar
import com.example.composefiesta.ui.common.DateVisualTransformation

@Composable
fun SignUpScreen(viewModel: SignUpViewModel = viewModel()) {
    Scaffold(
        topBar = { AppBar(title = stringResource(id = R.string.signup)) },
    ) { paddingValues ->
        val state by viewModel.state.collectAsState()
        val focusManager = LocalFocusManager.current

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.padding_xs),
                Alignment.CenterVertically
            ),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues)
        ) {
            SignUpField(
                value = state.email,
                onValueChange = { viewModel.onEmailChange(it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    viewModel.validateEmail(state.email)
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                icon = Icons.Default.Email,
                labelString = stringResource(id = R.string.email_label),
                placeholderString = stringResource(id = R.string.email_placeholder),
                trailingIcon = {
                    if (state.email.isNotEmpty() && state.emailValid == true) {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colors.secondary
                            )
                        }
                    }
                },
                isError = state.email.isNotEmpty() && state.emailValid == false,
                errorMessage = stringResource(id = R.string.email_invalid),
                modifier = Modifier
                    .onFocusChanged { focusState ->
                        when {
                            !focusState.isFocused -> {
                                Log.d("Francesc", "I lost focus!")
                                viewModel.validateEmail(state.email)
                            }
                        }
                    }
            )
            SignUpField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    viewModel.validatePassword(state.password)
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                icon = Icons.Default.Lock,
                labelString = stringResource(id = R.string.password_label),
                placeholderString = stringResource(id = R.string.password_placeholder),
                trailingIcon = {
                    Row {
                        IconButton(
                            onClick = { viewModel.onPasswordVisibilityChange(!state.passwordVisible) }
                        ) {
                            if (!state.passwordVisible) {
                                Icon(
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = null
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.VisibilityOff,
                                    contentDescription = null
                                )
                            }
                        }
                        if (state.password.isNotEmpty() && state.passwordValid == true) {
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = MaterialTheme.colors.secondary
                                )
                            }
                        }
                    }
                },
                isError = state.password.isNotEmpty() && state.passwordValid == false,
                visualTransformation = if (state.passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                errorMessage = stringResource(id = R.string.password_invalid),
                modifier = Modifier
                    .onFocusChanged { focusState ->
                        when {
                            !focusState.isFocused -> {
                                viewModel.validatePassword(state.password)
                            }
                        }
                    },
            )
            SignUpField(
                value = state.name,
                onValueChange = { viewModel.onNameChange(it) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    viewModel.validateName(state.name)
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                icon = Icons.Default.Person,
                labelString = stringResource(id = R.string.name_label),
                placeholderString = stringResource(id = R.string.name_placeholder),
                trailingIcon = {
                    if (state.nameValid == true && state.name.isNotEmpty()) {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colors.secondary
                            )
                        }
                    }
                },
                isError = state.nameValid == false,
                errorMessage = stringResource(id = R.string.name_invalid),
                modifier = Modifier
                    .onFocusChanged { focusState ->
                        when {
                            !focusState.isFocused -> {
                                if (state.name.isNotEmpty()) {
                                    viewModel.validateName(state.name)
                                }
                            }
                        }
                    },
            )
            SignUpField(
                value = state.date,
                onValueChange = {
                    if (it.length <= 8) {
                        viewModel.onDateChange(it)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    viewModel.validateDate(state.date)
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                icon = Icons.Default.Cake,
                labelString = stringResource(id = R.string.date_label),
                placeholderString = stringResource(id = R.string.date_placeholder),
                trailingIcon = {
                    if (state.date.isNotEmpty() && state.dateValid == true) {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colors.secondary
                            )
                        }
                    }
                },
                isError = state.date.isNotEmpty() && state.dateValid == false,
                visualTransformation = DateVisualTransformation(),
                errorMessage = stringResource(id = R.string.date_invalid),
                modifier = Modifier
                    .onFocusChanged { focusState ->
                        when {
                            !focusState.isFocused -> {
                                viewModel.validateDate(state.date)
                            }
                        }
                    },
            )
            SignUpField(
                value = state.phone,
                onValueChange = { viewModel.onPhoneChange(it) },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        viewModel.validatePhone(state.phone)
                        viewModel.validateFieldsAndSubmit(
                            state.email,
                            state.password,
                            state.name,
                            state.date,
                            state.phone
                        )
                        focusManager.clearFocus()
                    }
                ),
                icon = Icons.Default.Phone,
                labelString = stringResource(id = R.string.phone_label),
                placeholderString = stringResource(id = R.string.phone_placeholder),
                trailingIcon = {
                    if (state.phone.isNotEmpty() && state.phoneValid == true) {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colors.secondary
                            )
                        }
                    }
                },
                isError = state.phone.isNotEmpty() && state.phoneValid == false,
                errorMessage = stringResource(id = R.string.phone_invalid),
                modifier = Modifier.onFocusChanged { focusState ->
                    if (!focusState.isFocused) {
                        viewModel.validatePhone(state.phone)
                    }
                },
            )
            Button(
                onClick = {
                    viewModel.validateFieldsAndSubmit(
                        state.email,
                        state.password,
                        state.name,
                        state.date,
                        state.phone
                    )
                },
                enabled = state.allFieldsValid
            ) {
                if (state.submitting) {
                    CircularProgressIndicator(color = MaterialTheme.colors.surface)
                } else {
                    Text(text = stringResource(id = R.string.signup))
                }
            }
            AnimatedVisibility(state.successSubmitted) {
                Snackbar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(Alignment.Bottom)

                ) {
                    Text(text = stringResource(id = R.string.success_signup))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SignUpScreenPreview() {
    ComposeFiestaApp {
        SignUpScreen()
    }
}