package com.yalinwang.palcards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ywang28 on 2/7/16.
 */
public class ImageGridViewAdapter extends BaseAdapter {
    private final Context context;
    private static final String IMAGE_BASE_URL = "http://myexpensemanager.herokuapp.com/images/";
    private final List<String> imageUrls = new ArrayList<>();

    public ImageGridViewAdapter(Context context, int imageCount) {
        this.context = context;

        for (int i = 1; i <= imageCount; i++)  {
            imageUrls.add(IMAGE_BASE_URL + i + ".png");
        }

        Collections.shuffle(imageUrls);
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public String getItem(int position) {
        return imageUrls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView view = (ImageView) convertView;
        if (view == null) {
            view = new ImageView(context);
            view.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }

        // Get the image URL for the current position.
        String url = getItem(position);

        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(context) //
                .load(url) //
                .placeholder(R.drawable.loading) //
                .error(R.drawable.error_button) //
                .fit()
                .centerInside()//
                .tag(context) //
                .into(view);

        return view;
    }
}
