package com.nolovr.core.base.ncb;

public class NoloFramework {

    static {
        System.loadLibrary("javainfo");
    }

    public static native String stringFromJNI();
}
