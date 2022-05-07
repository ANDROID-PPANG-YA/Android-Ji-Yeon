package com.jiyeon.soptseminar.ui.signup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jiyeon.soptseminar.*
import com.jiyeon.soptseminar.databinding.ActivitySignUpBinding
import com.jiyeon.soptseminar.ui.home.HomeActivity
import com.jiyeon.soptseminar.ui.signin.SignInActivity
import com.jiyeon.soptseminar.week4.ServiceCreator
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
    private fun initSignUpBtn(){

        binding.btnSignUp.setOnClickListener{

            registerNetWork()
        }
        /*binding.btnSignUp.setOnClickListener {
            if (binding.etId.text.isNotEmpty() && binding.etName.text.isNotEmpty() && binding.etPw.text.isNotEmpty()){
                intent.putExtra("id", binding.etId.text.toString())
                intent.putExtra("pw", binding.etPw.text.toString())
                setResult(RESULT_OK, intent)
                finish() // 화면 닫기
            }else{
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
        }*/
    }

    // 회원가입 네트워크
    private fun registerNetWork(){

        val requestSignUp = RequestSignUp(
            name = binding.etName.text.toString(),
            email = binding.etId.text.toString(),
            password = binding.etPw.text.toString()
        )

        val call: Call<ResponseSignUp> = ServiceCreator.soptService.postRegister(requestSignUp)

        call.enqueue(object: Callback<ResponseSignUp> {
            override fun onResponse(
                call: Call<ResponseSignUp>,
                response: Response<ResponseSignUp>
            ) {
                if(response.isSuccessful){
                    val data = response.body()?.data

                    Toast.makeText(this@SignUpActivity, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show()
                    finish()
                }else  Toast.makeText(this@SignUpActivity, "회원가입에 실패하셨습니다.", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ResponseSignUp>, t: Throwable) {
                Log.e("NetworkTest","error:$t")
            }
        })
    }
}