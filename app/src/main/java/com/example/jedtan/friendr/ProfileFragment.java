package com.example.jedtan.friendr;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.preference.PreferenceManager;
import android.view.Display;

import android.graphics.Point;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Bundle instanceState;

    private Context thisContext;


    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d("here", "here");
        thisContext = getActivity().getApplicationContext();
        instanceState = getArguments();
        super.onActivityCreated(savedInstanceState);

        TextView nameContainer = (TextView) getView().findViewById(R.id.nameView);
        ImageView imageView= (ImageView) getView().findViewById(R.id.imageFinalView);
        RatingBar ratingBar = (RatingBar) getView().findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(thisContext);
                SharedPreferences.Editor prefsEditor = prefs.edit();
                prefsEditor.putInt(instanceState.getString("name"), (int)rating);
                prefsEditor.apply();
            }
        });


        if (instanceState == null){
            Log.d("was null", "Hello");
            nameContainer.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.INVISIBLE);
            ratingBar.setVisibility(View.INVISIBLE);
        }
        else{
            String extra = instanceState.getString("name");
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(thisContext);
            int i = prefs.getInt(extra, 0);
            ratingBar.setRating(i);

            Display display = getActivity().getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = (int) (size.x * .7);
            int height = size.y;


            nameContainer.setText(extra);
            Log.d("bleh", "http://www.martystepp.com/friendr/pokemon/" + extra.toLowerCase() + ".jpg");
            Picasso.with(getActivity().getApplicationContext())
                    .load("http://www.martystepp.com/friendr/pokemon/" + extra.toLowerCase() + ".jpg")
                    .resize(0, width)
                    .into(imageView);


        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("Here", "oncreateview");
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
