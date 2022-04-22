package com.jiyeon.soptseminar.ui.signup

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jiyeon.soptseminar.R
import com.jiyeon.soptseminar.databinding.ActivitySignUpBinding

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
        binding.btnSignUp.setOnClickListener {
            if (binding.etId.text.isNotEmpty() && binding.etName.text.isNotEmpty() && binding.etPw.text.isNotEmpty()){
                intent.putExtra("id", binding.etId.text.toString())
                intent.putExtra("pw", binding.etPw.text.toString())
                setResult(RESULT_OK, intent)
                finish() // 화면 닫기
            }else{
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}