package com.example.paulapariselias.flash.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.paulapariselias.flash.R;
import com.example.paulapariselias.flash.presenter.LoginCallback;
import com.example.paulapariselias.flash.presenter.LoginValidate;
import com.example.paulapariselias.flash.views.main.MainActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ResultCodes;

import java.util.Arrays;


public class LoginActivity extends AppCompatActivity implements LoginCallback {

    private static final int RC_SIGN_IN = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

            new LoginValidate(this).userLogin();




    }

    public void singUp(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(
                                Arrays.asList(
                                        new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()/*,
                                        new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build()*/))
                        .setTheme(R.style.LoginTheme)
                        .setLogo(R.mipmap.logo)

                        .build(),
                RC_SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (RC_SIGN_IN == requestCode){
            if (ResultCodes.OK == resultCode){
                logged();
            }
        }

    }

    public void logged() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }




}
