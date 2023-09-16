package com.examplepart.foodpart.ui.screens.authentication.registeruser

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examplepart.foodpart.network.common.Result
import com.examplepart.foodpart.network.common.safeApi
import com.examplepart.foodpart.network.user.RegisterUserModel
import com.examplepart.foodpart.network.user.UserApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val userApi: UserApi,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _usernameValidationState = MutableStateFlow<String?>(null)
    val usernameValidationState: StateFlow<String?> = _usernameValidationState

    private val _passwordValidationState = MutableStateFlow<String?>(null)
    val passwordValidationState: StateFlow<String?> = _passwordValidationState

    private val _isPasswordMatching = MutableStateFlow(false)
    private val isPasswordMatching: StateFlow<Boolean> = _isPasswordMatching

    private val _registerResult = MutableStateFlow<Result>(Result.Idle)
    val registerResult: SharedFlow<Result> = _registerResult.asSharedFlow()

    fun performValidation(username: String, password: String, repeatPassword: String) {

        val usernameError = validateUsername(username)
        _usernameValidationState.value = usernameError

        val passwordError = validatePassword(password)
        _passwordValidationState.value = passwordError

        _isPasswordMatching.value = password == repeatPassword
    }

    fun areAllFieldsValid(): Boolean {
        return usernameValidationState.value == null &&
                passwordValidationState.value == null && isPasswordMatching.value
    }

    fun doRegister(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            safeApi(
                call = {
                    userApi.createUser(
                        RegisterUserModel(username = username, password = password)
                    )
                },
                onDataReady = { response ->
                    val id = response.data.id
                    val avatar = response.data.avatar
                    val userName = response.data.username

                    saveUserData(id, avatar, userName)
                }
            ).collect(_registerResult)
        }
    }

    private fun saveUserData(id: String, avatar: String, username: String) {
        val editor = sharedPreferences.edit()
        editor.putString("id", id)
        editor.putString("avatar", avatar)
        editor.putString("username", username)
        editor.apply()
    }

    private fun validateUsername(username: String): String? {
        return if (username.length < 4) {
            "Username must be at least 4 characters long"
        } else {
            null
        }
    }

    private fun validatePassword(password: String): String? {
        return if (password.length < 8 || !password.any { it.isLowerCase() } || !password.any { it.isUpperCase() }) {
            "Password must be at least 8 characters long and contain both uppercase and lowercase letters"
        } else {
            null
        }
    }
}