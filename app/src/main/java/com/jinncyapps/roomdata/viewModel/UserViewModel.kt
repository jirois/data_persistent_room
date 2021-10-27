package com.jinncyapps.roomdata.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.jinncyapps.roomdata.data.UserDatabase
import com.jinncyapps.roomdata.model.User
import com.jinncyapps.roomdata.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<User>>
    private val userRepository: UserRepository

    init {
        val useDao = UserDatabase.getDatabase(application).userDao()
        userRepository = UserRepository(useDao)
        readAllData = useDao.readAllData()

    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }
    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(user)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteAll()
        }
    }
}