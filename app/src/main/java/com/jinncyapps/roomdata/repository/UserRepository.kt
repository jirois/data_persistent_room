package com.jinncyapps.roomdata.repository

import androidx.lifecycle.LiveData
import com.jinncyapps.roomdata.data.UserDao
import com.jinncyapps.roomdata.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.insert(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user:User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAll(){
        userDao.deleteAll()
    }
}