package com.example.codehubproject.app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codehubproject.R

@Composable
fun CHBankBalanceText(
    fontSize: TextUnit? = 20.sp,
    red: Int,
    green: Int,
    blue: Int,
    alpha: Int,
    text: String,
    fontWeight: FontWeight? = FontWeight.Medium,
    modifier: Modifier
) {
    Text(
        text = text,
        color = Color(
            red = red,
            green = green,
            blue = blue,
            alpha = alpha
        ),
        fontSize = fontSize ?: TextUnit.Unspecified,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun CHBankBalance(
    modifier: Modifier = Modifier,
    amount: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 20.dp)
            .offset(y = -38.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            CHBankBalanceText(
                text = stringResource(R.string.current_balance),
                red = 65,
                green = 65,
                blue = 65,
                alpha = 255,
                modifier = Modifier.padding(start = 15.dp)
            )

            CHBankBalanceText(
                text = stringResource(R.string.date_text),
                red = 114, green = 114,
                blue = 114, alpha = 255,
                modifier = Modifier.padding(start = 15.dp),
                fontSize = null
            )
        }
        CHBankBalanceText(
            text = amount, red = 78,
            blue = 78, green = 78, alpha = 255,
            modifier = Modifier
                .padding(end = 15.dp, top = 12.dp)
        )
    }
}