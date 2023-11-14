package com.example.exerciseapp.pages

import android.util.Patterns
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.exerciseapp.ExerciseScreen
import com.example.exerciseapp.data.AppDatabase
import com.example.exerciseapp.data.LoginInfo
import com.example.exerciseapp.ui.theme.ExerciseAppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.exerciseapp.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegistrationPage(
    onCancelClicked: () -> Unit,
    modifier: Modifier = Modifier,
    database: AppDatabase,
    navController: NavController
) {
    val font = FontFamily(Font(R.font.open_sans))
    val titleFont = FontFamily(Font(R.font.roboto))

    var nameSurname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val alphabeticRegex = Regex("[a-zA-Z]+")
    val isNameValid = alphabeticRegex.matches(nameSurname)

    val registerButtonClick: () -> Unit = {
        if (nameSurname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            errorMessage = "Please fill in all fields"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage = "Invalid email address"
        } else if (!isNameValid) {
            errorMessage = "Invalid name. Only letters are allowed. No spaces are allowed"
        } else {
            val loginInfo = LoginInfo(id = email, nameSurname = nameSurname, email = email, password = password)
            MainScope().launch {
                try {
                    val existingUser = withContext(Dispatchers.IO) {
                        database.loginDao().getUserId(email)
                    }
                    if (existingUser != null) {
                        errorMessage = "User with the same email already exists"
                    } else {
                        if (password != confirmPassword) {
                            errorMessage = "Passwords do not match"
                        } else {
                            if (password.length < 8) {
                                errorMessage = "Password too short"
                            } else {
                                withContext(Dispatchers.IO) {
                                    database.loginDao().insertUser(loginInfo)
                                }
                                navController.navigate(ExerciseScreen.Home.name)
                            }
                        }
                    }
                } catch (e: Exception) {
                    errorMessage = "Error occurred: ${e.message}"
                }
            }
        }
    }

    Box (
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome!",
                fontSize = 30.sp,
                modifier = modifier.padding(top = 90.dp),
                fontFamily = titleFont
            )
            Text(
                text = "Create Your Account",
                fontSize = 15.sp,
                modifier = modifier.padding(top = 8.dp),
                fontFamily = font
            )
            OutlinedTextField(
                value = nameSurname,
                onValueChange = { nameSurname = it },
                label = { Text("Username",
                    fontFamily = font) },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .widthIn(280.dp),
                placeholder = {
                    Text(text = "Username",
                        fontFamily = font)
                },
                leadingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Face,
                            contentDescription = null
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                singleLine = true
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email",
                    fontFamily = font) },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .widthIn(280.dp),
                placeholder = {
                    Text(text = "Email",
                        fontFamily = font)
                },
                leadingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Email,
                            contentDescription = null
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                singleLine = true
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password",
                    fontFamily = font) },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .widthIn(280.dp),
                placeholder = {
                    Text(text = "Password",
                        fontFamily = font)
                },
                leadingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = null
                        )
                    }
                },
                trailingIcon = {
                    val visibilityIcon = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    IconButton(
                        onClick = { passwordVisible = !passwordVisible },
                        content = {
                            Icon(
                                imageVector = visibilityIcon,
                                contentDescription = null
                            )
                        }
                    )
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                singleLine = true
            )
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password",
                    fontFamily = font) },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .widthIn(280.dp),
                placeholder = {
                    Text(text = "Confirm Password",
                        fontFamily = font)
                },
                leadingIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    registerButtonClick()
                    keyboardController?.hide()
                }),
                singleLine = true
            )
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp),
                fontFamily = font
            )
            Button(
                onClick = registerButtonClick,
                colors = ButtonDefaults.buttonColors(Color(0xFF8787E0)),
                modifier = Modifier
                    .padding(top = 50.dp)
                    .widthIn(200.dp)
                    .heightIn(50.dp)
            ) {
                Text(
                    text = "Register",
                    fontSize = 25.sp,
                    fontFamily = font
                )
            }
            Button(
                onClick = onCancelClicked,
                colors = ButtonDefaults.buttonColors(Color(0xFFB2B2DB)),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .widthIn(200.dp)
                    .heightIn(50.dp)
            ) {
                Text(
                    text = "Cancel",
                    color = Color.Black,
                    fontSize = 25.sp,
                    fontFamily = font
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview3() {
    val context = LocalContext.current
    ExerciseAppTheme {
        RegistrationPage({}, database = AppDatabase.getDatabase(context), navController = rememberNavController())
    }
}