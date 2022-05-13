# Seminar4
## 1️⃣ 필수과제
## 로그인/회원가입 API 연동
+ POSTMAN 테스트
![image](https://user-images.githubusercontent.com/62979643/168309919-a72d9962-acb1-4c81-8aaa-79f6085dd92e.png)
![image](https://user-images.githubusercontent.com/62979643/168309950-ec405243-6cc0-4556-84d4-5e3af963b2dc.png)

+ 로그인 완료 
```kotlin
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
```

+ 회원가입 완료 구현
```kotlin
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
```

## 2️⃣ 성장과제 2-1
## Github API 연동해서 FollowerList 연동하기 (+RepoList)
``` kotlin
data class ResponseFollowers(
    @SerializedName("login") val github_id:String, // 깃허브 id
    val avatar_url:String, // 프로필 사진
)

data class ResponseRepos(
    val name:String, // 레포이름
    val description:String // 설명
)
```

``` kotlin
interface GitHubService{

    @GET("users/stopkite/following")
    fun getFollowers(): Call<List<ResponseFollowers>>

    @GET("users/stopkite/repos")
    fun getRepos(): Call<List<ResponseRepos>>
}

```

``` kotlin
    private const val GITHUB_URL = "https://api.github.com/"

   private val gitHubRetrofit:Retrofit = Retrofit.Builder()
        .baseUrl(GITHUB_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val gitHubService:GitHubService = gitHubRetrofit.create(GitHubService::class.java)
```


## 👍배운점 
:heavy_check_mark: : @Serilazedname을 사용법을 정확히 알고 사용하게 되었습니다. 
:heavy_check_mark: Restful 이라는 말의 의미를 정확히 이해하고 사용할 수 있게 되었습니다. 
:heavy_check_mark: isSucessful 라는 함수의 존재를 처음 알고 사용해보았습니다.

## 👎의문점
❓: 그동안 open클래스로 중복되는 data 코드들을 처리해줬는데 Wrapper클래스랑 둘중 뭐가 더 나은지? 
-> 개인적으로는 open 클래스가 쓰기 편해서 더 좋은 것 같음   
❓: 실제 프로젝트에서는 Level 3 과제를 적용해야 할 것 같다. 솦커톤이나 앱잼 전에 공부를 해야겠다. 
