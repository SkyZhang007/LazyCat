# LazyCat
## Google MVP
- Model：数据类型
- View：UI -- Activity、Fragment
- Presenter：Model 和 View 的桥梁，额外创建契约类 Contract：持有 View和Presenter 的引用。

## Google MVVM
- Model:数据类型
- View：UI -- Activity、Fragment
- ViewModel：数据的持有者和管理者。
可结合 Lifecycle 和 Livedata 使用，UI 监听数据的变动，发生变化时进行 UI 更新操作。

## Google DataBinding
- 双向绑定：
```
public class ObservableUser extends BaseObservable{

    private String firstName;
    private String lastName;
    private String imageUrl;

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }
}
```
第二种写法：
```
public class Teacher {
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> className = new ObservableField<>();
    public final ObservableInt age = new ObservableInt();
    public final ObservableList<MeiziItemViewModel> mObservableMeizi = new ObservableArrayList<>();
}
```
xml写法 @=
```
```
<EditText
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="请输入内容"
    android:text="@={student.name}"/>
```
```

## Lifecycle
定义一个具有 Android 生命周期的对象，也就是与各大组件生命周期相关联。
- LifecycleObserver:lifecycle 观察者，可以监听被观察者状态。通过注解来得知被观察者的状态：
```
@OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
public void connectListener(){
    LogUtils.i(TAG,"connectListener");
}
```
或者得知当前被观察者在哪个状态之后
```
public void enable(){
    if(mLifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)){
        // do something
    }
}
```
- LifecycleOwner 接口，Activity 或 Fragment 实现它来表明自己是被观察者。
- LifecycleRegistry Lifecycle注册者，通过创建它来构建 LifecycleOwner 与 LifecycleObserver 之间的联系。
```
// 添加观察者
mLifecycleRegistry.addObserver(myLifecycleObserver);
```
当 LifecycleOwner 发生变化时，可以通过 LifecycleRegistry 来更新状态以告知观察者。

## Livedata
>LiveData is a data holder class that can be observed within a given lifecycle.
是一个数据管理类，它可以被lifecycleOwner监听，当数据变化时通知这些监听者。
- Livedata 通过 `observe()` 方法来完成 lifecycleOwner 与 Observer 的监听。
```
observe(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer)
```
- 当数据变化时，调用 Livedata 的相应方法来通知监听者
```
// 不同步，速度快。只能在主线程执行
mUserViewModel.getUserName().setValue("XiaoMing");
// 同步，略微影响性能。在子线程更新必须调用此方法
mUserViewModel.getUserName().postValue("XiaoMing2");
```
- 与 ViewModel 结合使用，让 ViewModel 持有 Livedata
```
private MutableLiveData<String> mUserName;
```
使用：
```
final UserViewModel mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
mUserViewModel.getUserName().observe(this,userObserver);
```
- 两个现成的 Livedata 子类：MutableLiveData，Mutable（易变的）。MediatorLiveData，Mediator（中间人）。
        - MutableLiveData：简单提供 postValue/setValue
        - MediatorLiveData：持有一些 LiveData，管理他们。例：多个 Livedata 请求，在他们的总数量为 10 时停止请求，就可以用 MediatorLiveData 在数量达到时移除工作和监听。
- 自定义 LiveData 看代码例子。

Livedata 转换

- map 方法，监听一个 Livedata，当它变化时及时转换为一个想要的类型返回。这样你只需要监听想要的数据类型的 livedata 即可。
```
LiveData<String> userName = Transformations.map(mUserLiveData, new Function<UserBean, String>() {
    @Override
    public String apply(UserBean input) {
        return input.getFirstName() + input.getLastName();
    }
});
```
- switchMap 方法。

- MediatorLiveData 中间人，管理者。通过 addSource 方法来添加 livedata 监听，当他们发生变化时更新自己。
```
private final MediatorLiveData<List<ProductEntity>> mObservableProducts;

mObservableProducts = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservableProducts.setValue(null);

        mRepository = ((BasicApp) application).getRepository();
        LiveData<List<ProductEntity>> products = mRepository.getProducts();

        // observe the changes of the products from the database and forward them
        mObservableProducts.addSource(products, mObservableProducts::setValue);
```

## ViewModel
> ViewModel is a class that is responsible for preparing and managing the data for
  an {@link android.app.Activity Activity} or a {@link android.support.v4.app.Fragment Fragment}.

  ViewModel 是为 Activity 或 Fragment 加载和管理数据的类。


## AppBarLayout 子布局 Toolbar（或是其它）的 ScrollFlag
> 通过设置滚动 View（NestedScrollView或RecyclerView） 的 layout_behavior，让 CoordinatorLayout 把该滚动View和AppBarLayout进行关联。
当ScrollView进行滚动时，再结合 AppBarLayout 子 View 的 layout_scrollFlags 来进行相应滚动。
1. layout_scrollFlags
- scroll:跟随 ScrollView 一起滚动
- enterAlways:无论何时向下拉动，都进行滚动
- enterAlwaysCollapsed:enterAlways 的附加属性，下拉时先展示最小高度，直到下拉到顶部再进行滚动
- exitUntilCollapsed:向上拉动时，先滚动 Toolbar，直到 Toolbar 达到最小高度再响应 ScrollView 的上滚事件。
- snap:Toolbar 只会完全显示或隐藏，不会存在一半的情况

## CollapsingToolbarLayout:主要用于 AppBarLayout 的子布局，用来作为 Toolbar 的父容器以更好的完成滑动效果
1. 主要功能：
- 折叠 title (Collapsing title)：title文字根据滑动改变大小
- 内容纱布(Content scrim)：滚动到特定位置，给 View 添加遮罩。通过 `setContentScrim(Drawable)` 来设置遮罩图片，默认遮罩是 colorPrimary。
- 状态栏纱布(Status bar scrim)：同上，`setStatusBarScrim(Drawable)`。LOLLIPOP以上有效
- 视差滚动子View(Parallax scrolling children): 让拥有此属性的子 View 滚动得相对慢一些。app:layout_collapseMode 设置为 parallax
- 将子View位置固定(Pinned position children):子 View 在全局固定位置。对 Toolbar 来说很有用：app:layout_collapseMode pin