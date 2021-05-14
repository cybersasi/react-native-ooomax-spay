// main index.js

import { NativeModules } from 'react-native';

const { ReactNativeSarawakpay } = NativeModules;

export default ReactNativeSarawakpay;

export const OpenSPay = ({orderEncryptedString}) => {
    ReactNativeSarawakpay.sendRequest(orderEncryptedString)
}
