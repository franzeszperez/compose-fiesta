package com.example.composefiesta.ui.screens.signup

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import com.example.composefiesta.R

@Composable
fun SignUpField(
    value: String,
    onValueChange: (String) -> Unit,
    labelString: String,
    placeholderString: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    isError: Boolean,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    errorMessage: String = ""
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        icon?.let {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = modifier.padding(dimensionResource(id = R.dimen.padding_s))
            )
        }
        SignUpTextField(
            value = value,
            onValueChange = onValueChange,
            labelString = labelString,
            placeholderString = placeholderString,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            errorMessage = errorMessage,
        )
    }
}

@Composable
fun SignUpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelString: String,
    placeholderString: String,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: @Composable () -> Unit = {},
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    isError: Boolean,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    errorMessage: String = "",
) {

    Column(
        modifier = modifier
            .wrapContentSize()
            .width(IntrinsicSize.Max)
            .padding(dimensionResource(id = R.dimen.padding_s))
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = labelString) },
            placeholder = { Text(text = placeholderString) },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            isError = isError,
            visualTransformation = visualTransformation,
        )
        if (isError) {
            Text(
                text = errorMessage,
                style = TextStyle(color = MaterialTheme.colors.error),
            )
        }
    }
}

