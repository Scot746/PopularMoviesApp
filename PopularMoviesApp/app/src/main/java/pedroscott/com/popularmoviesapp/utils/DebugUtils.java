package pedroscott.com.popularmoviesapp.utils;

import android.support.v7.appcompat.BuildConfig;
import android.util.Log;

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
public class DebugUtils {

    public static enum DebugMessageType {
        ERROR,
        DEBUG,
        VERBOSE,
        INFO,
        WARNING
    }

    public static void PrintLogMessage(String tag, String message, DebugMessageType type) {
        if (BuildConfig.DEBUG) {
            switch (type) {
                case DEBUG:
                    Log.d(tag, message);
                    break;
                case ERROR:
                    Log.e(tag, message);
                    break;
                case VERBOSE:
                    Log.v(tag, message);
                    break;
                case INFO:
                    Log.i(tag, message);
                    break;
                case WARNING:
                    Log.w(tag, message);
                    break;
                default:
                    Log.d(tag, message);
                    break;
            }

        }
    }

}
