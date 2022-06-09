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

# Seminar3
## 🎥실행화면
|Selector|ViewPager2|권한|
|------|---|---|
|<img src = "https://user-images.githubusercontent.com/62979643/167122494-ea405b0b-0dc0-4fbc-a766-0dc5370efdd9.gif" width ="200" />|<img src = "https://user-images.githubusercontent.com/62979643/167122481-6c522020-973a-435f-9def-81e5aa8ddaef.gif" width ="200" />|<img src = "https://user-images.githubusercontent.com/62979643/167122468-5a1ec866-0597-4f11-93d6-0d3250500735.gif" width ="200" />


## 1️⃣ 필수과제
## ProfileFragment
+ Button에 Selector 활용하기
+ isSelected 코드를 활용해서 배경에 변화를 주었습니다.
```kotlin
    binding.btnFollowerList.isSelected = true

        // 팔로우 rv 보여주기
        binding.btnFollowerList.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.fragment_profile, followListFragment).commit()
            binding.btnFollowerList.isSelected = true
            binding.btnRepoList.isSelected = false

# Seminar2
## 🎥실행화면
|이동(LinearLayout)|이동(GridLayout)|삭제|상세보기|
|------|---|---|---|
|<img src = "https://user-images.githubusercontent.com/62979643/164692378-51d50fc9-6d85-4d8e-8d59-e78ea7f5dc1f.gif" width ="200" />|<img src = "https://user-images.githubusercontent.com/62979643/164692305-e5854d9b-7c90-419f-9b64-670382e4ccd3.gif" width ="200" />|<img src = "https://user-images.githubusercontent.com/62979643/164692262-699a14b3-3bdc-4f57-b0b7-b8bb839c2d0e.gif" width ="200" />|<img src = "https://user-images.githubusercontent.com/62979643/164692345-6c6d4269-fbec-46a2-b2c7-224d43eb6de8.gif" width ="200"/>|

## 1️⃣ 필수과제
+ xml에 FragmentContainerView선언해주었습니다. 
+ HomeActivity에서 버튼을 누르면 해당 fragment로 replace(=교체)됩니다. 
+ 각각의 fragment 안에 미리 class로 만들어 놓은 adapter들을 연결해주었습니다.
 
## [1-1] HomeActivity 하단에 RecyclerView 구현하기
###### HomeActivity.kt
```kotlin
    private fun initTransFragmentEvent() {
        val followListFragment = FollowerListFragment()
        val repoListFragment = RepoListFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragment_home, followListFragment)
            .commit()

        // 팔로우 rv 보여주기
        binding.btnFollowerList.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_home, followListFragment).commit()
        }

        // 레포 rv 보여주기
        binding.btnRepoList.setOnClickListener {
            childFragmentManager.beginTransaction().replace(R.id.fragment_profile, repoListFragment).commit()
            binding.btnRepoList.isSelected = true
            binding.btnFollowerList.isSelected = false
        }
```
 
## HomeFragment
+ TabLayout + ViewPager2
```kotlin
    // viewpager2 초기화
    private fun initAdapter(){
        val homePagerAdapter = HomeViewPagerAdapter(this)
        val fragmentList = listOf(FollowingFragment(),FollowerFragment())
        homePagerAdapter.fragments.addAll(fragmentList)

        binding.vpHome.adapter = homePagerAdapter

        binding.vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }
    // tablayout 초기화
    private fun initTabLayout(){
        TabLayoutMediator(binding.tblHome, binding.vpHome) { tab, position ->
            when(position){
                0 -> {tab.text = "팔로잉"}
                1 -> {tab.text = "팔로워"}
            }
        }.attach()
    }
```

## 2️⃣ 성장과제
## ViewPager2 중첩 스크롤 문제 해결하기
##### NestedScrollableHost.kt 클래스 활용 
```xml
       <com.jiyeon.soptseminar.util.NestedScrollableHost
            android:layout_width="match_parent"
            android:layout_height="0dp"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintTop_toBottomOf="@id/tbl_home">

            <androidx.viewpager2.widget.ViewPager2
                android:id="@+id/vp_home"
                android:layout_width="match_parent"
                android:layout_height="match_parent"/>

        </com.jiyeon.soptseminar.util.NestedScrollableHost>
```
```kotlin
// Gesture is parallel, query child if movement in that direction is possible
if (canChildScroll(orientation, if (isVpHorizontal) dx else dy)) {
    // Child can scroll, disallow all parents to intercept
     parent.requestDisallowInterceptTouchEvent(true)
} else {
   // Child cannot scroll, allow all parents to intercept
  parent.requestDisallowInterceptTouchEvent(false)
}
```
requestDisallowInterceptTouchEvent를 활용하여
Child View(=안에 있는 스크롤 뷰)가 스크롤이 가능한 영역이면 true를 반환해준다. 
이 부분이 NestedScrollableHost.kt의 핵심이라 생각했다.

[참고사이트](https://developer.android.com/training/animation/vp2-migration#nested-scrollables)

## :three: 도전과제
## 갤러리에서 받아온 이미지(Uri)를  Glide를 사용해서 화면에 띄워보기
<img src = "https://user-images.githubusercontent.com/62979643/167130106-3ea3594b-8895-43c4-a7d7-3e81ea8c58ce.png" width ="300" /> <img src = "https://user-images.githubusercontent.com/62979643/167130126-4d531798-1925-45a7-abc1-4bf94c960c99.png" width ="300" />

```kotlin
// 사용자의 응답을 처리하는 권한 콜백을 등록
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // 권한 획득 성공 시
            openGallery()
        } else {
            // 권한 획득 거부 시
            showPermissionContextPopup()
        }
    }
 // 권한 요청 함수
    private fun requestPermission(){
        binding.btnInputPhoto.setOnClickListener {
            requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }
// 권한 요청하기
    private fun showPermissionContextPopup() {
        AlertDialog.Builder(requireContext())
            .setTitle("권한이 필요합니다.")
            .setMessage("프로필 이미지를 바꾸기 위해서는 갤러리 접근 권한이 필요합니다.")
            .setPositiveButton("동의하기") { _, _ ->
                registerForActivityResult(ActivityResultContracts.RequestPermission()){

                }
            }
            .setNegativeButton("취소하기") { _, _ -> }
            .create()
            .show()
    }
```
## 권한 flow 
![image](https://user-images.githubusercontent.com/62979643/167129885-633d110e-a8e4-4bfc-a9dd-a4cba6796be2.png)



## 👍배운점 
:heavy_check_mark: : color파일에서 text의 색상을 selector로 처리하는 방법을 배웠습니다. 
:heavy_check_mark: NestedScrollableHost의 존재를 알게 되었습니다.
:heavy_check_mark: 안드로이드에서 '권한'의 흐름을 배웠습니다.

## 👎의문점
❓: NestedScrollableHost같은 클래스를 직접 만드는 상황이 올까? 이런 클래스를 만들려면 무엇을 공부해야될까?   
❓: requestPermissionLauncher관련 이해가 더 필요할 것 같다. ex) 어떻게 등록된 권한이 앱 내에 자동으로 저장되는지?
            supportFragmentManager.beginTransaction().replace(R.id.fragment_home, repoListFragment)
                .commit()
        }
    }
```

## [1-2] 둘 중 하나의 RV는 GridLayout으로 구현하기
###### fragment_repo_list.xml
``` xml
<RecyclerView>
     ...
     app:spanCount="2"
     app:layoutManager="androidx.recyclerview.widget.GridLayoutManager"/>
```

## 2️⃣ 성장과제
## [2-1] 아이템 클릭시 상세 설명을 보여주는 Activity로 이동하기
1. FollowAdapter.kt에서 클릭이벤트 관련 interface를 선언해줍니다. 
```kotlin
private var listener : OnItemClickListener? = null

interface OnItemClickListener{
        fun onItemClick(v: View, data:FollowerData, pos:Int)
}

fun setOnItemClickListener(listener : OnItemClickListener) {
        this.listener = listener
}
```
2. inner class인 FollowViewHolder에 아이템 클릭 이벤트를 구현해줍니다.
```kotlin
  inner class FollowViewHolder(
        private var binding: ItemFollowListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: FollowerData) {
            ...
            val pos = adapterPosition
            if(pos!=RecyclerView.NO_POSITION){
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView,data,pos)
                }
            }
        }
    }
```
3. FollowerListFragment.kt에 setOnItemClickListener를 구현하고 Intent를 통해 DetailActivity에 데이터를 넘겨줍니다.
 ```kotlin
private fun itemEvent(){
        // 상세보기 화면으로 이동하는 클릭 이벤트
        followerAdapter.setOnItemClickListener(object : FollowerAdapter.OnItemClickListener {
            override fun onItemClick(v: View, data: FollowerData, pos: Int) {
                val intent = Intent(requireContext(), DetailActivity::class.java)
                intent.putExtra("profileData", data.profile)
                    .putExtra("nameData", data.name)
                    .putExtra("infoData", data.intro)

                startActivity(intent)
            }

        })
}
```
## [2-2] ItemDecoration을 활용해서 리스트 간 간격 또는 구분선 주기
+ 디자인 상 구분선보다는 간격을 주는 게 예쁠 것 같아서 간격으로 구현해보았습니다.
+ Util파일에 VerticalSpaceItemDecoration클래스를 생성하여 하단에 margin을 주었습니다. 
###### VerticalSpaceItemDecoration.kt 
```kotlin
class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = verticalSpaceHeight
    }
}
```
###### FollowerListFragment.kt
```kotlin
 private fun decoRVItem(){
        val spaceDecoration = VerticalSpaceItemDecoration(50)
        binding.rvFollow.addItemDecoration(spaceDecoration)
    }
``` 
[참고 사이트](https://odomm.tistory.com/entry/Kotlin-%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-Recyclerview-%EC%95%84%EC%9D%B4%ED%85%9C-%EA%B0%84%EA%B2%A9-%EA%B5%AC%EB%B6%84%EC%84%A0-%ED%91%9C%EC%8B%9C)

## [2-3] RecyclerView Item 이동, 삭제 구현
1. Util 파일에 ItemTouchHelperCallback 클래스를 생성하여 이동,삭제 이벤트를 구현해주었습니다. 
(코드는 길어서 생략합니다. ItemTouchHelperCallback.kt 참고 바랍니다!) 
2. 각각의 RV에 이동,삭제 관련 interface를 선언해주었습니다.
```kotlin
   private lateinit var dragListener: OnStartDragListener
    // 아이템 드래그 인터페이스
    interface OnStartDragListener {
        fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
    }
    fun startDrag(listener: OnStartDragListener) {
        this.dragListener = listener
    }
    // 아이템 이동
    override fun onItemMoved(fromPos: Int, toPos: Int) {
        Collections.swap(followList, fromPos, toPos)
        notifyItemMoved(fromPos, toPos)
    }

    // 아이템 삭제
    override fun onItemSwiped(pos: Int) {
        followList.removeAt(pos)
        notifyItemRemoved(pos)
    }
```  
3. 각각의 Fragment에 ItemTouchHelper를 초기화해주었습니다. 
```kotlin
    private fun itemEvent(){
        ...
        // 아이템 이동,삭제 이벤트
        val callback = ItemTouchHelperCallback(followerAdapter)
        val touchHelper = ItemTouchHelper(callback)

        touchHelper.attachToRecyclerView(binding.rvFollow)
        binding.rvFollow.adapter = followerAdapter

        followerAdapter.startDrag(object : FollowerAdapter.OnStartDragListener {
            override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                touchHelper.startDrag(viewHolder)
            }
        })
``` 

[참고 사이트](https://betterprogramming.pub/drag-to-reorder-android-recyclerview-items-using-kotlin-afcaee1b7fb5)


## :three: 도전과제
## [3-1] 보일러 플레이트 코드 개선하기
**개선법: BaseActivity와 BaseFragment을 Util로 만들어서 중복되는 뷰 설정 코드를 제거해주었습니다.**
### BaseActivity
+ layoutResId와 viewModelClass를 인자로 받아서 DataBinding과 ViewModel 연결을 해당 Activity에서 진행하도록 했습니다.

#### before
```kotlin
class HomeActivity : AppCompatActivity() {

    ❌private lateinit var binding:ActivityHomeBinding
    ❌private lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ❌binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        ❌viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding.viewModel = viewModel
    }
}
``` 

#### after
```kotlin
  class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(
    R.layout.activity_home,
    HomeViewModel::class.java
) {
   // ⭐binding과 viewModel을 일일이 선언해줄 필요가 없어졌습니다. 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         // ⭐onCreate가 단순해졌습니다.
    }

    ⭐ 오버라이드를 통해 중요한 초기화를 잊지않고 할 수 있습니다. 일반함수와 구분이 편합니다.
    override fun initViewModel() {
        super.initViewModel()
        binding.viewModel = viewModel 
    }
}
``` 
### BaseFragment
+ Fragment에 ViewModel이 쓰인 코드가 없어서 DataBinding만 진행해주었습니다. Activity와 다른 점은 메모리 누수를 방지하기 위해 onDestroyView에 ``` _binding = null```  작업을 해준 점입니다. 
#### before
```kotlin
class FollowerListFragment : Fragment() {

    ❌private var _binding: FragmentFollowListBinding? = null
    ❌private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       ❌ _binding =DataBindingUtil.inflate(layoutInflater, R.layout.fragment_follow_list, container, false)
       ❌return binding.root
    }

    ❌override fun onDestroyView() {
        ❌super.onDestroyView()
        ❌_binding = null
    ❌}
}
``` 
#### after
```kotlin
class FollowerListFragment : BaseFragment<FragmentFollowListBinding>(R.layout.fragment_follow_list) {
} ⭐ 깔끔... 굿굿 ... ^^b
``` 

## 🚩 [리드미 과제] notifyDataSetChanged에는 문제점이 있습니다. 문제점은 무엇이고 이러한 문제점을 해결한 방식을 적용해보고 리드미에 작성해주세요!
```
notifyDataSetChanged는 바뀐 데이터나 뷰를 찾기 위해 모든 뷰를 검사하기 때문에 부분 데이터가 변경되었을 때 비효율적이고 버벅거리는 형상이 발생한다.  
따라서 변경된 부분 데이터를 찾기 위한 최소한의 업데이트를 계산하는 ```DiffUtil``` 를 활용해 볼 수 있다.
```

**코드 적용은 아직 못했습니다. LiveData 개념을 먼저 숙지한 후 , 진행하도록 하겠습니다. **

[참고 사이트](https://developer.android.com/topic/performance/vitals/render?hl=ko#recyclerview_notifydatasetchanged)

[참고 사이트](https://developer.android.com/codelabs/kotlin-android-training-diffutil-databinding#0)


## 👍배운점 
:heavy_check_mark: : 그동안 라이브러리만으로 RV의 이동,삭제를 구현했는데 ItemTouchHelperCallback의 존재를 알게 되어 이를 직접 Custom해볼 수 있는 기회를 가지게 되었습니다. 

:heavy_check_mark: BaseActivity와 BaseFragment가 왜 유용한 지 꼼꼼하게 확인해 볼 수 있는 시간이었습니다.

:heavy_check_mark: DiffUtil의 존재를 알게되었고, 그동안 와닿지 않았던 LiveData의 유용함을 알게 되었습니다.

## 👎의문점
❓: BaseActivity와 BaseFragment가 인자를 저렇게 받아와도 되는걸까? 

❓: BaseActivity에서 initViewModel()까지 처리하는 방법은 없는 걸까?   

❓: ItemTouchHelperCallback에 대한 개념이 아직 부족한 것 같다. (내장 함수에 대해 더 공부해야 할듯) 

❓: viewBinding과 dataBinding을 한 프로젝트에 함께 써도 되는 걸까? 간단한 화면은 viewBinding으로 구성하면 안되는 걸까?

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

##  💚 Sopt 30th Android 커리큘럼 💚

| Week | 세미나 | 과제 |커리큘럼 내용 |
| ------ | -- | -- |----------- |
| 1주차 | ☑️ | ☑️ | 안드로이드 기초와 View/ViewGroup |
| 2주차 | ☑️ | ☑️ | Fragment와 RecyclerView |
| 3주차 | ☑️ | ☑️ | 앱 내 디자인 적용하는 법 |
| 4주차 | ☑️ | ☑️ | Retrofit2로 서버와 통신하기 |
| 5주차 |  |  | 클라이언트 & 디자인 합동 세미나 |
| 6주차 |  |  | 클라이언트 & 서버 합동 세미나 + 솝커톤 |
| 7주차 |  |  | 확장 함수와 영속성 데이터 |
| 8주차 |  |  | 협업을 위한 팁과 유용한 라이브러리 소개 |
