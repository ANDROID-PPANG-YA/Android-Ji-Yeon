package com.jiyeon.soptseminar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jiyeon.soptseminar.databinding.ActivitySignInBinding

class SiginInActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 로그인 버튼 클릭
        binding.btnLogin.setOnClickListener {
            // 아이디, 비밀번호 공백 여부 체크
            if (binding.etId.text.isNotEmpty() && binding.etPw.text.isNotEmpty()) {   // 공백 x

                // 토스트 메세지 출력
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()

                // HomeActivity 이동
                startActivity(Intent(this,HomeActivity::class.java))

            } else { // 공백 o
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            }
        }

        // 회원가입 버튼 클릭
        binding.btnSignUp.setOnClickListener {
            // SignUpActivity 이동
            startActivity(Intent(this,SignUpActivity::class.java))
        }


    }
}