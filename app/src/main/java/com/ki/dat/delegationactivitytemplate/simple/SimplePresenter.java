package com.ki.dat.delegationactivitytemplate.simple;

import com.ki.dat.delegationactivitytemplate.base.BasePresenterImpl;

/**
 * Created by ihor on 17.04.17.
 */

public class SimplePresenter extends BasePresenterImpl<SimpleContract.SimpleView>
        implements SimpleContract.SimplePresenter {

    @Override
    public void doSomething() {
        mView.onSomethingDone();
    }

}
