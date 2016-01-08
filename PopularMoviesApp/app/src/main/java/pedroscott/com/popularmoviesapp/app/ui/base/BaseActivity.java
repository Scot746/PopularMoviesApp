package pedroscott.com.popularmoviesapp.app.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import pedroscott.com.popularmoviesapp.R;

public class BaseActivity extends AppCompatActivity {

    protected ArrayList<String> titleStack = new ArrayList<String>();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    public static void navigateTo(FragmentManager manager, Fragment newFragment, int containerId, boolean options) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(containerId, newFragment, newFragment.getClass().getSimpleName());
        if (options) {
            ft.addToBackStack(newFragment.getClass().getSimpleName());
        }
        ft.commit();
    }

    public static void cleanFragmentStack(FragmentManager fm) {
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void updateActionBarTitle() {
        if (getToolbar() != null) {
            getToolbar().setTitle(titleStack.get(titleStack.size() - 1));
        }
    }

    public void navigateMainContent(Fragment frg, String title) {
        cleanFragmentStack(getSupportFragmentManager());
        navigateTo(getSupportFragmentManager(), frg, R.id.container, false);
        titleStack.clear();
        titleStack.add(title);
        updateActionBarTitle();
    }

    public void navigateToLowLevel(Fragment frg, String title) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        titleStack.add(title);
        navigateTo(getSupportFragmentManager(), frg, R.id.container, true);
        updateActionBarTitle();

    }


    @Override
    public void onBackPressed() {
        if ((titleStack.size()) > 0) {
            titleStack.remove(titleStack.size() - 1);
        }
        if (titleStack.size() == 1) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setHomeButtonEnabled(false);
        }
        if (titleStack.size() > 0) {
            updateActionBarTitle();
        }
        super.onBackPressed();
    }


}
