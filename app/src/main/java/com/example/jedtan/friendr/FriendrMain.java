package com.example.jedtan.friendr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.util.Log;

public class FriendrMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendr_main);
    }

    public void goToUserList(View view){
        Log.d("hi", "hi");
        Intent intent = new Intent(this, ViewUsersActivity.class);
        startActivity(intent);
    }
}
