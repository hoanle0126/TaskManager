package com.example.taskmanagement.controller

import com.example.taskmanagement.viewModel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.userProfileChangeRequest

class AuthController() {
    private val auth: FirebaseAuth = AuthViewModel().auth

    fun login(email:String,password: String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }

    fun signup(email:String,password: String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    updateUI(null)
                }
            }
    }

    fun updateDetail(name:String){
        val profilesUpdate = userProfileChangeRequest {
            displayName = name
        }

        auth.currentUser!!.updateProfile(profilesUpdate)
            .addOnCompleteListener{
                if(it.isSuccessful){

                }
            }
    }

    fun signout(){
        auth.signOut()
    }

    private fun updateUI(user: FirebaseUser?) {
    }

}