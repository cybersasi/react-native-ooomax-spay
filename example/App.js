/**
 * Sample React Native App
 *
 * adapted from App.js generated by the following command:
 *
 * react-native init example
 *
 * https://github.com/facebook/react-native
 */

import React, { Component } from 'react';
import { Platform, StyleSheet, Text, View, Button, Alert } from 'react-native';
import ReactNativeSarawakpayModule, {OpenSPay} from 'react-native-sarawakpay';

const orderEncryptedString = "MDAwMjU2R8/YMSpuAD8bFzmeozXOtjCRcvldFFwOzIP9VSWJ/mCJPgOgr/DA2WQw7O5E6pjCxbPWyXkm3NZFKyqFEsNjzy9m2uACYg7df4E+0tWiFSFpPV3G/PcXwkZ2wnUJBZZK5qSJax9k6kpiahkIcui484Xk7zXHnVm9CB6OvOS11T1tD3Q7gWsezNZPXw93orJtEQvmpmI6FgCT2MN4Ql+EwcjtCCLFSTVfVMzaXQjamq+ctTmYfqLw5yBSG+7j2of+y/KtjdyEYyzjilurF8SFH7RnH0nkt+1t9vmFFRqmd4IcsigDivgBMhO5EOwuwqDxTtFkfti2itPhREbLWpdknczxirMsc7aFFY2YwM/vhfOzpCoUzsAOP80gkIvJhVzIw8B8Kd8hWXfAOsielSsW6jTTyS9jeiL0d2ff05iNUxyZPkF00vJO2S5pUQhjx9IdRx7VlMQl/cxexOdyHVOe8Fh6wgCviXdhHma75g/zdkglBNcKjbAW/7xIXIKUpp9NxhQPPoNTxHBzUi5DmYDkKUVNJqtEtJn/9QO/DsMWh0cuwblKvIRkoi8KT9d6Mo7BM9RIDTRqD9mLCjHElkhySl7rDPIUbIo2/cfH+kKooin4HLfMD7R09PU+dvZr8PvUtOPth1IYLxj+ScYE7Dtd7wVDc8qLPZb3Au83I1F+3FIST9uZcesnbRYTzquhYzM0TyoMrWAP1fPId9vmD4xvD/Kk6Q8q1/LEx9UTLN33LCVD1y6NnFE1b5JC8irL/lf4isYnLdVO3bRYmJqwGqRWgM2RNP/PwdEkX2eJ8JPoxgYr+cjkVvy4nKVshARWmCgpIl28FuLPfHYjuH3du4irU/Hv0lwCuWke9lMK5VRfrq2R0Qgp8E/AT3eb4aTB6OWUpg1LmDKBJHlRUw0IaIcYhtQqOMWgfU+ATe42r3OuW6+tIkVM8ewzmlUl7rFZ+4TJkY06Nyd3qX6ZY3xxJ0KyHPl0iL5Bx1BJS65s43TwjbOc/5jj0WC9XSGgxEIu9ROkUqFVr/B0qo5nuKZogDn3gfLjAzifmm2G1kuLRPggIQE="
  
export default class App extends Component<{}> {
  state = {
    status: 'starting',
    message: '--'
  };
  // componentDidMount() {
  //   ReactNativeSarawakpay.sampleMethod('Testing', 123, (message) => {
  //     this.setState({
  //       status: 'native callback received',
  //       message
  //     });
  //   });
  // }

  onPress = () => {

    OpenSPay(orderEncryptedString)
    // ReactNativeSarawakpayModule.test(res => {
    //   console.log(res)
    // })
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>☆ReactNativeSarawakpay example☆</Text>
        <Text style={styles.instructions}>STATUS: {this.state.status}</Text>
        <Text style={styles.welcome}>☆NATIVE CALLBACK MESSAGE☆</Text>
        <Text style={styles.instructions}>{this.state.message}</Text>
        <Button
          title="Click to test start SarawakPay"
          color="#841584"
          onPress={this.onPress}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
