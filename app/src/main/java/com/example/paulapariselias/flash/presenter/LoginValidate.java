package com.example.paulapariselias.flash.presenter;

import com.example.paulapariselias.flash.data.CurrentUser;

/**
 * Created by paulapariselias on 26-08-17.
 */

public class LoginValidate {

    private LoginCallback callback;

    public LoginValidate(LoginCallback callback) {
        this.callback = callback;

    }

    public  void  userLogin(){

        if(new CurrentUser().getCurrentUser() != null){

            callback.logged();
        }else {
            callback.singUp();
        }
    }

}
