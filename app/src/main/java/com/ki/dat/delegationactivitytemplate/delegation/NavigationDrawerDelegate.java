package com.ki.dat.delegationactivitytemplate.delegation;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ki.dat.delegationactivitytemplate.R;
import com.ki.dat.delegationactivitytemplate.base.BaseActivityDelegate;

import butterknife.BindView;

/**
 * Created by ihor on 17.04.17.
 */

public class NavigationDrawerDelegate extends BaseActivityDelegate<
        NavigationDrawerContract.NavigationDrawerView,
        NavigationDrawerPresenter> implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    protected DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar)
    protected Toolbar mToolBar;
    @BindView(R.id.nav_view)
    protected NavigationView mNavigationView;

    @Override
    public void onCreate(NavigationDrawerPresenter presenter) {
        super.onCreate(presenter);
        configureDrawer();
    }

    private void configureDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                mPresenter.getView().getActivity(),
                mDrawerLayout,
                mToolBar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mPresenter.getView().getActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                mPresenter.getView().openCamera();
                break;
            case R.id.nav_gallery:

                break;
            case R.id.nav_slideshow:

                break;
            case R.id.nav_manage:

                break;
            case R.id.nav_share:

                break;
            case R.id.nav_send:
                mPresenter.doSomething();
                break;
        }
        closeDrawer();
        return true;
    }

    /***
     *
     * @return true if Drawer was opened.
     */
    public boolean closeDrawer() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return true;
        } else {
            return false;
        }
    }

}
