package pedroscott.com.popularmoviesapp.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;

import pedroscott.com.popularmoviesapp.R;
import pedroscott.com.popularmoviesapp.app.db.DBQLiteHelper;
import pedroscott.com.popularmoviesapp.rest.RestClientPublic;

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
public class App extends MultiDexApplication {

    private static RestClientPublic restClientPublic;
    private static DBQLiteHelper sqlExternalHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        restClientPublic = new RestClientPublic(getString(R.string.base_url),
                getString(R.string.api_key_themoviedb));
        stetho();
    }

    private void stetho() {
        Stetho.initializeWithDefaults(this);
    }

    /**
     * get the Instance of the rest client to call web services.
     */
    public static RestClientPublic getRestClientPublic() {
        return restClientPublic;
    }

    public static DBQLiteHelper getDBExternalHelper(Context context) {
        if (sqlExternalHelper == null) {
            sqlExternalHelper = new DBQLiteHelper(context);
        }
        return sqlExternalHelper;
    }


}
