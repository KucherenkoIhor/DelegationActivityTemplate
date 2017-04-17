package com.ki.dat.delegationactivitytemplate.delegation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ki.dat.delegationactivitytemplate.R;
import com.ki.dat.delegationactivitytemplate.base.BaseDelegationActivity;

import butterknife.BindView;
import butterknife.OnClick;

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
