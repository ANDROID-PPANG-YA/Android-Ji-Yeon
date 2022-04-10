# Seminar1
## 1️⃣ Level 1 & Level2
|로그인|회원가입|홈|
|------|---|---|
|<img src = "https://user-images.githubusercontent.com/62979643/162348246-8ed7ddd0-9781-4b5a-9267-9b9bb41a66c5.gif" width ="200" />|<img src = "https://user-images.githubusercontent.com/62979643/162348243-930e07ef-e4d2-421d-b786-e0a17766405f.gif" width ="200" />|<img src = "https://user-images.githubusercontent.com/62979643/162348239-ad472446-274c-44f8-832a-d0896764cd30.gif" width ="200" />|
|toast, Intent, EditTextView|finish()|ImageView,TextView|
|⭐**registerForActivityResult**|putExtra|ScrollView,⭐**constraintDimensionRatio**|

## ⭐registerForActivityResult
###### SignInActivity.kt
```kotlin
    // 1. result 런처 지연 초기화 
    private lateinit var resultLauncher: ActivityResultLauncher<Intent> 
    
    // 2. 작동 성공여부를 확인한 후(=Result_OK), getExtra로 이전 activity에서 데이터를 받아온다.
     resultLauncher = 
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    val id = it.data?.getStringExtra("id")
                    val pw = it.data?.getStringExtra("pw")

                    binding.etId.setText(id)
                    binding.etPw.setText(pw)
                }
            }

   // 3. 화면을 이동할 때 launcher를 심어준다.
    val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
```
###### SignUpActivity.kt
```kotlin
  // 정보 넘겨주기
   intent.putExtra("id", binding.etId.text.toString())
   intent.putExtra("pw", binding.etPw.text.toString())
   setResult(RESULT_OK, intent)
```
[참고 사이트](https://developer.android.com/training/basics/intents/result#custom)

## ⭐constraintDimensionRatio
```
Constrains the views aspect ratio. For Example a HD screen is 16 by 9 = 16/(float)9 = 1.777f.
뷰의 가로 세로 비율을 제한합니다. 예를 들어 HD 화면은 16x9 = 16/(수직)9 = 1.777f입니다.
```
```
android:layout_width="0dp"
android:layout_height="0dp"
app:layout_constraintDimensionRatio="1:1"
```

[참고 사이트](https://developer.android.com/reference/androidx/constraintlayout/widget/ConstraintProperties#dimensionRatio(java.lang.String))

## 2️⃣ Level 3
## 🚩 [리드미 과제] ViewBinding vs DataBinding
**ViewBinding**과 **DataBinding**은 findViewById 경우를 대체하여 쓰인다. 이는 ``findViewById``가 가진 단점을 보완할 수 있기 때문인데 Binding을 사용함으로써 얻는 이점은 다음과 같다. 

+ Null Safety : id를 잘못 입력함으로써 생기는 null 에러를 잡을 수 있다.  
+ Type Safety : XML 파일에서 타입이 바로 매칭이 되기 때문에 class cast 에러가 발생하지 않는다.  

**즉, 런타임이 아닌 컴파일 시에 에러를 미리 잡아낼 수 있는 장점을 가진다고 말할 수 있다.**
(코드와 레이아웃 간의 호환성이 있기 때문)

**그렇다면, ViewBinding과 DataBinding의 차이는 무엇일까?**

``` ViewBinding의 장점 ``` 
+ Faster compilation: annotation과정이 없기 때문에 빠른 접근이 가능하다
+ Ease of use: XML 파일에 따로 layout 태그를 써줄 필요가 없기 때문에 쉽게 이용이 가능하다. 또한 모듈화에 용이하다.
 
``` ViewBinding의 단점 (= DataBinding의 장점) ```
+  layout태그를 사용하지 않기 때문에 다이나믹한 뷰 구성이 어렵다
+ two-way data binding를 제공하지 않는다 (-> mvvm, mvi 패턴에 부적합)

### 결론: 각각의 이점을 고려하여 프로젝트에 따라 ViewBinding과 DataBinding 둘 중 하나를 사용하면 된다.

[참고 사이트](https://developer.android.com/topic/libraries/view-binding#findviewbyid)

## ⭐ MVVM 구현 방법 설명
**ViewModel**과 **DataBinding**을 사용하여 간단하게만 구현해보았습니다. 
1. **LiveData**을 사용해보려 했으나 업데이트 기능이 있는(ex.텍스트 변경됨) ui가 없어서 억지로 넣을 필요가 없다고 판단했습니다. (물론 ui는 자유롭게 꾸며도 된다고 하셨지만 ㅎㅎㅎㅎ 구구절절절절...)
2. two-way data binding이 필요한 곳은 자기소개 변수들 뿐이라고 생각해서 **HomeViewModel**에만 코드를 넣어주었습니다. 로그인을 한 사람이 누구냐에 따라 계속 바뀌는 ui이기 때문입니다. **다만, SignInViewModel과 SignUpViewModel에도 제가 뭔가 놓치고 있는 것이 있는지 궁금합니다. ㅎㅎ**

## :three: 배운점
:heavy_check_mark: : ```registerForActivityResult```와 ```constraintDimensionRatio```의 개념과 사용법을 알게 되었습니다.

:heavy_check_mark: : 항상 헷갈렸던 ```DataBinding```과 ```ViewBinding```의 차이를 정확히 이해하는 시간을 가졌습니다.

:heavy_check_mark: ```ViewModel```을 사용해 볼 수 있었습니다
