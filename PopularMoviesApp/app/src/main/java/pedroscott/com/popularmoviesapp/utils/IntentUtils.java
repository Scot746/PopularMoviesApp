package pedroscott.com.popularmoviesapp.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import pedroscott.com.popularmoviesapp.R;


/**
 * Copyright (C) 2015 The Android Open Source Project
 * <p/>8
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
public class IntentUtils {

    public static void goToYouTube(Context context, String url) {
        try {
            context.startActivity(newYouTubeIntent(context.getPackageManager(), url));
        }catch (ActivityNotFoundException exception){
            context.startActivity(newWebBrowser(context.getString(R.string.url_video_youtube,url)));
        }
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

    private static Intent newWebBrowser(String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        return i;
    }


}
