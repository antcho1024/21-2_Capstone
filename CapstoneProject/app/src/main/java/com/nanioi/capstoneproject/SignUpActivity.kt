package com.nanioi.capstoneproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nanioi.capstoneproject.databinding.ActivitySignInBinding
import com.nanioi.capstoneproject.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import android.widget.EditText
import android.widget.Toast



class SignUpActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // by 나연. 회원가입 완료 버튼 클릭 시 액티비티 이동 (21.09.27)
        binding.signUpButton.setOnClickListener {

            //TODO 입력 데이터들 저장하는거 추가해주세
            val email = getInputEmail()
            val password = getInputPassword()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "회원가입에 성공했습니다. 로그인 버튼을 눌러 로그인해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(this, "이미 가입한 이메일이거나, 회원가입에 실패했습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }
                    startActivity(Intent(this, MainActivity::class.java))
                }
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload();
        }
    }

    private fun getInputEmail():String{
        return findViewById<EditText>(R.id.emailEditText).text.toString()
    }
//    private fun getInputUserName():String{
//        return findViewById<EditText>(R.id.emailEditText).text.toString()
//    }
    private fun getInputPassword():String{ //R을 회원가입 페이지에다 놓고 골라야하는데,, 바꿀줄을 모르겠습니다..
        return findViewById<EditText>(R.id.passwordEditText).text.toString()
    }
    private fun getInputPassword2():String{
        return findViewById<EditText>(R.id.passwordEditText).text.toString()
    }
}
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.core.widget.addTextChangedListener
//import com.facebook.CallbackManager
//import com.facebook.FacebookCallback
//import com.facebook.FacebookException
//import com.facebook.login.LoginResult
//import com.facebook.login.widget.LoginButton
//import com.google.firebase.auth.FacebookAuthProvider
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.database.ktx.database
//import com.google.firebase.ktx.Firebase
//import com.nanioi.tinder_application.DBkey.Companion.USERS
//import com.nanioi.tinder_application.DBkey.Companion.USER_ID
//
//class LoginActivity : AppCompatActivity() {
//
//    private lateinit var auth:FirebaseAuth
//    private lateinit var callbackManager:CallbackManager
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
//
//        auth= Firebase.auth
//        callbackManager= CallbackManager.Factory.create()
//
//        val emailEditText = findViewById<EditText>(R.id.emailEditText)
//        val passwordEditText  = findViewById<EditText>(R.id.passwordEditText)
//
//        initLoginButton()
//        initSignUpButton()
//        initEmailAndPasswordEditText()
//        initFacebookLoginButton()
//    }
//
//
//    private fun initLoginButton() {
//        val loginButton = findViewById<Button>(R.id.loginButton)
//        loginButton.setOnClickListener {
//            val email = getInputEmail()
//            val password = getInputPassword()
//
//            auth.signInWithEmailAndPassword(email,password)
//                    .addOnCompleteListener(this) { task->
//                        if(task.isSuccessful){
//                            handleSuccessLogin()
//                        }else{
//                            Toast.makeText(this,"로그인에 실패했습니다. 이메일 또는 비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show()
//                        }
//
//                    }
//        }
//    }
//    private fun initSignUpButton() {
//        val signUpButton = findViewById<Button>(R.id.signUpButton)
//        signUpButton.setOnClickListener {
//            val email = getInputEmail()
//            val password = getInputPassword()
//
//            auth.createUserWithEmailAndPassword(email,password)
//                    .addOnCompleteListener(this) {task->
//                        if(task.isSuccessful){
//                            Toast.makeText(this,"회원가입에 성공했습니다. 로그인 버튼을 눌러 로그인해주세.",Toast.LENGTH_SHORT).show()
//                        }else{
//                            Toast.makeText(this,"이미 가입한 이메일이거나, 회원가입에 실패했습니다.",Toast.LENGTH_SHORT).show()
//                        }
//                    }
//        }
//    }
//    private fun initEmailAndPasswordEditText() {
//        val emailEditText = findViewById<EditText>(R.id.emailEditText)
//        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
//        val loginButton = findViewById<Button>(R.id.loginButton)
//        val signUpButton = findViewById<Button>(R.id.signUpButton)
//
//        emailEditText.addTextChangedListener {
//            val enable = emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()
//            loginButton.isEnabled=enable
//            signUpButton.isEnabled=enable
//        }
//        passwordEditText.addTextChangedListener {
//            val enable = emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()
//            loginButton.isEnabled=enable
//            signUpButton.isEnabled=enable
//        }
//    }
//    private fun initFacebookLoginButton() {
//        val facebookLoginButton = findViewById<LoginButton>(R.id.facebookLoginButton)
//
//        facebookLoginButton.setPermissions("email","public_profile")
//        facebookLoginButton.registerCallback(callbackManager,object :FacebookCallback<LoginResult>{
//            override fun onSuccess(result: LoginResult) {
//                val credential = FacebookAuthProvider.getCredential(result.accessToken.token)
//                auth.signInWithCredential(credential)
//                        .addOnCompleteListener(this@LoginActivity) {task->
//                            if(task.isSuccessful){
//                                handleSuccessLogin()
//                            }else{
//                                Toast.makeText(this@LoginActivity,"페이스북 로그인에 실패했습니다.",Toast.LENGTH_SHORT).show()
//                            }
//                        }
//            }
//
//            override fun onCancel() {
//            }
//
//            override fun onError(error: FacebookException?) {
//                Toast.makeText(this@LoginActivity,"페이스북 로그인에 실패했습니다.",Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//    private fun getInputEmail():String{
//        return findViewById<EditText>(R.id.emailEditText).text.toString()
//    }
//    private fun getInputPassword():String{
//        return findViewById<EditText>(R.id.passwordEditText).text.toString()
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        callbackManager.onActivityResult(requestCode,resultCode,data)
//    }
//    private fun handleSuccessLogin(){
//        if(auth.currentUser == null){
//            Toast.makeText(this,"로그인에 실패했습니다.",Toast.LENGTH_SHORT).show()
//            return
//        }
//        val userId = auth.currentUser?.uid.orEmpty() //userId 가져오기
//        val currentUserDB = Firebase.database.reference.child(USERS).child(userId)
//        val user = mutableMapOf<String,Any>()
//        user[USER_ID]=userId
//        currentUserDB.updateChildren(user)
//
//        finish()
//    }
//}

// -> 제가 전에 한 프로젝트 로그인 부분예시 코드인데 참고하셔서 해주세요