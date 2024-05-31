package com.example.anarstore.ui.features.signUp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.anarstore.model.user.UserRepository
import com.example.anarstore.unit.coroutineExceptionHandler
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {
    val name = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")

    fun signUpUser(loggingEvent :(String) -> Unit) {




        viewModelScope.launch(coroutineExceptionHandler){



   //             val result = userRepository.signUp(name.value!! , email.value!! , password.value!!)
   //             loggingEvent(result)
        //    userRepository.signUpByFirebase(email.value.toString() , password.value.toString())

         //   userRepository.signUpFireBase(email.value.toString() , password.value.toString())






        }

    }

}
