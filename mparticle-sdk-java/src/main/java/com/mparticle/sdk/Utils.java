package com.mparticle.sdk;

public final class Utils {

    private Utils() {
    }

    /**
     * <p>Checks if a CharSequence is null or empty.</p>
     *
     * @param cs the CharSequence to check
     * @return {@code true} if the CharSequence is null or empty
     */
    public static boolean isNullOrEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

}
