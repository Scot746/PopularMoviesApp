package pedroscott.com.popularmoviesapp.app;

import android.support.multidex.MultiDexApplication;

import pedroscott.com.popularmoviesapp.R;
import pedroscott.com.popularmoviesapp.rest.RestClientPublic;

/**
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class App extends MultiDexApplication {

    private static RestClientPublic restClientPublic;

    @Override
    public void onCreate() {
        super.onCreate();
        restClientPublic = new RestClientPublic(getString(R.string.base_url));
    }

    /**
     * get the Instance of the rest client to call web services.
     */
    public static RestClientPublic getRestClientPublic() {
        return restClientPublic;
    }

}
