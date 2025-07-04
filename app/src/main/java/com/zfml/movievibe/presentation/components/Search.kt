package com.zfml.movievibe.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.zfml.movievibe.R
import com.zfml.movievibe.ui.theme.poppinsFontFamily

@Composable
fun Search(
    modifier: Modifier = Modifier,
    searchQuery: String,
    onSearch: (String) -> Unit = {}
) {


    TextField(
        value = searchQuery,
        onValueChange = { text ->
            onSearch(text)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.icon_search),
                contentDescription = "Search",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .scale(1f),
                tint = MaterialTheme.colorScheme.outline
            )
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFE3E3E3),
            unfocusedContainerColor = Color(0xFFE3E3E3),
            focusedIndicatorColor = Color(0xFFE3E3E3),
            unfocusedIndicatorColor = Color(0xFFE3E3E3),
        ),
        placeholder = {
            Text(
                text = "Search Movies",
                fontFamily = poppinsFontFamily,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.outline,
            )
        },
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color("#D1D5DB".toColorInt()),
                shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
            .height(50.dp)
    )


}