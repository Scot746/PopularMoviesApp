package pedroscott.com.popularmoviesapp.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * Created by android4 on 1/14/16.
 */
public class IntentUtils {

    public static void goToYooTube(Context context, String url){
        context.startActivity(newYouTubeIntent(context.getPackageManager(),url));
    }
    /**
     * Intent to open a YouTube Video
     *
     * @param pm  The {@link PackageManager}.
     * @param url The URL or YouTube video ID.
     * @return the intent to open the YouTube app or Web Browser to play the video
     */
    private static Intent newYouTubeIntent(PackageManager pm, String url) {
        Intent intent;
        if (url.length() == 11) {
            // youtube video id
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + url));
        } else {
            // url to video
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        }
        try {
            if (pm.getPackageInfo("com.google.android.youtube", 0) != null) {
                intent.setPackage("com.google.android.youtube");
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return intent;
    }

}
