package com.example.ecology.ui.uikit.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Icons.Media: ImageVector
    get() {
        if (_Media != null) {
            return _Media!!
        }
        _Media = ImageVector.Builder(
            name = "Media",
            defaultWidth = 48.dp,
            defaultHeight = 48.dp,
            viewportWidth = 48f,
            viewportHeight = 48f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF13EC13)),
                fillAlpha = 0.1f
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
            path(fill = SolidColor(Color(0xFF13EC13))) {
                moveTo(24f, 30.875f)
                curveTo(25.563f, 30.875f, 26.891f, 30.328f, 27.984f, 29.234f)
                curveTo(29.078f, 28.141f, 29.625f, 26.813f, 29.625f, 25.25f)
                curveTo(29.625f, 23.688f, 29.078f, 22.359f, 27.984f, 21.266f)
                curveTo(26.891f, 20.172f, 25.563f, 19.625f, 24f, 19.625f)
                curveTo(22.438f, 19.625f, 21.109f, 20.172f, 20.016f, 21.266f)
                curveTo(18.922f, 22.359f, 18.375f, 23.688f, 18.375f, 25.25f)
                curveTo(18.375f, 26.813f, 18.922f, 28.141f, 20.016f, 29.234f)
                curveTo(21.109f, 30.328f, 22.438f, 30.875f, 24f, 30.875f)
                close()
                moveTo(24f, 28.375f)
                curveTo(23.125f, 28.375f, 22.385f, 28.073f, 21.781f, 27.469f)
                curveTo(21.177f, 26.865f, 20.875f, 26.125f, 20.875f, 25.25f)
                curveTo(20.875f, 24.375f, 21.177f, 23.635f, 21.781f, 23.031f)
                curveTo(22.385f, 22.427f, 23.125f, 22.125f, 24f, 22.125f)
                curveTo(24.875f, 22.125f, 25.615f, 22.427f, 26.219f, 23.031f)
                curveTo(26.823f, 23.635f, 27.125f, 24.375f, 27.125f, 25.25f)
                curveTo(27.125f, 26.125f, 26.823f, 26.865f, 26.219f, 27.469f)
                curveTo(25.615f, 28.073f, 24.875f, 28.375f, 24f, 28.375f)
                close()
                moveTo(14f, 35.25f)
                curveTo(13.313f, 35.25f, 12.724f, 35.005f, 12.234f, 34.516f)
                curveTo(11.745f, 34.026f, 11.5f, 33.438f, 11.5f, 32.75f)
                verticalLineTo(17.75f)
                curveTo(11.5f, 17.063f, 11.745f, 16.474f, 12.234f, 15.984f)
                curveTo(12.724f, 15.495f, 13.313f, 15.25f, 14f, 15.25f)
                horizontalLineTo(17.938f)
                lineTo(20.25f, 12.75f)
                horizontalLineTo(27.75f)
                lineTo(30.063f, 15.25f)
                horizontalLineTo(34f)
                curveTo(34.688f, 15.25f, 35.276f, 15.495f, 35.766f, 15.984f)
                curveTo(36.255f, 16.474f, 36.5f, 17.063f, 36.5f, 17.75f)
                verticalLineTo(32.75f)
                curveTo(36.5f, 33.438f, 36.255f, 34.026f, 35.766f, 34.516f)
                curveTo(35.276f, 35.005f, 34.688f, 35.25f, 34f, 35.25f)
                horizontalLineTo(14f)
                close()
                moveTo(14f, 32.75f)
                horizontalLineTo(34f)
                verticalLineTo(17.75f)
                horizontalLineTo(28.938f)
                lineTo(26.656f, 15.25f)
                horizontalLineTo(21.344f)
                lineTo(19.063f, 17.75f)
                horizontalLineTo(14f)
                verticalLineTo(32.75f)
                close()
            }
        }.build()

        return _Media!!
    }

@Suppress("ObjectPropertyName")
private var _Media: ImageVector? = null
