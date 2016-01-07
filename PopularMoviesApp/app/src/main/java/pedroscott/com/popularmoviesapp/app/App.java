package pedroscott.com.popularmoviesapp.app;

import android.support.multidex.MultiDexApplication;

import pedroscott.com.popularmoviesapp.R;
import pedroscott.com.popularmoviesapp.rest.RestClientPublic;

/**
 * Created by android4 on 1/6/16.
 */
public class App extends MultiDexApplication {

    private static RestClientPublic restClientPublic;
    @Override
    public void onCreate() {
        super.onCreate();
        restClientPublic = new RestClientPublic(getString(R.string.base_url));
    }

    public static RestClientPublic getRestClientPublic() {
        return restClientPublic;
    }

}
