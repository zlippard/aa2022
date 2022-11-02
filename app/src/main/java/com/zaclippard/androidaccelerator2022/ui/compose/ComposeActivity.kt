package com.zaclippard.androidaccelerator2022.ui.compose

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.parcelize.Parcelize

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyNavHost(navController = rememberNavController())
        }
    }
}

enum class NavRoute(val rawValue: String) {
    MAIN("main"),
    DETAILS("details"),
}

@Parcelize
data class Person(
    val firstName: String,
    val lastName: String,
) : Parcelable

@Composable
fun MyNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = NavRoute.MAIN.rawValue,
    ) {
        composable(NavRoute.MAIN.rawValue) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text("This is main!")
                Button(
                    onClick = {
                        val bundle = Bundle()
                        bundle.putParcelable("person", Person("Zac", "Lippard"))
//                        navController.currentBackStackEntry?.arguments?.putParcelable("person", Person("Zac", "Lippard"))
                        navController.navigate(
                            route = NavRoute.DETAILS.rawValue,
                            args = bundle,
                        )
                    }
                ) {
                    Text("Go to Details")
                }
            }
        }
        composable(NavRoute.DETAILS.rawValue) { backStackEntry ->
            val person = backStackEntry.arguments?.getParcelable<Person>("person")
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
            ) {
                Text("Details! Person = $person")
            }
        }
    }
}

