package com.example.composefiesta.ui.screens.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.composefiesta.ComposeFiestaApp
import com.example.composefiesta.R
import com.example.composefiesta.ui.common.AppBar

@Composable
fun SignUpScreen() {
    Scaffold(
        topBar = { AppBar() },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        val focusManager = LocalFocusManager.current

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                dimensionResource(id = R.dimen.padding_xs),
                Alignment.CenterVertically
            ),
            modifier = Modifier
                .padding(paddingValues = paddingValues)
        ) {
            Text(
                text = stringResource(id = R.string.signup_header),
                style = MaterialTheme.typography.h6,
                textDecoration = TextDecoration.Underline
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = stringResource(id = R.string.email_label)) },
                placeholder = { Text(text = stringResource(id = R.string.email_placeholder)) },
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Email, contentDescription = null)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = stringResource(id = R.string.password_label)) },
                placeholder = { Text(text = stringResource(id = R.string.password_placeholder)) },
                trailingIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = stringResource(id = R.string.name_label)) },
                placeholder = { Text(text = stringResource(id = R.string.name_placeholder)) },
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = stringResource(id = R.string.date_label)) },
                placeholder = { Text(text = stringResource(id = R.string.date_placeholder)) },
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Cake, contentDescription = null)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }),
            )
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text(text = stringResource(id = R.string.phone_label)) },
                placeholder = { Text(text = stringResource(id = R.string.phone_placeholder)) },
                trailingIcon = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Phone, contentDescription = null)
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Send
                ),
                keyboardActions = KeyboardActions(
                    onSend = {
                        focusManager.clearFocus()
                    }
                )
            )
            Button(onClick = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.signup))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    ComposeFiestaApp {
        SignUpScreen()
    }
}