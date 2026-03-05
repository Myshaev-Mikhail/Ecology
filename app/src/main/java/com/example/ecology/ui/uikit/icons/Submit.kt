package com.example.ecology.ui.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Submit: ImageVector
    get() {
        if (_Submit != null) {
            return _Submit!!
        }
        _Submit = ImageVector.Builder(
            name = "Submit",
            defaultWidth = 19.dp,
            defaultHeight = 16.dp,
            viewportWidth = 19f,
            viewportHeight = 16f
        ).apply {
            path(fill = SolidColor(Color(0xFF0F172A))) {
                moveTo(0f, 16f)
                verticalLineTo(0f)
                lineTo(19f, 8f)
                lineTo(0f, 16f)
                close()
                moveTo(2f, 13f)
                lineTo(13.85f, 8f)
                lineTo(2f, 3f)
                verticalLineTo(6.5f)
                lineTo(8f, 8f)
                lineTo(2f, 9.5f)
                verticalLineTo(13f)
                close()
                moveTo(2f, 13f)
                verticalLineTo(8f)
                verticalLineTo(3f)
                verticalLineTo(6.5f)
                verticalLineTo(9.5f)
                verticalLineTo(13f)
                close()
            }
        }.build()

        return _Submit!!
    }

@Suppress("ObjectPropertyName")
private var _Submit: ImageVector? = null
