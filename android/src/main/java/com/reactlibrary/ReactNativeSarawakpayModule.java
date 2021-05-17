package com.reactlibrary;

package com.reactlibrary;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;

import sharepay.paylibrary.BaseCallbackBean;
import sharepay.paylibrary.SarawakAPI;
import sharepay.paylibrary.SarawakPay;
import sharepay.paylibrary.SarawakPayCallback;

import android.util.Log;


public class ReactNativeSarawakpayModule extends ReactContextBaseJavaModule implements SarawakPayCallback  {

    private final ReactApplicationContext reactContext;
    private SarawakAPI mFactory;

    Promise promise;
    Activity mActivity;

    public ReactNativeSarawakpayModule(ReactApplicationContext reactContext, Activity mActivity) {
        super(reactContext);
        this.reactContext = reactContext;
        this.mActivity = mActivity;
        mFactory = SarawakPay.createFactory(mActivity);
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
        // final Activity activity = getCurrentActivity();
        // mFactory = SarawakPay.createFactory(activity);
        mFactory.sendReq(data, ReactNativeSarawakpayModule.this);
        Log.d("pkgName", "Cannot resolve info for" + data);
        String packageName = mActivity.getPackageName();
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
