import React from "react";
import {decorate, observable, action} from "mobx";

class LoginPageStore {
    userName='';
    password='';
    isLogin=false;
    test="";


    setUserNameAndPassword = (e) => {
      this[e.target.name]=e.target.value;
    }

    validateCredentials = () => {
        if(this.userName === 'admin' && this.password === 'admin') {
            this.isLogin=true;
            test="123"
        }
    }
}
decorate(LoginPageStore, {
    userName:observable,
    password:observable,
    isLogin:observable,
    validateCredentials:action

})

export default LoginPageStore;