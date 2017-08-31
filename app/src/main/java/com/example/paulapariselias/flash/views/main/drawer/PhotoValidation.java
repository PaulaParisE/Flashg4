package com.example.paulapariselias.flash.views.main.drawer;

import android.content.Context;

import com.example.paulapariselias.flash.data.PhotoPreference;

/**
 * Created by paulapariselias on 30-08-17.
 */

public class PhotoValidation {

    private Context context;
    private PhotoCallback callback;

    public PhotoValidation(Context context, PhotoCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void validate () {
        String url = new PhotoPreference(context).getPhoto();
        if (url != null){
            callback.photoAvailable(url);

        }else {

            callback.emptyPhoto();
        }
    }
}
