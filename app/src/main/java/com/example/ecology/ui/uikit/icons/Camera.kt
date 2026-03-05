package com.example.ecology.ui.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Camera: ImageVector
    get() {
        if (_Camera != null) {
            return _Camera!!
        }
        _Camera = ImageVector.Builder(
            name = "Camera",
            defaultWidth = 28.dp,
            defaultHeight = 25.dp,
            viewportWidth = 28f,
            viewportHeight = 25f
        ).apply {
            path(fill = SolidColor(Color(0xFF94A3B8))) {
                moveTo(2.5f, 25f)
                curveTo(1.813f, 25f, 1.224f, 24.755f, 0.734f, 24.266f)
                curveTo(0.245f, 23.776f, 0f, 23.188f, 0f, 22.5f)
                verticalLineTo(7.5f)
                curveTo(0f, 6.813f, 0.245f, 6.224f, 0.734f, 5.734f)
                curveTo(1.224f, 5.245f, 1.813f, 5f, 2.5f, 5f)
                horizontalLineTo(6.438f)
                lineTo(8.75f, 2.5f)
                horizontalLineTo(16.25f)
                verticalLineTo(5f)
                horizontalLineTo(9.844f)
                lineTo(7.563f, 7.5f)
                horizontalLineTo(2.5f)
                verticalLineTo(22.5f)
                horizontalLineTo(22.5f)
                verticalLineTo(11.25f)
                horizontalLineTo(25f)
                verticalLineTo(22.5f)
                curveTo(25f, 23.188f, 24.755f, 23.776f, 24.266f, 24.266f)
                curveTo(23.776f, 24.755f, 23.188f, 25f, 22.5f, 25f)
                horizontalLineTo(2.5f)
                close()
                moveTo(22.5f, 7.5f)
                verticalLineTo(5f)
                horizontalLineTo(20f)
                verticalLineTo(2.5f)
                horizontalLineTo(22.5f)
                verticalLineTo(0f)
                horizontalLineTo(25f)
                verticalLineTo(2.5f)
                horizontalLineTo(27.5f)
                verticalLineTo(5f)
                horizontalLineTo(25f)
                verticalLineTo(7.5f)
                horizontalLineTo(22.5f)
                close()
                moveTo(12.5f, 20.625f)
                curveTo(14.063f, 20.625f, 15.391f, 20.078f, 16.484f, 18.984f)
                curveTo(17.578f, 17.891f, 18.125f, 16.563f, 18.125f, 15f)
                curveTo(18.125f, 13.438f, 17.578f, 12.109f, 16.484f, 11.016f)
                curveTo(15.391f, 9.922f, 14.063f, 9.375f, 12.5f, 9.375f)
                curveTo(10.938f, 9.375f, 9.609f, 9.922f, 8.516f, 11.016f)
                curveTo(7.422f, 12.109f, 6.875f, 13.438f, 6.875f, 15f)
                curveTo(6.875f, 16.563f, 7.422f, 17.891f, 8.516f, 18.984f)
                curveTo(9.609f, 20.078f, 10.938f, 20.625f, 12.5f, 20.625f)
                close()
                moveTo(12.5f, 18.125f)
                curveTo(11.625f, 18.125f, 10.885f, 17.823f, 10.281f, 17.219f)
                curveTo(9.677f, 16.615f, 9.375f, 15.875f, 9.375f, 15f)
                curveTo(9.375f, 14.125f, 9.677f, 13.385f, 10.281f, 12.781f)
                curveTo(10.885f, 12.177f, 11.625f, 11.875f, 12.5f, 11.875f)
                curveTo(13.375f, 11.875f, 14.115f, 12.177f, 14.719f, 12.781f)
                curveTo(15.323f, 13.385f, 15.625f, 14.125f, 15.625f, 15f)
                curveTo(15.625f, 15.875f, 15.323f, 16.615f, 14.719f, 17.219f)
                curveTo(14.115f, 17.823f, 13.375f, 18.125f, 12.5f, 18.125f)
                close()
            }
        }.build()

        return _Camera!!
    }

@Suppress("ObjectPropertyName")
private var _Camera: ImageVector? = null
