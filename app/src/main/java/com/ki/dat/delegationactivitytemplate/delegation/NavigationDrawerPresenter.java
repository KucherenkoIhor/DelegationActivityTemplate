package com.ki.dat.delegationactivitytemplate.delegation;

import com.ki.dat.delegationactivitytemplate.base.BasePresenterImpl;

/**
 * Created by ihor on 17.04.17.
 */

public class NavigationDrawerPresenter
        extends BasePresenterImpl<NavigationDrawerContract.NavigationDrawerView>
        implements NavigationDrawerContract.NavigationDrawerPresenter {

    @Override
    public void doSomething() {
        mView.onSomethingDone();
    }
}
