package com.jiyeon.soptseminar

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jiyeon.soptseminar.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 회원가입 완료
        binding.btnSignUp.setOnClickListener {
            if (binding.etId.text.isNotEmpty() && binding.etName.text.isNotEmpty() && binding.etPw.text.isNotEmpty()){
                finish() // 화면 닫기
            }else{
                Toast.makeText(this, "입력되지 않은 정보가 있습니다", Toast.LENGTH_SHORT).show()
            }
        }
    }
}