package com.yalinwang.palcards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * Created by ywang28 on 2/7/16.
 */
public class ImageGridViewAdapter extends BaseAdapter {

    private final Context context;
    private int count;


    public ImageGridViewAdapter(Context context, int imageCount) {
        this.context = context;
        this.count = imageCount;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public String getItem(int position) {
        return String.valueOf(position);
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
            view.setScaleType(ImageView.ScaleType.FIT_XY);
        }

        view.setImageResource(R.drawable.card_back);
        view.setAdjustViewBounds(true);
        view.setPadding(5, 5, 5, 5);

        return view;
    }
}
