package com.ki.dat.delegationactivitytemplate.simple;

import com.ki.dat.delegationactivitytemplate.base.BasePresenter;
import com.ki.dat.delegationactivitytemplate.base.BasePresenterImpl;
import com.ki.dat.delegationactivitytemplate.base.BaseView;

/**
 * Created by ihor on 17.04.17.
 */

public class SimpleContract {

    interface SimpleView extends BaseView {
        void onSomethingDone();
    }

    interface SimplePresenter extends BasePresenter<SimpleView> {
        void doSomething();
    }

}
