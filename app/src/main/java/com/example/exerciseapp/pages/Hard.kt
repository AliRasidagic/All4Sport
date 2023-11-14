package com.example.exerciseapp.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exerciseapp.R
import com.example.exerciseapp.data.AppDatabase
import com.example.exerciseapp.ui.theme.ExerciseAppTheme

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Hard(
    modifier: Modifier = Modifier,
    database: AppDatabase
) {
    val font = FontFamily(Font(R.font.open_sans))
    val titleFont = FontFamily(Font(R.font.roboto))

    val inclinePress = painterResource(R.drawable.incline_press)
    val splitSquats = painterResource(R.drawable.split_squats)
    val barbellRow = painterResource(R.drawable.barbell_row)
    val arnoldPress = painterResource(R.drawable.get_to_the_choppa)
    val skullCrushers = painterResource(R.drawable.skull_crushers)
    val barbellCurl = painterResource(R.drawable.barbell_curl)
    val deadlift = painterResource(R.drawable.deadlift)

    var height by remember { mutableIntStateOf(0) }
    var weight by remember { mutableIntStateOf(0) }
    var age by remember { mutableIntStateOf(0) }
    var gender by remember { mutableStateOf("") }

    var sets by remember{ mutableIntStateOf(0) }
    var repetitions by remember{ mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        val retrievedHeight = database.loginDao().getHeight()
        val retrievedWeight = database.loginDao().getWeight()
        val retrievedAge = database.loginDao().getAge()
        val retrievedGender = database.loginDao().getGender()

        height = retrievedHeight
        weight = retrievedWeight
        age = retrievedAge
        gender = retrievedGender

        if (height > 180) {
            repetitions = 12
        } else {
            repetitions = 10
        }
        if (weight > 80) {
            repetitions += 2
        } else {
            repetitions -= 2
        }
        if (age < 30) {
            repetitions += 3
        } else if (age in 31..49) {
            repetitions += 1
        } else {
            repetitions -= 1
        }
        if (gender.equals("male", ignoreCase = true)) {
            repetitions += 2
        } else {
            repetitions += 1
        }

        if (height > 180) {
            sets = 2
        } else {
            sets = 1
        }
        if (weight > 80) {
            sets += 1
        }
        if (age < 30) {
            sets += 1
        }
        if (gender.equals("female", ignoreCase = true)) {
            sets -= 1
        }
        if (sets <= 0) {
            sets = 1
        }
    }

    Box (
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ){
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Hard Plan",
                    fontSize = 30.sp,
                    modifier = modifier.padding(top = 90.dp),
                    fontFamily = titleFont
                )
                Text(
                    text = "Check out your customized plan. Good luck!",
                    fontSize = 15.sp,
                    modifier = modifier.padding(top = 16.dp),
                    fontFamily = font
                )
                Box(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .size(width = 340.dp, height = 280.dp)
                        .background(color = Color(0xFFB2B2DB), shape = RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Incline Barbell Bench Press",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .padding(10.dp),
                            fontFamily = titleFont
                        )
                        Row {
                            Text(
                                text = stringResource(R.string.hard_inclinePress),
                                modifier = Modifier
                                    .size(height = 200.dp, width = 180.dp)
                                    .padding(10.dp),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Justify,
                                fontFamily = font
                            )
                            Image(
                                painter = inclinePress,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp)
                            )
                        }
                        Text(
                            text = "Repetitions: " + sets + "x" + repetitions,
                            fontFamily = font,
                            fontSize = 15.sp
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .size(width = 340.dp, height = 280.dp)
                        .background(color = Color(0xFFB2B2DB), shape = RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Bulgarian Split Squat",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .padding(10.dp),
                            fontFamily = titleFont
                        )
                        Row {
                            Text(
                                text = stringResource(R.string.hard_splitSquat),
                                modifier = Modifier
                                    .size(height = 200.dp, width = 180.dp)
                                    .padding(10.dp),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Justify,
                                fontFamily = font
                            )
                            Image(
                                painter = splitSquats,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp)
                            )
                        }
                        Text(
                            text = "Repetitions: " + sets + "x" + repetitions,
                            fontFamily = font,
                            fontSize = 15.sp
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .size(width = 340.dp, height = 280.dp)
                        .background(color = Color(0xFFB2B2DB), shape = RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Barbell Row",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .padding(10.dp),
                            fontFamily = titleFont
                        )
                        Row {
                            Text(
                                text = stringResource(R.string.hard_barbellRow),
                                modifier = Modifier
                                    .size(height = 200.dp, width = 180.dp)
                                    .padding(10.dp),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Justify,
                                fontFamily = font
                            )
                            Image(
                                painter = barbellRow,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp)
                            )
                        }
                        Text(
                            text = "Repetitions: " + sets + "x" + repetitions,
                            fontFamily = font,
                            fontSize = 15.sp
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .size(width = 340.dp, height = 280.dp)
                        .background(color = Color(0xFFB2B2DB), shape = RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Arnold Press",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .padding(10.dp),
                            fontFamily = titleFont
                        )
                        Row {
                            Text(
                                text = stringResource(R.string.hard_getToTheChoppa),
                                modifier = Modifier
                                    .size(height = 200.dp, width = 180.dp)
                                    .padding(10.dp),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Justify,
                                fontFamily = font
                            )
                            Image(
                                painter = arnoldPress,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp)
                            )
                        }
                        Text(
                            text = "Repetitions: " + sets + "x" + repetitions,
                            fontFamily = font,
                            fontSize = 15.sp
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .size(width = 340.dp, height = 280.dp)
                        .background(color = Color(0xFFB2B2DB), shape = RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "EZ Bar Skull Crushers",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .padding(10.dp),
                            fontFamily = titleFont
                        )
                        Row {
                            Text(
                                text = stringResource(R.string.hard_skullCrushers),
                                modifier = Modifier
                                    .size(height = 200.dp, width = 180.dp)
                                    .padding(10.dp),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Justify,
                                fontFamily = font
                            )
                            Image(
                                painter = skullCrushers,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp)
                            )
                        }
                        Text(
                            text = "Repetitions: " + sets + "x" + repetitions,
                            fontFamily = font,
                            fontSize = 15.sp
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .size(width = 340.dp, height = 280.dp)
                        .background(color = Color(0xFFB2B2DB), shape = RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Barbell Curl",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .padding(10.dp),
                            fontFamily = titleFont
                        )
                        Row {
                            Text(
                                text = stringResource(R.string.hard_barbellCurl),
                                modifier = Modifier
                                    .size(height = 200.dp, width = 180.dp)
                                    .padding(10.dp),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Justify,
                                fontFamily = font
                            )
                            Image(
                                painter = barbellCurl,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp)
                            )
                        }
                        Text(
                            text = "Repetitions: " + sets + "x" + repetitions,
                            fontFamily = font,
                            fontSize = 15.sp
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .size(width = 340.dp, height = 280.dp)
                        .background(color = Color(0xFFB2B2DB), shape = RoundedCornerShape(16.dp))
                ) {
                    Column(
                        modifier = modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Deadlift",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .padding(10.dp),
                            fontFamily = titleFont
                        )
                        Row {
                            Text(
                                text = stringResource(R.string.hard_deadlift),
                                modifier = Modifier
                                    .size(height = 200.dp, width = 180.dp)
                                    .padding(10.dp),
                                fontSize = 12.sp,
                                textAlign = TextAlign.Justify,
                                fontFamily = font
                            )
                            Image(
                                painter = deadlift,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(200.dp)
                            )
                        }
                        Text(
                            text = "Repetitions: " + sets + "x" + repetitions,
                            fontFamily = font,
                            fontSize = 15.sp
                        )
                    }
                }
                Text(
                    text = "Note: If you choose to build muscle, focus more on exercises 1 and 2. If you choose to maintain bodyweight, focus on 3 and 4. And if you choose to lose weight; your focus should be on exercises 5, 6 and 7.",
                    fontSize = 15.sp,
                    color = Color.Red,
                    modifier = modifier.padding(16.dp),
                    fontFamily = font,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview10() {
    val context = LocalContext.current
    ExerciseAppTheme {
        Hard(database = AppDatabase.getDatabase(context))
    }
}