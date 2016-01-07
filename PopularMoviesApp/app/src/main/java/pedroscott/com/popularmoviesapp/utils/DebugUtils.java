package pedroscott.com.popularmoviesapp.utils;

import android.support.v7.appcompat.BuildConfig;
import android.util.Log;



/**
 * Created by Pedro Scott
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
