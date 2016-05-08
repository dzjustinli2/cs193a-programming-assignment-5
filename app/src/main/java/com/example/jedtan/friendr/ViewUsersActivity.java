package com.example.jedtan.friendr;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import com.koushikdutta.ion.*;
import com.koushikdutta.async.future.*;
import stanford.androidlib.*;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.view.Window;
import android.content.Intent;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class ViewUsersActivity extends AppCompatActivity {
    private ImageAdapter imageAdapter;
    private GridView gridView;

    private String[] users;
    private String[] names;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        gridView = (GridView) findViewById(R.id.gridView);

         //Instance of ImageAdapter Class
        imageAdapter = new ImageAdapter(this);

        Ion.with(this)
                .load("http://www.martystepp.com/friendr/pokemon/list")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String urlText) {
                        users = urlText.split("\n");
                        names = users.clone();
                        for (int i = 0; i < users.length; i++) {
                            users[i] = "http://www.martystepp.com/friendr/pokemon/" + users[i].toLowerCase() + ".jpg";
                       }
                        imageAdapter.urls = users;
                        gridView.setAdapter(imageAdapter);



                    }
                });
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                    intent.putExtra("name", names[position]);
                    startActivity(intent);
                }
                else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

                    Bundle bundle = new Bundle();
                    bundle.putString("name", names[position]);
                    ProfileFragment fragInfo = new ProfileFragment();
                    fragInfo.setArguments(bundle);
                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.fragment, fragInfo);
                    ft.commit();
                }



            }
        });


    }
}
