package com.example.composefiesta.ui.screens.signup

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composefiesta.ComposeFiestaApp
import com.example.composefiesta.R
import com.example.composefiesta.ui.common.AppBar
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun SignUpScreen(viewModel: SignUpViewModel = viewModel()) {
    Scaffold(
        topBar = { AppBar() },
    ) { paddingValues ->
        val state by viewModel.state.collectAsState()
        val dialogState = rememberMaterialDialogState()
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
                labelString = stringResource(id = R.string.email_label),
                placeholderString = stringResource(id = R.string.email_placeholder),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    viewModel.validateEmail(state.email)
                    focusManager.moveFocus(FocusDirection.Down)
                }),
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
                icon = Icons.Default.Email,
                errorMessage = stringResource(id = R.string.email_invalid)
            )
            SignUpField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                labelString = stringResource(id = R.string.password_label),
                placeholderString = stringResource(id = R.string.password_placeholder),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    viewModel.validatePassword(state.password)
                    focusManager.moveFocus(FocusDirection.Down)
                }),
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
                icon = Icons.Default.Lock,
                errorMessage = stringResource(id = R.string.password_invalid)
            )
            SignUpField(
                value = state.name,
                onValueChange = { viewModel.onNameChange(it) },
                labelString = stringResource(id = R.string.name_label),
                placeholderString = stringResource(id = R.string.name_placeholder),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
                isError = false,
                icon = Icons.Default.Person
            )
            SignUpField(
                value = state.date,
                onValueChange = { viewModel.onDateChange(it) },
                labelString = stringResource(id = R.string.date_label),
                placeholderString = stringResource(id = R.string.date_placeholder),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
//                visualTransformation = DateVisualTransformation(),
                isError = false,
                icon = Icons.Default.Cake,
                modifier = Modifier.clickable { }

            )
            SignUpField(
                value = state.phone,
                onValueChange = { viewModel.onPhoneChange(it) },
                labelString = stringResource(id = R.string.phone_label),
                placeholderString = stringResource(id = R.string.phone_placeholder),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        viewModel.validatePhone(state.phone)
                        if (state.allFieldsValid) {
                            viewModel.submitForm()
                        }
                        focusManager.clearFocus()
                    }
                ),
                isError = state.phone.isNotEmpty() && state.phoneValid == false,
                icon = Icons.Default.Phone,
                errorMessage = stringResource(id = R.string.phone_invalid)
            )
            Button(onClick = { viewModel.submitForm() }, enabled = state.allFieldsValid) {
                if (state.submitting) {
                    CircularProgressIndicator(color = MaterialTheme.colors.surface)
                } else {
                    Text(text = stringResource(id = R.string.signup))
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