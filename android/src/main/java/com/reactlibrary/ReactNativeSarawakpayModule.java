// ReactNativeSarawakpayModule.java

package com.reactlibrary;

import android.content.pm.PackageManager;
import android.content.Intent;

// below to be delete is no use 
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;


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
import sharepay.paylibrary.BaseCallbackBean.Builder;

import android.util.Log;

public class ReactNativeSarawakpayModule extends ReactContextBaseJavaModule   {
    private final ReactApplicationContext reactContext;
    private SarawakAPI mFactory;
    private IPayAidlInterface mIPayAidlInterface;
    private final boolean mIsBind;
    Promise promise;


    private ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder service) {
            ReactNativeSarawakpayModule.this.mIPayAidlInterface = Stub.asInterface(service);
        }

        public void onServiceDisconnected(ComponentName name) {
        }
    };

    public ReactNativeSarawakpayModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;

        Intent intent = new Intent();
        intent.setComponent(new ComponentName("my.gov.sarawak.paybills", "my.gov.sarawak.paybills.pay.SarawakPayPayService"));
        this.mIsBind = this.reactContext.bindService(intent, this.mServiceConnection, 1);
    }

    @ReactMethod
    public void test(Callback callback) {
        // TODO: Implement some actually useful functionality
        if (this.mIPayAidlInterface != null && this.mIsBind) {
            String targetPackName = this.mIPayAidlInterface.getTargetPackName();
            callback.invoke("ok ok" + targetPackName);
            return ;
        }
        callback.invoke("Cannot show");
    }

    @Override
    public String getName() {
        return "ReactNativeSarawakpay";
    }



    // @ReactMethod
    // public void sendRequest(String orderInfo) {
    //     Intent intent;
    //     intent = new Intent("my.gov.sarawak.paybills.login.InitActivity");
    //     intent.putExtra("orderInfo", orderInfo);
    //     intent.putExtra("payFlag", "sharePay");
    //     intent.putExtra("pkgName", this.reactContext.getPackageName());
    //     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    //     this.reactContext.startActivity(intent);
    // }

    @ReactMethod
    public void sampleMethod(String stringArgument, int numberArgument, Callback callback) {
        // TODO: Implement some actually useful functionality
        callback.invoke("Received numberArgument: " + numberArgument + " stringArgument: " + stringArgument);
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
