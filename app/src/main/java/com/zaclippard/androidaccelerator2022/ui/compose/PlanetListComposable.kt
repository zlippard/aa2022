package com.zaclippard.androidaccelerator2022.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zaclippard.androidaccelerator2022.models.Planet

@Composable
fun PlanetListComposable(
    planets: List<Planet>,
) {
    var title by rememberSaveable { mutableStateOf("Some Title") }
    Column {
        Text(title)
        LazyColumn(
            // This weight modifier
            // allows the button below to appear
            // otherwise the LazyColumn takes up
            // the remaining height.
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(planets) { index, planet ->
                Text("${planet.name} $index")
                Text("${planet.name} $index")
                Text("${planet.name} $index")
            }
        }
        Button(onClick = {
            title = "A New Title"
        }) {
            Text("Change header")
        }
    }
}

@Preview
@Composable
fun PreviewList() {
    PlanetListComposable(
        listOf(
            Planet("Venus", "Hot", "Rocky", "12000", "0"),
            Planet("Earth", "Warm", "Rocky", "12000", "7000000000"),
            Planet("Earth", "Cold", "Rocky", "8000", "0"),
        )
    )
}
