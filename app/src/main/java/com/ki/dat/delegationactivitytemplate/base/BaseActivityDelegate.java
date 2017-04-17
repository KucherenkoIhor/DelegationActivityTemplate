package com.ki.dat.delegationactivitytemplate.base;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ihor on 17.04.17.
 */

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
