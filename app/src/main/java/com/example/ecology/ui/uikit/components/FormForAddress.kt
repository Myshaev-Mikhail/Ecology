package com.example.ecology.ui.uikit.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.ecology.R

@Composable
fun FormForAddress(
    district: String = "",
    street: String,
    house: String,
    onDistrictChange: (String) -> Unit,
    onStreetChange: (String) -> Unit,
    onHouseChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.white), RoundedCornerShape(14.dp))
            .border(1.dp, colorResource(id = R.color.border), RoundedCornerShape(14.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Text(
                text = "Район",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            BasicTextField(
                value = district,
                onValueChange = onDistrictChange,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters,
                ),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = colorResource(id = R.color.black)
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, colorResource(id = R.color.border), RoundedCornerShape(18.dp))
                    .background(colorResource(id = R.color.white_background), RoundedCornerShape(18.dp))
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Улица",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            BasicTextField(
                value = street,
                onValueChange = onStreetChange,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Characters,
                ),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = colorResource(id = R.color.black)
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, colorResource(id = R.color.border), RoundedCornerShape(18.dp))
                    .background(colorResource(id = R.color.white_background), RoundedCornerShape(18.dp))
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Дом",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(4.dp))

            BasicTextField(
                value = house,
                onValueChange = onHouseChange,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone,
                ),
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = colorResource(id = R.color.black)
                ),
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, colorResource(id = R.color.border), RoundedCornerShape(18.dp))
                    .background(colorResource(id = R.color.white_background), RoundedCornerShape(18.dp))
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            )
        }
    }
}