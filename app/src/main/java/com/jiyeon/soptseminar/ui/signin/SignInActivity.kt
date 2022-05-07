package com.jiyeon.soptseminar.ui.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.RequestSignIn
import com.jiyeon.soptseminar.ResponseSignIn
import com.jiyeon.soptseminar.databinding.ActivitySignInBinding
import com.jiyeon.soptseminar.ui.MainActivity
import com.jiyeon.soptseminar.ui.home.HomeActivity
import com.jiyeon.soptseminar.ui.signup.SignUpActivity
import com.jiyeon.soptseminar.week4.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var viewModel: SignInViewModel
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        viewModel = ViewModelProvider(this)[SignInViewModel::class.java]

        setResultLauncher()

        initLoginBtn()
        initSignUpBtn()

    }

    // 로그인 버튼 이벤트
    private fun initLoginBtn() {
        binding.btnLogin.setOnClickListener {

            loginNetWork()

            /*// 아이디, 비밀번호 공백 여부 체크
            if (binding.etId.text.isNotEmpty() && binding.etPw.text.isNotEmpty()) {   // 공백 x

                // 토스트 메세지 출력
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

                // HomeActivity 이동
                startActivity(Intent(this, MainActivity::class.java))

            } else { // 공백 o
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }*/
        }
    }

    // 회원가입 버튼 이벤트
    private fun initSignUpBtn() {
        binding.btnSignUp.setOnClickListener {
            // SignUpActivity 이동
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    // 회원가입 정보 가져오기
    private fun setResultLauncher() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val id = it.data?.getStringExtra("id")
                    val pw = it.data?.getStringExtra("pw")

                    binding.etId.setText(id)
                    binding.etPw.setText(pw)
                }
            }
    }


    private fun loginNetWork(){
        val requestSignIn = RequestSignIn(
             id = binding.etId.text.toString(),
             password = binding.etPw.text.toString()
        )

        val call: Call<ResponseSignIn> = ServiceCreator.soptService.postLogin(requestSignIn)

        call.enqueue(object:Callback<ResponseSignIn>{
            override fun onResponse(
                call: Call<ResponseSignIn>,
                response: Response<ResponseSignIn>
            ) {
                if(response.isSuccessful){
                    val data = response.body()?.data

                    Toast.makeText(this@SignInActivity, "${data?.email}님 반갑습니다.", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@SignInActivity,HomeActivity::class.java))
                }else  Toast.makeText(this@SignInActivity, "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }
        })
    }

}
