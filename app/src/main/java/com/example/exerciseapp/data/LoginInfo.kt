package com.example.exerciseapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LoginInfo")
data class LoginInfo(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "Name and Surname") val nameSurname: String,
    @ColumnInfo(name = "E-mail") val email: String,
    @ColumnInfo(name = "Password") val password: String,
)

@Entity(tableName = "UserInfo")
data class UserInfo(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id1") val id1: Int = 0,
    @ColumnInfo(name="height") val height: Int,
    @ColumnInfo(name="weight") val weight: Int,
    @ColumnInfo(name="age") val age: Int,
    @ColumnInfo(name="gender") val gender: String
)