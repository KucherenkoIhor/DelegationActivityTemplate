package com.ki.dat.delegationactivitytemplate.base;

/**
 * Created by ihor on 05.04.17.
 */

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
