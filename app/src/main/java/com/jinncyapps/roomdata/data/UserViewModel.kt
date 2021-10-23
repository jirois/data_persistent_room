package com.jinncyapps.roomdata.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<User>>
    private val userRepository: UserRepository

    init {
        val useDao = UserDatabase.getDatabase(application).userDao()
        readAllData = useDao.readAllData()
        userRepository = UserRepository(useDao)
    }

    fun addUser(user:User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }
}