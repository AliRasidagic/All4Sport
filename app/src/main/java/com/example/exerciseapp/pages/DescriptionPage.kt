package com.example.exerciseapp.pages

import android.annotation.SuppressLint
import android.text.TextUtils
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.exerciseapp.ExerciseScreen
import com.example.exerciseapp.R
import com.example.exerciseapp.data.AppDatabase
import com.example.exerciseapp.data.UserInfo
import com.example.exerciseapp.ui.theme.ExerciseAppTheme
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DescriptionPage(
    onCancelClicked: () -> Unit,
    database: AppDatabase,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val font = FontFamily(Font(R.font.open_sans))
    val titleFont = FontFamily(Font(R.font.roboto))

    val manIcon = painterResource(R.drawable.man_icon)

    var height by rememberSaveable { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    val heightInt = height.toIntOrNull()
    val weightInt = weight.toIntOrNull()
    val ageInt = age.toIntOrNull()

    var errorMessage by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val weightFocusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    val onResetClicked: () -> Unit = {
        MainScope().launch {
            database.loginDao().deleteAllUserInfo()
            height = ""
            weight = ""
            age = ""
            gender = ""
        }
    }

    val submitButtonClick: () -> Unit = {
        if (height.isEmpty() || weight.isEmpty() || age.isEmpty() || gender.isEmpty()) {
            errorMessage = "Please fill in all fields"
        } else if (!TextUtils.isDigitsOnly(height) || !TextUtils.isDigitsOnly(weight) || !TextUtils.isDigitsOnly(age)) {
            errorMessage = "Please enter numeric values for height, weight and age"
        } else if (heightInt == null || heightInt < 130 || heightInt > 220) {
            errorMessage = "Please input your real height"
        } else if (weightInt == null || weightInt < 30 || weightInt > 130) {
            errorMessage = "Please input your real weight"
        } else if (ageInt == null || ageInt < 15 || ageInt > 85) {
            errorMessage = "Please input your real age"
        } else if (gender != "male" && gender != "female" && gender != "Male" && gender != "Female") {
            errorMessage = "Please input your gender as male or female"
        } else {
            val userInfo = UserInfo(height = height.toInt(), weight = weight.toInt(), age = age.toInt(), gender = gender)
            MainScope().launch {
                try {
                    database.loginDao().insertInfo(userInfo)
                    navController.navigate(ExerciseScreen.ChoosePlan.name)
                } catch (e: Exception) {
                    errorMessage = "Error occurred: ${e.message}"
                }
            }
        }
    }

    Box {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Insert Your Information",
                    modifier = Modifier
                        .padding(top = 90.dp),
                    fontSize = 30.sp,
                    fontFamily = titleFont
                )
                Text(
                    text = "Click `reset values` if you want to change your information",
                    modifier = Modifier
                        .padding(16.dp),
                    fontSize = 15.sp,
                    fontFamily = font,
                    textAlign = TextAlign.Center
                )
                Image(
                    painter = manIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 220.dp, height = 220.dp)
                        .padding(top = 16.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = height,
                        onValueChange = { height = it },
                        label = {
                            Text(
                                "Height (CM)",
                                fontFamily = font
                            )
                        },
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .widthIn(100.dp),
                        placeholder = {
                            Text(
                                text = "Height",
                                fontFamily = font
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(
                                FocusDirection.Right
                            )
                        }),
                        singleLine = true,
                    )
                    OutlinedTextField(
                        value = weight,
                        onValueChange = { weight = it },
                        label = {
                            Text(
                                "Weight (KG)",
                                fontFamily = font
                            )
                        },
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(start = 16.dp)
                            .widthIn(100.dp),
                        placeholder = {
                            Text(
                                text = "Weight",
                                fontFamily = font
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = { weightFocusRequester.requestFocus() }),
                        singleLine = true,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = age,
                        onValueChange = { age = it },
                        label = {
                            Text(
                                "Age",
                                fontFamily = font
                            )
                        },
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .widthIn(100.dp)
                            .focusRequester(weightFocusRequester),
                        placeholder = {
                            Text(
                                text = "Age",
                                fontFamily = font
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(
                                FocusDirection.Right
                            )
                        }),
                        singleLine = true,
                    )
                    OutlinedTextField(
                        value = gender,
                        onValueChange = { gender = it },
                        label = {
                            Text(
                                "Gender",
                                fontFamily = font
                            )
                        },
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(start = 16.dp)
                            .widthIn(100.dp),
                        placeholder = {
                            Text(
                                text = "Gender",
                                fontFamily = font
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = {
                            submitButtonClick()
                            keyboardController?.hide()
                        }),
                        singleLine = true,
                    )
                }
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 8.dp),
                    fontFamily = font
                )
                Row(
                    modifier = Modifier
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = onCancelClicked,
                        colors = ButtonDefaults.buttonColors(Color(0xFFB2B2DB)),
                        modifier = Modifier
                            .widthIn(150.dp)
                            .heightIn(50.dp)
                            .padding(end = 8.dp)
                    ) {
                        Text(
                            text = "Cancel",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontFamily = font
                        )
                    }
                    Button(
                        onClick = submitButtonClick,
                        colors = ButtonDefaults.buttonColors(Color(0xFF8787E0)),
                        modifier = Modifier
                            .widthIn(150.dp)
                            .heightIn(50.dp)
                            .padding(start = 8.dp),
                    ) {
                        Text(
                            text = "Submit",
                            fontSize = 20.sp,
                            fontFamily = font
                        )
                    }
                }
                Text(
                    text = "Reset Values",
                    textDecoration = TextDecoration.Underline,
                    color = Color.Red,
                    fontSize = 20.sp,
                    fontFamily = font,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable {
                            onResetClicked()
                        }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview5() {
    val context = LocalContext.current
    ExerciseAppTheme {
        DescriptionPage({}, database = AppDatabase.getDatabase(context), navController = rememberNavController())
    }
}