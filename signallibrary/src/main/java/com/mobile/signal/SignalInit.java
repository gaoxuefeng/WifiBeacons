package com.mobile.signal;

import android.content.Context;

/**
 * @author gaoxuefeng
 * @date 2018/3/26
 */

public class SignalInit {
    private static Context context;

    public static void init(Context context) {
        SignalInit.context = context;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        SignalInit.context = context;
    }
}
