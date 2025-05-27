package com.example.codehubproject.app.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TextFieldDefaults.TextFieldDecorationBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.codehubproject.ui.theme.ColorTextPrimaryBackground

@Composable
fun CHBankOperations(
    modifier: Modifier = Modifier,
    linearGradientColor1: Color,
    linearGradientColor2: Color,
    @StringRes operationText: Int,
    @StringRes placeHolderText1: Int,
    @StringRes placeHolderText2: Int?,
    textState1: MutableState<String>,
    textState2: MutableState<String>?,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(
                top = 16.dp,
                start = 15.dp,
                end = 15.dp
            )
            .fillMaxWidth()
            .height(100.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        colors = listOf(linearGradientColor1, linearGradientColor2)
                    )
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(operationText),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = (-5).dp),
            ) {
                CHBankOperationTextField(
                    textState1,
                    Modifier
                        .padding(start = 12.dp, bottom = 6.dp),
                    placeHolderText = placeHolderText1
                )
                if (placeHolderText2 != null && textState2 != null) {
                    CHBankOperationTextField(
                        textState2,
                        Modifier
                            .padding(start = 12.dp, bottom = 6.dp),
                        placeHolderText = placeHolderText2
                    )
                }
                CHBankOperationIconButton(
                    btnIcon = Icons.Filled.ArrowForwardIos,
                    onClick = onClick
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun CHBankOperationTextField(
    textValue: MutableState<String>,
    modifier: Modifier = Modifier,
    @StringRes placeHolderText: Int
) {
    // For keyboard events -> Enter
    val (focusRequester: FocusRequester) = FocusRequester.createRefs()

    val interactionSource = remember { MutableInteractionSource() }

    BasicTextField(
        value = textValue.value,
        onValueChange = { textValue.value = it },
        modifier = modifier
            .padding(top = 12.dp)
            .width(150.dp)
            .height(40.dp) // compact height
            .clip(
                RoundedCornerShape(
                    topStart = 6.dp,
                    topEnd = 6.dp,
                    bottomStart = 8.dp,
                    bottomEnd = 8.dp
                )
            )
            .background(ColorTextPrimaryBackground)
            .onKeyEvent {
                println(it)
                true
            },
        textStyle = LocalTextStyle.current.copy(
            fontSize = 14.sp,
            color = Color.Black,
            lineHeight = 18.sp
        ),
        singleLine = true,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusRequester.requestFocus()
            }
        ),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(Modifier.weight(1f)) {

                    TextFieldDecorationBox(
                        value = textValue.value,
                        innerTextField = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(horizontal = 8.dp, vertical = 8.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                innerTextField()
                            }
                        },
                        enabled = true,
                        singleLine = true,
                        visualTransformation = VisualTransformation.None,
                        interactionSource = interactionSource,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        contentPadding = PaddingValues(0.dp), // control inner spacing,
                        placeholder = {
                            Text(
                                modifier = Modifier.padding(
                                    start = 9.dp
                                ),
                                text = stringResource(placeHolderText),
                            )
                        }
                    )
                }
            }
        }
    )

    println("Hello")
}

@Composable
fun CHBankOperationIconButton(
    btnIcon: ImageVector,
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier.offset(y = 9.dp, x = 6.dp),
        onClick = onClick,
    ) {
        Icon(
            imageVector = btnIcon,
            contentDescription = null,
        )
    }
}