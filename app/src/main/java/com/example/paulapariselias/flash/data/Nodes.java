package com.example.paulapariselias.flash.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



/**
 * Created by paulapariselias on 31-08-17.
 */

public class Nodes {

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference();


    public DatabaseReference users () {
        return root.child("users");
    }

    public DatabaseReference user(String key){

        return users().child(key);
    }
}
