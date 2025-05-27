package com.example.codehubproject.app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codehubproject.R
import com.example.codehubproject.ui.theme.ColorPrimary
import com.example.codehubproject.ui.theme.ColorPrimaryDarker
import com.example.codehubproject.ui.theme.ColorSecondary

@Composable
fun CHBankGreetingText(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = -15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 68.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.CHBank),
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(ColorPrimaryDarker, ColorPrimary, ColorSecondary)
                    )
                ),
                fontSize = 30.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
            Text(
                text = stringResource(R.string.welcome_back, text),
                fontWeight = FontWeight.Medium,
                color = Color(red = 65, green = 65, blue = 65, alpha = 255),
                fontSize = 22.sp,
                modifier = Modifier.padding(top = 1.dp, bottom = 40.dp)
            )
        }
    }
}
