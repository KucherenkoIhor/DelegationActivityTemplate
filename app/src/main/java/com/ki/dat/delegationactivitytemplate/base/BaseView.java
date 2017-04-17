package com.ki.dat.delegationactivitytemplate.base;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.View;

/**
 * Created by ihor on 05.04.17.
 */

public interface BaseView {

    void showProgress();

    void hideProgress();

    View getContentView();

}
