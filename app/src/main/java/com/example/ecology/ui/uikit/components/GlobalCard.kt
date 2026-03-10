package com.example.ecology.ui.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ecology.R

@Composable
fun GlobalCard(
    district: String,
    street: String,
    house: String,
    description: String,
    imageUrl: String?,
    reportsCount: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(28.dp))
            .border(
                width = 3.dp,
                color = Color(0xFF9BE3A7),
                shape = RoundedCornerShape(28.dp)
            )
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF4F5F6)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(20f.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(20.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(28.dp))
                        .border(
                            width = 1.dp,
                            color = colorResource(id = R.color.lime).copy(alpha = 0.3f),
                            shape = RoundedCornerShape(28.dp)
                        )
                        .background(colorResource(id = R.color.lime).copy(alpha = 0.1f))
                        .width(80.dp)
                        .height(25.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = reportsText(reportsCount),
                        style = MaterialTheme.typography.bodyMedium,
                        color = colorResource(id = R.color.lime)
                    )
                }
                Text(
                    text = "$district, улица $street $house",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF111827)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF6B7280),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

fun reportsText(count: Int): String {
    val mod10 = count % 10
    val mod100 = count % 100

    val word = when {
        mod10 == 1 && mod100 != 11 -> "отчет"
        mod10 in 2..4 && mod100 !in 12..14 -> "отчета"
        else -> "отчетов"
    }

    return "$count $word"
}