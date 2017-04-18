# Clean Your Activity Using Delegation Pattern
![](https://cdn-images-1.medium.com/max/800/1*_xk8HBrdtVr3snK9rZ6Jlg.jpeg)

## Read article [here](https://medium.com/@kucherenkoigor/clean-your-activity-using-delegation-pattern-fcaafd82336d) 
Whenever we add Navigation Drawer to our app, we face a lot of boilerplate code 
that pollutes the Activity. It’ll further detract from main logic and can lead 
to over-proliferation of class. Side menu is a good design pattern for navigation, 
and most large apps use it. That’s why I decided to create template project based on 
MVP to solve this problem.

This project contains base classes for MVP architecture and you can use it as template.
```java
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity
        implements BaseView {

    private Unbinder mUnBinder;

    private ProgressDialog mProgressDialog = null;

    protected @NonNull abstract P getPresenterInstance();

    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = getPresenterInstance();
        mPresenter.attachView(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        mUnBinder = ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        mUnBinder.unbind();
        super.onDestroy();
    }
    
    @Override
    public View getContentView() {
        return getWindow().getDecorView();
    }
}
```
## Base Presenter:
```java
public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();

}
```
## Helpful simple implementation of base Presenter:
```java
public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {

    protected V mView;

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public V getView() {
        return mView;
    }

}
```
## Base view:
```java
public interface BaseView {

    void showProgress();

    void hideProgress();

    View getContentView();

}
```
![](https://cdn-images-1.medium.com/max/800/1*R1x_nwQcYY4GRClC1q2ehA@2x.png)

You can check out all these classes in [base package](https://github.com/KucherenkoIhor/DelegationActivityTemplate/tree/master/app/src/main/java/com/ki/dat/delegationactivitytemplate/base) of the template. 
It demonstrates a basic Model‑View‑Presenter (MVP) architecture and provides a foundation on which the sample is built. 
I also recommend you visit [Android Architecture Blueprints](https://github.com/googlesamples/android-architecture) for more details.
## Delegation pattern
To reduce the amount of code in Activity, we will use the [Delegation pattern](https://en.wikipedia.org/wiki/Delegation_pattern). 
The point is that an instance handles a request by delegating to a second object (the delegate).
Also, we have to keep in mind the fact that we deal with Android and adapt our Delegate class to Activity Lifecycle.

![](https://cdn-images-1.medium.com/max/800/1*bbf4PTi0NvYFx8Dw-1TTog.jpeg)

This project contains base classes which will handle main challenges:
```java
public abstract class BaseDelegationActivity<
        V extends BaseView,
        P extends BasePresenterImpl<V>,
        D extends BaseActivityDelegate<V, P>>
        extends BaseActivity<P> {

    protected D mDelegate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDelegate = instantiateDelegateInstance();
        mDelegate.onCreate(mPresenter);
    }

    protected abstract D instantiateDelegateInstance();

    @Override
    protected void onDestroy() {
        mDelegate.onDestroy();
        super.onDestroy();
    }
}
```
And base class of delegates:

```java
public abstract class BaseActivityDelegate<
        V extends BaseView,
        P extends BasePresenterImpl<V>> {

    private Unbinder mUnBinder = null;

    protected P mPresenter;

    public void onCreate(P presenter) {
        mPresenter = presenter;
        mUnBinder = ButterKnife.bind(this, mPresenter.getView().getContentView());
    }

    public void onDestroy() {
        mUnBinder.unbind();
    }
}
```
Inheritors of this [BaseDelegationActivity](https://github.com/KucherenkoIhor/DelegationActivityTemplate/blob/master/app/src/main/java/com/ki/dat/delegationactivitytemplate/base/BaseDelegationActivity.java) will 
delegate tasks to heirs of [BaseActivityDelegate](https://github.com/KucherenkoIhor/DelegationActivityTemplate/blob/master/app/src/main/java/com/ki/dat/delegationactivitytemplate/base/BaseActivityDelegate.java). Let’s take a look at the result:
```java
public class NavigationDrawerDelegate extends BaseActivityDelegate<
        NavigationDrawerContract.NavigationDrawerView,
        NavigationDrawerPresenter> implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    protected DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    protected Toolbar mToolBar;
    @BindView(R.id.nav_view)
    protected NavigationView mNavigationView;

    @Override
    public void onCreate(NavigationDrawerPresenter presenter) {
        super.onCreate(presenter);
        configureDrawer();
    }

    private void configureDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mPresenter.getView().getActivity(),
                mDrawerLayout,
                mToolBar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mPresenter.getView().getActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                mPresenter.getView().openCamera();
                break;
            case R.id.nav_gallery:

                break;
            case R.id.nav_slideshow:

                break;
            case R.id.nav_manage:

                break;
            case R.id.nav_share:

                break;
            case R.id.nav_send:
                mPresenter.doSomething();
                break;
        }
        closeDrawer();
        return true;
    }
    
    public boolean closeDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        } else {
            return false;
        }
    }
}
```
As we can see, all logic of Navigation Drawer is contained in [NavigationDrawerDelegate](https://github.com/KucherenkoIhor/DelegationActivityTemplate/blob/master/app/src/main/java/com/ki/dat/delegationactivitytemplate/delegation/NavigationDrawerDelegate.java).
```java
public class NavigationDrawerActivity extends BaseDelegationActivity<
        NavigationDrawerContract.NavigationDrawerView,
        NavigationDrawerPresenter,
        NavigationDrawerDelegate>
        implements NavigationDrawerContract.NavigationDrawerView {

    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    public static Intent newIntent(Context context) {
        return new Intent(context, NavigationDrawerActivity.class);
    }

    @Override
    protected NavigationDrawerDelegate instantiateDelegateInstance() {
        return new NavigationDrawerDelegate();
    }

    @NonNull
    @Override
    protected NavigationDrawerPresenter getPresenterInstance() {
        return new NavigationDrawerPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_navigation_drawer);
        setSupportActionBar(mToolbar);
        super.onCreate(savedInstanceState);
    }

    @Override
    public NavigationDrawerActivity getActivity() {
        return this;
    }

    @OnClick(R.id.fab)
    public void onFabClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void onBackPressed() {
        if (!mDelegate.closeDrawer()) {
            super.onBackPressed();
        }
    }

    @Override
    public void onSomethingDone() {
        Snackbar.make(getContentView(), "Done", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void openCamera() {
        Snackbar.make(getContentView(), "open camera", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
```
We’ve succeeded and our Activity is cleaner.
## Conclusions 
Activity is a sample of [God object anti-pattern](https://en.wikipedia.org/wiki/God_object), that’s why the problem of over-proliferation is actual.
We considered using Delegation pattern to move a part of code into a separate class and created template project.
You can check out [article](https://medium.com/@kucherenkoigor/clean-your-activity-using-delegation-pattern-fcaafd82336d) for more details.
