// main index.js
import { NativeModules,  Platform, Linking  } from 'react-native';

const { ReactNativeSarawakpay } = NativeModules;

export default ReactNativeSarawakpay;

export const OpenSPay = async (orderEncryptedString, pkgName = 'bereach') => {
    if(Platform.OS === 'android'){
        console.log('run Android')
        const isInstalledAndroid = await ReactNativeSarawakpay.isPackageInstalled("my.gov.sarawak.paybills")
        if(!isInstalledAndroid){
            // ask user to go download app 
            Linking.openURL("https://play.google.com/store/apps/details?id=my.gov.sarawak.paybills")
        } else {
           return ReactNativeSarawakpay.sendRequest(orderEncryptedString)
        }
        ReactNativeSarawakpay.sendRequest(orderEncryptedString)
    } else if(Platform.OS === 'ios'){
        const isInstalledIos = await Linking.canOpenURL('sarawakpay://bereach')
        if(!isInstalledIos){
            await Linking.openURL('https://apps.apple.com/in/app/sarawak-pay/id1250845042')
        } else {
           return await Linking.openURL(`sarawakpay://${pkgName}?${orderEncryptedString}`)
        }
    } else {
        throw 'Platform not support'
    }
}

