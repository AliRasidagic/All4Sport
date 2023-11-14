package com.example.exerciseapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LoginDao {
    @Query("SELECT * FROM LoginInfo")
    fun getAll(): Flow<List<LoginInfo>>

    @Query("SELECT * FROM UserInfo")
    fun getAllUsers(): Flow<List<UserInfo>>

    @Query("SELECT * FROM LoginInfo WHERE `E-mail` = :email AND Password = :password")
    suspend fun getUser(email: String, password: String): LoginInfo?

    @Query("SELECT * FROM LoginInfo WHERE `E-mail` = :email")
    suspend fun getUserId(email: String): LoginInfo?

    @Query("SELECT `Height` FROM UserInfo")
    suspend fun getHeight(): Int

    @Query("SELECT `Weight` FROM UserInfo")
    suspend fun getWeight(): Int

    @Query("SELECT `Age` FROM UserInfo")
    suspend fun getAge(): Int

    @Query("SELECT `Gender` FROM UserInfo")
    suspend fun getGender(): String

    @Insert
    suspend fun insertUser(loginInfo: LoginInfo)

    @Insert
    suspend fun insertInfo(userInfo: UserInfo)

    @Query("DELETE FROM UserInfo")
    suspend fun deleteAllUserInfo()
}