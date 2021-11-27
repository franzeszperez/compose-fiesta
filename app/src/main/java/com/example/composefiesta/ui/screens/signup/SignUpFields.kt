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
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    labelString: String = "",
    placeholderString: String = "",
    trailingIcon: @Composable () -> Unit = {},
    isError: Boolean = false,
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
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions,
    modifier: Modifier = Modifier,
    labelString: String = "",
    placeholderString: String = "",
    trailingIcon: @Composable () -> Unit = {},
    isError: Boolean = false,
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
            trailingIcon = trailingIcon,
            isError = isError,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
        )
        if (isError) {
            Text(
                text = errorMessage,
                style = TextStyle(color = MaterialTheme.colors.error),
            )
        }
    }
}

