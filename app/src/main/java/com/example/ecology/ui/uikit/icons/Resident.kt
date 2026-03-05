package com.example.ecology.ui.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Resident: ImageVector
    get() {
        if (_Resident != null) {
            return _Resident!!
        }
        _Resident = ImageVector.Builder(
            name = "Resident",
            defaultWidth = 48.dp,
            defaultHeight = 48.dp,
            viewportWidth = 48f,
            viewportHeight = 48f
        ).apply {
            path(
                fill = SolidColor(Color.White),
                fillAlpha = 0.2f
            ) {
                moveTo(8f, 0f)
                lineTo(40f, 0f)
                arcTo(8f, 8f, 0f, isMoreThanHalf = false, isPositiveArc = true, 48f, 8f)
                lineTo(48f, 40f)
                arcTo(8f, 8f, 0f, isMoreThanHalf = false, isPositiveArc = true, 40f, 48f)
                lineTo(8f, 48f)
                arcTo(8f, 8f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 40f)
                lineTo(0f, 8f)
                arcTo(8f, 8f, 0f, isMoreThanHalf = false, isPositiveArc = true, 8f, 0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF0F172A))) {
                moveTo(16.5f, 32.75f)
                horizontalLineTo(20.25f)
                verticalLineTo(25.25f)
                horizontalLineTo(27.75f)
                verticalLineTo(32.75f)
                horizontalLineTo(31.5f)
                verticalLineTo(21.5f)
                lineTo(24f, 15.875f)
                lineTo(16.5f, 21.5f)
                verticalLineTo(32.75f)
                close()
                moveTo(14f, 35.25f)
                verticalLineTo(20.25f)
                lineTo(24f, 12.75f)
                lineTo(34f, 20.25f)
                verticalLineTo(35.25f)
                horizontalLineTo(25.25f)
                verticalLineTo(27.75f)
                horizontalLineTo(22.75f)
                verticalLineTo(35.25f)
                horizontalLineTo(14f)
                close()
            }
        }.build()

        return _Resident!!
    }

@Suppress("ObjectPropertyName")
private var _Resident: ImageVector? = null
