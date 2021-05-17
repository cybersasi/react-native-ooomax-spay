package com.reactlibrary;

import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import sharepay.paylibrary.BaseCallbackBean;
import sharepay.paylibrary.SarawakAPI;
import sharepay.paylibrary.SarawakPay;
import sharepay.paylibrary.SarawakPayCallback;


public class ReactNativeSarawakpayModule extends ReactContextBaseJavaModule implements SarawakPayCallback  {

    private final ReactApplicationContext reactContext;
    private SarawakAPI mFactory;

    Promise promise;

    public ReactNativeSarawakpayModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ReactNativeSarawakpay";
    }

    @Override
    public void payResult(BaseCallbackBean baseCallbackBean) {
        //其中baseCallbackBean封装了相应的请求信息
        baseCallbackBean.getFlag();
    }

    @ReactMethod
    public void sendRequest(String data) {

        mFactory = SarawakPay.createFactory(reactContext.getCurrentActivity());
        mFactory.sendReq(data, this);
        Log.d("pkgName", "Cannot resolve info for" + data);
        String packageName = reactContext.getPackageName();
        Log.d("pkgName", "Cannot resolve info for" + packageName);
    }

    @ReactMethod
    public void isPackageInstalled(String packageName, final Promise promise) {
        Intent sendIntent = this.reactContext.getPackageManager().getLaunchIntentForPackage(packageName);
        if (sendIntent == null) {
            promise.resolve(false);
            return;
        }
        promise.resolve(true);
    }


}
