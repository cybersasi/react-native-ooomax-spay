package com.reactlibrary;

import android.util.Log;
import android.content.Intent;
import android.app.Activity;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactContext;

import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;

import sharepay.paylibrary.BaseCallbackBean;
import sharepay.paylibrary.SarawakAPI;
import sharepay.paylibrary.SarawakPay;
import sharepay.paylibrary.SarawakPayCallback;

public class ReactNativeSarawakpayModule extends ReactContextBaseJavaModule implements SarawakPayCallback, LifecycleEventListener  {
    private ReactContext reactContext;
    private SarawakAPI mFactory;
    Promise promise;

    public ReactNativeSarawakpayModule(ReactApplicationContext context) {
        super(context);
        reactContext = context;
        reactContext.addLifecycleEventListener(this);
    }

    @Override
    public String getName() {
        return "ReactNativeSarawakpay";
    }

    @Override
    public void payResult(BaseCallbackBean baseCallbackBean) {
        //其中baseCallbackBean封装了相应的请求信息
        Log.d("payResult", baseCallbackBean.getFlag());
        baseCallbackBean.getFlag();
    }

    @ReactMethod
    public void sendRequest(String data) {
        // final Activity currentActivity = getCurrentActivity();
        // mFactory = SarawakPay.createFactory(currentActivity);

        mFactory.sendReq(data, this);
        Log.d("spay", "Cannot resolve info for" + data);
        String packageName = reactContext.getPackageName();
        Log.d("pkgName", "Cannot resolve info for" + packageName);
    }


    @ReactMethod
    public void isPackageInstalled(String packageName, final Promise promise) {
        Intent sendIntent = reactContext.getPackageManager().getLaunchIntentForPackage(packageName);
        if (sendIntent == null) {
            promise.resolve(false);
            return;
        }
        promise.resolve(true);
    }

    @Override
    public void onHostResume() {
       if (mFactory == null) {
           mFactory = SarawakPay.createFactory(this.getCurrentActivity());
       }
    }
    @Override
    public void onHostPause() {
       // Activity `onPause`
    }
    @Override
    public void onHostDestroy() {
       if (mFactory != null) {
           mFactory.onDestroy();
       }
    }
}


