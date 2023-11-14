package com.example.exerciseapp.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exerciseapp.R
import com.example.exerciseapp.ui.theme.ExerciseAppTheme

@Composable
fun HomePage(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val font = FontFamily(Font(R.font.open_sans))
    val titleFont = FontFamily(Font(R.font.roboto))

    val mainImage = painterResource(R.drawable.main_image)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Welcome To All4Sport!",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 90.dp),
                    fontFamily = titleFont
                )
                Text(
                    text = "We Provide All Tools For Your Body. Free.",
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 16.dp),
                    fontFamily = font
                )
                Image(
                    painter = mainImage,
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 350.dp, height = 200.dp)
                )
                Text(
                    text = "Everything Is Possible!",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontFamily = titleFont
                )
                Text(
                    text = stringResource(R.string.big_text),
                    modifier = Modifier
                        .padding(16.dp)
                        .padding(top = 16.dp),
                    textAlign = TextAlign.Justify,
                    fontSize = 15.sp,
                    fontFamily = font
                )
                Button(
                    onClick = onButtonClick,
                    colors = ButtonDefaults.buttonColors(Color(0xFF8787E0)),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .widthIn(200.dp)
                        .heightIn(40.dp)
                ) {
                    Text(
                        text = "Start Your Journey!",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = font
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview1() {
    ExerciseAppTheme {
        HomePage({})
    }
}
