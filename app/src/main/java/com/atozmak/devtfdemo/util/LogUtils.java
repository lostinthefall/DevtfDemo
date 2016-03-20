package com.atozmak.devtfdemo.util;

/**
 * Created by Mak on 2016/3/18.
 */
public class LogUtils {
    private static final String LOG_PREFIX = "devtf_";
    private static final int MAX_LOG_TAG_LENGTH = 23;
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();

    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }
        return LOG_PREFIX + str;
    }

    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }


}
