package com.example.exerciseapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exerciseapp.R
import com.example.exerciseapp.ui.theme.ExerciseAppTheme

@Composable
fun ChoosePlan(
    onChoose1Clicked: () -> Unit,
    onChoose2Clicked: () -> Unit,
    onChoose3Clicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    val font = FontFamily(Font(R.font.open_sans))
    val titleFont = FontFamily(Font(R.font.roboto))

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
                    text = "Choose Plan",
                    fontSize = 30.sp,
                    modifier = modifier.padding(top = 90.dp),
                    fontFamily = titleFont
                )
                Text(
                    text = "Plans are adapted to your body",
                    fontSize = 15.sp,
                    modifier = modifier
                        .padding(top = 16.dp),
                    fontFamily = font
                )
                Box(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .size(width = 340.dp, height = 220.dp)
                        .background(color = Color(0xFFB2B2DB), shape = RoundedCornerShape(16.dp))
                ) {
                    Column {
                        Text(
                            text = "Simple",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .padding(top = 8.dp),
                            fontFamily = titleFont
                        )
                        Text(
                            text = stringResource(R.string.first_plan),
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(16.dp),
                            textAlign = TextAlign.Justify,
                            fontFamily = font
                        )
                    }
                }
                Button(
                    onClick = onChoose1Clicked,
                    colors = ButtonDefaults.buttonColors(Color(0xFF8787E0)),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(start = 180.dp)
                        .widthIn(120.dp)
                        .heightIn(50.dp)
                ) {
                    Text(
                        text = "Choose Plan",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = font
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .size(width = 340.dp, height = 220.dp)
                        .background(color = Color(0xFFB2B2DB), shape = RoundedCornerShape(16.dp))
                ) {
                    Column {
                        Text(
                            text = "Medium",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .padding(top = 8.dp),
                            fontFamily = titleFont
                        )
                        Text(
                            text = stringResource(R.string.second_plan),
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(16.dp),
                            textAlign = TextAlign.Justify,
                            fontFamily = font
                        )
                    }
                }
                Button(
                    onClick = onChoose2Clicked,
                    colors = ButtonDefaults.buttonColors(Color(0xFF8787E0)),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(start = 180.dp)
                        .widthIn(120.dp)
                        .heightIn(50.dp)
                ) {
                    Text(
                        text = "Choose Plan",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = font
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .size(width = 340.dp, height = 220.dp)
                        .background(color = Color(0xFFB2B2DB), shape = RoundedCornerShape(16.dp))
                ) {
                    Column {
                        Text(
                            text = "Hard",
                            fontSize = 25.sp,
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .padding(top = 16.dp),
                            fontFamily = titleFont
                        )
                        Text(
                            text = stringResource(R.string.third_plan),
                            fontSize = 12.sp,
                            modifier = Modifier
                                .padding(16.dp),
                            textAlign = TextAlign.Justify,
                            fontFamily = font
                        )
                    }
                }
                Button(
                    onClick = onChoose3Clicked,
                    colors = ButtonDefaults.buttonColors(Color(0xFF8787E0)),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(start = 180.dp)
                        .widthIn(120.dp)
                        .heightIn(50.dp)
                ) {
                    Text(
                        text = "Choose Plan",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = font
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview6() {
    ExerciseAppTheme {
        ChoosePlan({},{},{})
    }
}