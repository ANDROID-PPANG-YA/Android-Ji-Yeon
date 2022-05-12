package com.jiyeon.soptseminar.ui.signup

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.network.RequestSignUp
import com.jiyeon.soptseminar.network.ResponseSignUp
import com.jiyeon.soptseminar.databinding.ActivitySignUpBinding
import com.jiyeon.soptseminar.network.ServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        initSignUpBtn()
    }

    // 회원가입 버튼 이벤트
    private fun initSignUpBtn() {

        binding.btnSignUp.setOnClickListener {
            if (binding.etId.text.isNotEmpty() && binding.etName.text.isNotEmpty() && binding.etPw.text.isNotEmpty()) {
                registerNetWork() // 회원가입 서버 통신
            } else {
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 회원가입 네트워크
    private fun registerNetWork() {

        val requestSignUp = RequestSignUp(
            name = binding.etName.text.toString(),
            email = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )

        val call: Call<ResponseSignUp> = ServiceCreator.soptService.postRegister(requestSignUp)

        call.enqueue(object : Callback<ResponseSignUp> {
            override fun onResponse(
                call: Call<ResponseSignUp>,
                response: Response<ResponseSignUp>
            ) {
                if (response.isSuccessful) { // 회원가입 성공

                    Toast.makeText(this@SignUpActivity, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT)
                        .show()
                    intent.putExtra("id", binding.etId.text.toString())
                    intent.putExtra("pw", binding.etPw.text.toString())
                    setResult(RESULT_OK, intent)
                    finish() // 화면닫기

                } else { // 회원가입 실패
                    when (response.code()) {
                        409 -> {
                            Toast.makeText(this@SignUpActivity, "이미 존재하는 회원입니다.", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(this@SignUpActivity, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }
        })
    }
}