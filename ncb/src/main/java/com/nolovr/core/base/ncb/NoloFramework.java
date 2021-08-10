package com.nolovr.core.base.ncb;

import android.content.Context;
import android.util.Log;

public class NoloFramework implements TcScreenObserver.IScreenListener {

    private static final String TAG = "NoloFramework";

    static {
        System.loadLibrary("javainfo");
    }

    private static NoloFramework    instance;
    private        TcScreenObserver mScreenObserver;
    private        Context          mContext;

    protected NoloFramework(Context mContext) {
        this.mContext = mContext;
    }

    public static NoloFramework getInstance(Context context) {
        Log.d(TAG, "NoloFramework getInstance: " + context.getPackageManager());
        synchronized (NoloFramework.class) {
            if (instance == null) {
                instance = new NoloFramework(context);
            }
        }
        return instance;
    }

    public void init() {
        if (mScreenObserver == null) {
            mScreenObserver = new TcScreenObserver(mContext, this);
        }
        mScreenObserver.start();
    }

    public void release() {
        if (mScreenObserver != null) {
            mScreenObserver.stop();
            mScreenObserver = null;
        }
    }

    @Override
    public void onScreenOn(Context aContext) {
        Log.d(TAG, "onScreenOn: ");
        setScreenStatus(1);
    }

    @Override
    public void onScreenOff(Context aContext) {
        Log.d(TAG, "onScreenOff: ");
        setScreenStatus(0);
    }

//    public static native String stringFromJNI();

    public static native void setScreenStatus(int status);

    public static native void testdlOpen();
}
