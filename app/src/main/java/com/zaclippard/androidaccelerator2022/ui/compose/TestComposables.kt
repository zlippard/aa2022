package com.zaclippard.androidaccelerator2022.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.zaclippard.androidaccelerator2022.R

@Composable
fun TestBoxComposable() {
    Box(
        modifier = Modifier.background(Color.Blue)
    ) {
        Text(
            color = Color.Black,
            text = "Text 1",
//            modifier = Modifier.background(Color.Red),
        )
        Text(
            color = Color.Green,
            text = "More Text 2",
//            modifier = Modifier.background(Color.Red),
        )
    }
}

@Preview
@Composable
fun PreviewBox() {
    TestBoxComposable()
}

@Composable
fun TestRowComposable() {
    Row(
        modifier = Modifier.background(Color.Blue)
    ) {
        Text(
            color = Color.Black,
            text = "Text 1",
//            modifier = Modifier.background(Color.Red),
        )
        Text(
            color = Color.Green,
            text = "More Text 2",
//            modifier = Modifier.background(Color.Red),
        )
    }
}

@Preview
@Composable
fun PreviewRow() {
    TestRowComposable()
}

@Composable
fun TestColumnComposable() {
    Column(
        modifier = Modifier.background(Color.Blue)
    ) {
        Text(
            color = Color.Black,
            text = "Text 1",
//            modifier = Modifier.background(Color.Red),
        )
        Text(
            color = Color.Green,
            text = "More Text 2",
//            modifier = Modifier.background(Color.Red),
        )
    }
}

@Preview
@Composable
fun PreviewColumn() {
    TestColumnComposable()
}

@Composable
fun TestAvatarComposable() {
    Row(
        modifier = Modifier
            .background(Color.Blue)
            .clickable {
                       // do something
//                       onTap()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Avatar"
        )
        Column {
            Text(
                color = Color.Black,
                text = "Text 1",
//            modifier = Modifier.background(Color.Red),
            )
            Text(
                color = Color.Green,
                text = "More Text 2",
//            modifier = Modifier.background(Color.Red),
            )
        }
    }
}

@Preview
@Composable
fun PreviewAvatar() {
    TestAvatarComposable()
}

