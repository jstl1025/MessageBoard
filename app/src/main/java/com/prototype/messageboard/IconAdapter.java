package com.prototype.messageboard;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class IconAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<StorageReference> storageRef;

    public IconAdapter(Context context, ArrayList<StorageReference> storageRef) {
        this.context = context;
        this.storageRef = storageRef;
    }

    @Override
    public int getCount() {
        return storageRef.size();
    }

    @Override
    public Object getItem(int position) {
        return storageRef.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(250, 250));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else{
            imageView = (ImageView) convertView;
        }
        GlideApp.with(context).load(storageRef.get(position)).into(imageView);
        return imageView;
    }
}
