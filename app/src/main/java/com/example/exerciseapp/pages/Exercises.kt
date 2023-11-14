package com.example.exerciseapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.exerciseapp.ExerciseScreen
import com.example.exerciseapp.R
import com.example.exerciseapp.ui.theme.ExerciseAppTheme

@Composable
fun Exercises(
    navController: NavController,
) {
    val font = FontFamily(Font(R.font.open_sans))
    val titleFont = FontFamily(Font(R.font.roboto))

    val exercises1 = painterResource(R.drawable.exercises_1)
    val exercises2 = painterResource(R.drawable.exercises_2)
    val exercises3 = painterResource(R.drawable.exercises_3)

    Box (
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item {
                Text(
                    text = "Choose Your Goal!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 90.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontFamily = titleFont
                )
                Text(
                    text = "Click on Image to Select Goal",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp,
                    fontFamily = font
                )
                Text(
                    text = "Build Muscle",
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .clickable {
                            navController.navigate(ExerciseScreen.DescriptionPage.name)
                        },
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontFamily = titleFont
                )
                Image(
                    painter = exercises1,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .padding(start = 16.dp)
                        .size(width = 250.dp, height = 250.dp)
                        .clickable {
                            navController.navigate(ExerciseScreen.DescriptionPage.name)
                        }
                )
                Text(
                    text = "Maintain Body-weight",
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .clickable {
                            navController.navigate(ExerciseScreen.DescriptionPage.name)
                        },
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontFamily = titleFont
                )
                Image(
                    painter = exercises2,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .padding(start = 16.dp)
                        .size(width = 250.dp, height = 250.dp)
                        .clickable {
                            navController.navigate(ExerciseScreen.DescriptionPage.name)
                        }
                )
                Text(
                    text = "Lose Weight",
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .clickable {
                            navController.navigate(ExerciseScreen.DescriptionPage.name)
                        },
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontFamily = titleFont
                )
                Image(
                    painter = exercises3,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .padding(start = 16.dp)
                        .size(width = 250.dp, height = 250.dp)
                        .clickable {
                            navController.navigate(ExerciseScreen.DescriptionPage.name)
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview2() {
    ExerciseAppTheme {
        val navController = rememberNavController()
        ExerciseAppTheme {
            Exercises(navController = navController)
        }
    }
}