package com.ki.dat.delegationactivitytemplate.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ki.dat.delegationactivitytemplate.R;
import com.ki.dat.delegationactivitytemplate.simple.SimpleContract;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ihor on 05.04.17.
 */

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
    public void showProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mProgressDialog == null || !mProgressDialog.isShowing()) {
                    mProgressDialog = new ProgressDialog(BaseActivity.this);
                    mProgressDialog.setMessage(getString(R.string.loading));
                    mProgressDialog.setCancelable(false);
                    mProgressDialog.show();
                }
            }
        });

    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mProgressDialog != null && mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                }
            }
        });
    }

    @Override
    public View getContentView() {
        return getWindow().getDecorView();
    }
}
