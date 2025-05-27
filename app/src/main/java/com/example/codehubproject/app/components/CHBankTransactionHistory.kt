package com.example.codehubproject.app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codehubproject.R
import com.example.codehubproject.app.state.CHBankTransactionHistoryList

// Center card
@Composable
fun CHBankTransactionHistory(modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .padding(
                top = 50.dp,
                start = 15.dp,
                end = 15.dp
            )
            .offset(y = (-80).dp)
            .fillMaxWidth()
            .height(225.dp)
            .shadow(
                shape = RoundedCornerShape(12.dp),
                spotColor = Color.Blue,
                ambientColor = Color.Blue,
                elevation = 12.dp
            ),
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(red = 231, green = 239, blue = 232, alpha = 255)
        )
    ) {
        val bankingTransCard = CHBankTransactionHistoryList.CHBankTransactionHistoryList
        val listState = moveViewToLastElemOfLazyCol(bankingTransCard)
        LazyColumn(state = listState) {
            itemsIndexed(bankingTransCard) { idx, item ->
                // To get the spacer elem of height 20.dp below Last transaction.
                if (idx == bankingTransCard.lastIndex) {
                    item()
                    Spacer(Modifier.height(20.dp))
                } else item()
            }
        }
    }
}

@Composable
fun CHBankTransactionHistoryText(
    modifier: Modifier = Modifier,
    timeText: String,
    amountText: String,
    isDeposit: Boolean = true,
    isLoan: Boolean = false,
    transferToUserName: String?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors =
                            if (isDeposit)
                                listOf(
                                    Color(0xFF4EBC7C),
                                    Color(0x9EBDA520)
                                )
                            else if (isLoan)
                                listOf(
                                    Color(0xFF3A7BD5),  // Medium blue
                                    Color(0xFF00D2FF)
                                )
                            else {
                                listOf(
                                    Color(0xFFFF585F),
                                    Color(0xFFFD424B)
                                )
                            }
                        ),
                        shape = RoundedCornerShape(11.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            )
            {
                // To display whether
                if (isDeposit)
                    Text(text = stringResource(R.string.deposit), color = Color.White)
                else
                    Text(text = stringResource(R.string.withdraw), color = Color.White)
            }
            Text(
                text = timeText,
                modifier = Modifier
                    .padding(top = 6.dp, start = 5.dp)
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "$amountText $",
                textAlign = TextAlign.End,
                modifier = Modifier
                    .padding(end = 15.dp, top = 12.dp),
                color = Color(red = 77, green = 77, blue = 77, alpha = 255),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = transferToUserName ?: "",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(end = 14.dp)
            )
        }
    }
}

@Composable
fun moveViewToLastElemOfLazyCol(items: SnapshotStateList<@Composable () -> Unit>): LazyListState {
    val listState: LazyListState = rememberLazyListState()

    LaunchedEffect(items.size) {
        listState.animateScrollToItem(items.lastIndex)
    }
    return listState
}