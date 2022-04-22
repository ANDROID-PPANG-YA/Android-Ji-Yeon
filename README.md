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
