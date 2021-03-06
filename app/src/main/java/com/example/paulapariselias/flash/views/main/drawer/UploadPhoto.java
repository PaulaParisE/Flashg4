package com.example.paulapariselias.flash.views.main.drawer;

import android.content.Context;
import android.net.Uri;

import com.example.paulapariselias.flash.data.CurrentUser;
import com.example.paulapariselias.flash.data.Nodes;
import com.example.paulapariselias.flash.data.PhotoPreference;
import com.example.paulapariselias.flash.models.LocalUser;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by paulapariselias on 30-08-17.
 */

public class UploadPhoto {

    private Context context;


    public UploadPhoto(Context context) {
        this.context = context;
    }

    public void toFireBase (String path){
        final CurrentUser currentUser = new CurrentUser();
        String folder = currentUser.sanitizedEmail(currentUser.email()+ "/");
        String photoName = "avatar.jpeg";
        String baseUrl = "gs://flash-e12b3.appspot.com/avatars/";
        String refUrl = baseUrl + folder + photoName;
        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(refUrl);
        storageReference.putFile(Uri.parse(path)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                @SuppressWarnings("VisibleForTests")String[] fullUrl = taskSnapshot.getDownloadUrl().toString().split("&token");
                String url = fullUrl[0];

                new PhotoPreference(context).photoSave(url);
                LocalUser user = new LocalUser();
                user.setEmail(currentUser.email());
                user.setName(currentUser.getCurrentUser().getDisplayName());
                user.setPhoto(url);
                user.setUid(currentUser.uid());

                String key = currentUser.sanitizedEmail(currentUser.email());

                new Nodes().user(key).setValue(user);



            }
        });


    }
}
