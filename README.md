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
