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
import com.jiyeon.soptseminar.data.reponse.ResponseSignIn
import com.jiyeon.soptseminar.data.request.RequestSignIn
import com.jiyeon.soptseminar.databinding.ActivitySignInBinding
import com.jiyeon.soptseminar.network.ServiceCreator
import com.jiyeon.soptseminar.ui.MainActivity
import com.jiyeon.soptseminar.ui.signup.SignUpActivity
import com.jiyeon.soptseminar.util.SOPTSharedPreferences
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

        isAutoLogin() // 자동 로그인 체크

    }

    // 자동 로그인 체크

    // 로그인 버튼 이벤트
    private fun initLoginBtn() {
        binding.btnLogin.setOnClickListener {

            // 아이디, 비밀번호 공백 여부 체크
            if (binding.etId.text.isNotEmpty() && binding.etPw.text.isNotEmpty()) {   // 공백 x

                loginNetWork() // 로그인 서버통신 시도

            } else { // 공백 o
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 자동로그인 체크
    private fun isAutoLogin(){
        SOPTSharedPreferences.init(this)

        if(SOPTSharedPreferences.getAutoLogin(this)){

            Toast.makeText(this, "자동로그인 되었습니다.", Toast.LENGTH_SHORT).show()

            // MainActivity 이동
            startActivity(Intent(this@SignInActivity, MainActivity::class.java))
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


    private fun loginNetWork() {
        val requestSignIn = RequestSignIn(
            id = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )

        val call: Call<ResponseSignIn> = ServiceCreator.soptService.postLogin(requestSignIn)

        call.enqueue(object : Callback<ResponseSignIn> {
            override fun onResponse(
                call: Call<ResponseSignIn>,
                response: Response<ResponseSignIn>
            ) {
                if (response.isSuccessful) { // 로그인 성공
                    val data = response.body()?.data

                    // 토스트 메세지 출력
                    Toast.makeText(this@SignInActivity, "${data?.email}님 반갑습니다.", Toast.LENGTH_SHORT).show()

                    // MainActivity 이동
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))

                    // 자동 로그인 연결
                    SOPTSharedPreferences.setAutoLogin(true)

                } else { // 로그인 실패
                    when (response.code()) {
                        404 -> {
                            Toast.makeText(this@SignInActivity, "이메일에 해당하는 사용자 정보가 없습니다.", Toast.LENGTH_SHORT).show()
                        }

                        else -> {
                            Toast.makeText(this@SignInActivity, "비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseSignIn>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }

}
