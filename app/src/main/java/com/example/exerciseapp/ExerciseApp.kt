package com.example.exerciseapp

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.exerciseapp.data.AppDatabase
import com.example.exerciseapp.pages.ChoosePlan
import com.example.exerciseapp.pages.DescriptionPage
import com.example.exerciseapp.pages.Exercises
import com.example.exerciseapp.pages.Hard
import com.example.exerciseapp.pages.HomePage
import com.example.exerciseapp.pages.LogInPage
import com.example.exerciseapp.pages.Medium
import com.example.exerciseapp.pages.RegistrationPage
import com.example.exerciseapp.pages.Simple
import com.example.exerciseapp.pages.StartingPage

enum class ExerciseScreen(@StringRes val title: Int) {
    StartingPage(title = R.string.starting_page),
    Registration(title = R.string.registration),
    Login(title = R.string.login),
    Home(title = R.string.home),
    Exercises(title = R.string.exercises),
    ChoosePlan(title = R.string.choose),
    DescriptionPage(title = R.string.desc),
    Simple(title = R.string.simple),
    Medium(title = R.string.medium),
    Hard(title = R.string.hard)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExerciseApp(
    context: Context
) {
    val database = AppDatabase.getDatabase(context)

    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ExerciseScreen.valueOf(
        backStackEntry?.destination?.route ?: ExerciseScreen.StartingPage.name
    )

    Scaffold(
        topBar = {
            ExerciseTopAppBar(
                title = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                navController = navController
            )
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = ExerciseScreen.StartingPage.name
            ) {
                composable(route = ExerciseScreen.StartingPage.name) {
                    StartingPage(
                        onRegistrationClicked = {
                            navController.navigate(ExerciseScreen.Registration.name)
                        },
                        onLoginClicked = {
                            navController.navigate(ExerciseScreen.Login.name)
                        }
                    )
                }

                composable(route = ExerciseScreen.Registration.name) {
                    RegistrationPage(
                        onCancelClicked = {
                            navController.navigate(ExerciseScreen.StartingPage.name)
                        },
                        navController = navController,
                        database = database
                    )
                }

                composable(route = ExerciseScreen.Login.name) {
                    LogInPage(
                        onCancelClicked = {
                            navController.navigate(ExerciseScreen.StartingPage.name)
                        },
                        navController = navController,
                        database = database
                    )
                }

                composable(route = ExerciseScreen.Home.name) {
                    HomePage(
                        onButtonClick = {
                            navController.navigate(ExerciseScreen.Exercises.name)
                        }
                    )
                }

                composable(route = ExerciseScreen.Exercises.name) {
                    Exercises(
                        navController = navController
                    )
                }

                composable(route = ExerciseScreen.DescriptionPage.name) {
                    DescriptionPage(
                        onCancelClicked = {
                            navController.navigate(ExerciseScreen.Exercises.name)
                        },
                        navController = navController,
                        database = database
                    )
                }

                composable(route = ExerciseScreen.ChoosePlan.name) {
                    ChoosePlan(
                        onChoose1Clicked = {
                            navController.navigate(ExerciseScreen.Simple.name)
                        },
                        onChoose2Clicked = {
                            navController.navigate(ExerciseScreen.Medium.name)
                        },
                        onChoose3Clicked = {
                            navController.navigate(ExerciseScreen.Hard.name)
                        }
                    )
                }

                composable(route = ExerciseScreen.Simple.name) {
                    Simple(
                        database = database
                    )
                }

                composable(route = ExerciseScreen.Medium.name) {
                    Medium(
                        database = database
                    )
                }

                composable(route = ExerciseScreen.Hard.name) {
                    Hard(
                        database = database
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseTopAppBar(
    @StringRes title: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    navController: NavController
    ) {
    val bigFont = FontFamily(Font(R.font.open_sans))
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(title),
                    color = Color.White,
                    fontFamily = bigFont,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(30.dp))
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(170.dp)
                        .clickable {
                            navController.navigate(ExerciseScreen.Home.name)
                        }
                )
            }
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF8787E0)
        )
    )
}