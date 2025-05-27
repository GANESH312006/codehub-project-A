package com.example.codehubproject.app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.codehubproject.R
import com.example.codehubproject.ui.theme.ColorPrimaryDarker
import com.example.codehubproject.ui.theme.ColorTertiaryDarker

@Composable
// TODO: Refactor code.
fun CHBankSummaryText(
    modifier: Modifier = Modifier.offset(y = (-90).dp),
    inAmount: String,
    outAmount: String,
    interest: String,
    currencyFormatter: (Double) -> String = { "%.2f".format(it) }
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.`in`),
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = inAmount,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally),
            color = ColorPrimaryDarker
        )
        Text(
            text = stringResource(R.string.out),
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = outAmount,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally),
            color = ColorTertiaryDarker
        )
        Text(
            text = stringResource(R.string.interest),
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        Text(
            text = interest,
            modifier = Modifier
                .weight(1f)
                .wrapContentWidth(Alignment.CenterHorizontally),
            color = ColorPrimaryDarker
        )
    }
}