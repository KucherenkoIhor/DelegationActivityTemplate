package com.ki.dat.delegationactivitytemplate.base;

/**
 * Created by ihor on 05.04.17.
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);

    void detachView();


}
