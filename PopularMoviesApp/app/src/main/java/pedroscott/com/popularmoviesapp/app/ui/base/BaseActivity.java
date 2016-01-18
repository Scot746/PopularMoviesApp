package pedroscott.com.popularmoviesapp.app.ui.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import pedroscott.com.popularmoviesapp.R;

/**
 * Copyright (C) 2015 The Android Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class BaseActivity extends AppCompatActivity {

    protected ArrayList<String> titleStack = new ArrayList<String>();
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Get the toolbar in the baseActivity instance.
     */
    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * Set the toolbar to the baseActivity instance.
     */
    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }

    /**
     * Method to navigate using  FragmentTransaction and FragmentManager.
     */
    public static void navigateTo(FragmentManager manager, Fragment newFragment, int containerId, boolean options) {
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(containerId, newFragment, newFragment.getClass().getSimpleName());
        if (options) {
            ft.addToBackStack(newFragment.getClass().getSimpleName());
        }
        ft.commit();
    }

    /**
     * clean Fragment Stack in the FragmentManager.
     */
    public static void cleanFragmentStack(FragmentManager fm) {
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * Update the toolbar title.
     */
    public void updateActionBarTitle() {
        if (getToolbar() != null) {
            getToolbar().setTitle(titleStack.get(titleStack.size() - 1));
        }
    }

    /**
     * Navigate in the R.id.container with the SupportFragmentManager.
     */
    public void navigateMainContent(Fragment frg, String title) {
        cleanFragmentStack(getSupportFragmentManager());
        navigateTo(getSupportFragmentManager(), frg, R.id.container, false);
        titleStack.clear();
        titleStack.add(title);
        updateActionBarTitle();
    }

    /**
     * Navigate in the any FrameLayout container with the SupportFragmentManager.
     */
    public void navigateDetailContent(Fragment frg, String title, int container) {
        cleanFragmentStack(getSupportFragmentManager());
        navigateTo(getSupportFragmentManager(), frg, container, false);
        titleStack.clear();
        titleStack.add(title);
        updateActionBarTitle();
    }

}
