package com.ki.dat.delegationactivitytemplate.simple;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.ki.dat.delegationactivitytemplate.R;
import com.ki.dat.delegationactivitytemplate.base.BaseActivity;
import com.ki.dat.delegationactivitytemplate.delegation.NavigationDrawerActivity;

import butterknife.OnClick;

public class SimpleActivity extends BaseActivity<SimpleContract.SimplePresenter>
    implements SimpleContract.SimpleView {

    @NonNull
    @Override
    protected SimpleContract.SimplePresenter getPresenterInstance() {
        return new SimplePresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
    }

    @OnClick(R.id.btnGo)
    public void onBtnGoClick() {
        mPresenter.doSomething();
    }

    @Override
    public void onSomethingDone() {
        startActivity(NavigationDrawerActivity.newIntent(this));
    }
}
