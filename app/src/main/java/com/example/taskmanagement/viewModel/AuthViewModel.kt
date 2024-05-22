package com.example.taskmanagement.viewModel

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class AuthViewModel:ViewModel() {
    val auth:FirebaseAuth = Firebase.auth
}