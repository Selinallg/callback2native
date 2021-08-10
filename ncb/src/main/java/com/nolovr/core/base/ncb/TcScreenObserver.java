/**
 * Filename:    TcScreenObserver.java
 * Description:
 * Copyright:   Baidu MIC Copyright(c)2011
 *
 * @author: CoCoMo
 * @version: 1.0
 * Create at:   2013-1-6 下午1:55:30
 * <p>
 * Modification History:
 * Date         Author      Version     Description
 * ------------------------------------------------------------------
 * 2013-1-6    CoCoMo      1.0         1.0 Version
 */
package com.nolovr.core.base.ncb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.util.Log;

/**
 * ScreenObserver
 * Created by Tamic. on 2016-04-15.
 */
public class TcScreenObserver extends BroadcastReceiver {

    /**
     * DEBUG mode
     */
    private static final boolean DEBUG = true;
    /**
     * Log TAG
     */
    private static final String LOG_TAG = TcScreenObserver.class.getSimpleName();

    /**
     * Context
     */
    private Context mContext;
    /**
     * ScreenObserver
     */
    private IScreenListener mListener;

    /**
     * Constructor
     *
     * @param aContext  Context
     * @param aListener IScreenListener
     */
    public TcScreenObserver(Context aContext, IScreenListener aListener) {
        mContext = aContext;
        mListener = aListener;
    }


    public void start() {
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_ON);
            // close by llg
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            mContext.registerReceiver(this, filter);

            if (isScreenOn(mContext)) {
                if (mListener != null) {
                    mListener.onScreenOn(mContext);
                }
            } else {
                if (mListener != null) {
                    mListener.onScreenOff(mContext);
                }
            }
        } catch (Exception e) {
            if (DEBUG) {
                Log.w(LOG_TAG, "start Exception", e);
            }
        }
    }


    public void stop() {
        try {
            mContext.unregisterReceiver(this);
        } catch (Exception e) {
            if (DEBUG) {
                Log.w(LOG_TAG, "stop Exception", e);
            }
        }
    }

    /**
     * isScreenOn
     *
     * @param aContext Context
     */
    public boolean isScreenOn(Context aContext) {
        PowerManager pm = (PowerManager) aContext.getSystemService(Context.POWER_SERVICE);
        return pm.isScreenOn();
    }

    @Override
    public void onReceive(Context aContext, Intent aIntent) {

        Log.d(LOG_TAG, "onReceive: ");
        if (mListener != null) {
            Log.d(LOG_TAG, "onReceive: " + mListener.getClass());
        }
        String action = aIntent.getAction();
        if (Intent.ACTION_SCREEN_ON.equals(action)) {
            if (mListener != null) {
                if (isFastOperationOn()) return;
                mListener.onScreenOn(aContext);
            }
        } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            if (mListener != null) {
                if (isFastOperationOff()) return;
                mListener.onScreenOff(aContext);
            }
        }
    }

    /**
     * IScreenListener
     */
    public interface IScreenListener {


        void onScreenOn(Context aContext);


        void onScreenOff(Context aContext);
    }

    // 两次连接时间间隔 start
    private static final int MIN_CONNECTED_TIME = 3000;  // 两次点击间隔不能少于1000ms
    private static long lastOperationTimeOn;
    private static long lastOperationTimeOff;

    public static boolean isFastOperationOn() {
        boolean flag = true;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastOperationTimeOn) >= MIN_CONNECTED_TIME) {
            flag = false;
        }
        lastOperationTimeOn = currentClickTime;
        return flag;
    }


	public static boolean isFastOperationOff() {
		boolean flag = true;
		long currentClickTime = System.currentTimeMillis();
		if ((currentClickTime - lastOperationTimeOff) >= MIN_CONNECTED_TIME) {
			flag = false;
		}
		lastOperationTimeOff = currentClickTime;
		return flag;
	}
    //  两次连接时间间隔 end

}
