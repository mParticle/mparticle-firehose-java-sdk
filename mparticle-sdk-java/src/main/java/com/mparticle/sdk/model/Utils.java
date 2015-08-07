package com.mparticle.sdk.model;

public class Utils {

    private Utils() {
    }

    public static boolean isNullOrEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

}
