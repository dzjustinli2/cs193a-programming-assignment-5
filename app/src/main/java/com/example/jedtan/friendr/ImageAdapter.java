package com.example.jedtan.friendr;

/**
 * Created by jedtan on 5/6/16.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.util.Log;
import com.squareup.picasso.*;



public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public String[] urls;
    public String[] names;

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return urls.length;
    }

    @Override
    public Object getItem(int position) {
        return urls[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("Hello", "Hello");
        ImageView imageView = new ImageView(mContext);
        Picasso.with(mContext)
                .load(urls[position])
                .into(imageView);
        return imageView;
    }
}

