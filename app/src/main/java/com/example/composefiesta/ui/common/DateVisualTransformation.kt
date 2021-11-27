package com.example.composefiesta.ui.common

import android.util.Log
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Making dd-MM-YYYY string.
        val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text
        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (i == 1 || i == 3) out += "/"
        }

        /**
         * The offset translator should ignore the slash characters, so conversion from
         *  original offset to transformed text works like
         *  - The 2nd char of the original text is 3rd char in the transformed text.
         *  - The 4th char of the original text is 5th char in the transformed text.
         *  Similarly, the reverse conversion works like
         *  - The 3rd char of the transformed text is 2nd char in the original text.
         *  - The 5th char of the transformed text is 4th char in the original text.
         */
        val creditCardOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 1) return offset
                if (offset <= 3) return offset + 1
                if (offset <= 8) return offset + 2
                return 10
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 4) return offset - 1
                return 8
            }
        }

        return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
    }
}
