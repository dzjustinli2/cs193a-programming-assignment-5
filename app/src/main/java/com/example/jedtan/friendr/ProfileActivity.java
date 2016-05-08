package com.example.jedtan.friendr;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.util.Log;
import android.content.*;

public class ProfileActivity extends AppCompatActivity {

    private String extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);


        Intent intent = getIntent();
        extra = intent.getStringExtra("name");

        Bundle bundle = new Bundle();
        bundle.putString("name", extra);
        ProfileFragment fragInfo = new ProfileFragment();
        fragInfo.setArguments(bundle);
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment, fragInfo);
        ft.commit();


    }

}
