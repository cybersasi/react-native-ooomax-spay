// ReactNativeSarawakpayModule.java

package com.reactlibrary;

import android.content.pm.PackageManager;
import android.content.Intent;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;

import android.util.Log;

public class ReactNativeSarawakpayModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    Promise promise;

    public ReactNativeSarawakpayModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ReactNativeSarawakpay";
    }

    @ReactMethod
    public void sendRequest(String orderInfo) {
        Intent intent;
        intent = new Intent("my.gov.sarawak.paybills.pay.AuthActivity");
        intent.putExtra("orderInfo", orderInfo);
        intent.putExtra("payFlag", "sharePay");
        intent.putExtra("pkgName", this.reactContext.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.reactContext.startActivity(intent);
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
