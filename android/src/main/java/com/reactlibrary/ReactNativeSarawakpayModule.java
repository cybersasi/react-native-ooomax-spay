// ReactNativeSarawakpayModule.java

package com.reactlibrary;

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

import my.gov.sarawak.paybills.IPayAidlInterface;
import my.gov.sarawak.paybills.IPayAidlInterface.Stub;

import android.util.Log;

public class ReactNativeSarawakpayModule extends ReactContextBaseJavaModule implements SarawakPayCallback  {

    private final ReactApplicationContext reactContext;
    private SarawakAPI mFactory;
    private IPayAidlInterface mIPayAidlInterface;
    Promise promise;
    // Context context;

    public ReactNativeSarawakpayModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        // mContext = context;

//        String targetPackName = this.mIPayAidlInterface.getTargetPackName();
//        Log.d("pkgName", targetPackName);
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



    // @ReactMethod
    // public void sendRequest(String orderInfo) {
    //     Intent intent;
    //     intent = new Intent("my.gov.sarawak.paybills.login.InitActivity");
    //     intent.putExtra("orderInfo", orderInfo);
    //     intent.putExtra("payFlag", "sharePay");
    //     intent.putExtra("pkgName", this.reactContext.getPackageName());
    //     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //     // this.reactContext.startActivity(intent);
    //     getReactApplicationContext().startActivity(intent);
    // }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
    }

    @ReactMethod
    public void sendRequest(String data) {
        // if (this.mIPayAidlInterface != null && this.mIsBind) {
        //     Log.d("pkgName", "Not working" );
        // }
        mFactory = SarawakPay.createFactory(this.reactContext);
        mFactory.sendReq(data, ReactNativeSarawakpayModule.this);
        Log.d("pkgName", "Cannot resolve info for" + data);
        String packageName = this.reactContext.getPackageName();
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
