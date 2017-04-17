package com.ki.dat.delegationactivitytemplate.delegation;

import com.ki.dat.delegationactivitytemplate.base.BasePresenter;
import com.ki.dat.delegationactivitytemplate.base.BaseView;

/**
 * Created by ihor on 17.04.17.
 */

public class NavigationDrawerContract {

    interface NavigationDrawerView extends BaseView {

        void openCamera();

        void onSomethingDone();

        NavigationDrawerActivity getActivity();

    }

    interface NavigationDrawerPresenter extends BasePresenter<NavigationDrawerView> {

        void doSomething();

    }
}
