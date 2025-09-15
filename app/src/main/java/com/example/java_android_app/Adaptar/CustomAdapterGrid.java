package com.example.java_android_app.Adaptar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.java_android_app.R;
import com.example.java_android_app.databinding.GridItemBinding;

public class CustomAdapterGrid extends BaseAdapter {

    private Context context ;
    private String[] items;
    private int[] images;
    private LayoutInflater inflater ;

    public CustomAdapterGrid(Context context, String[] items, int[] images, LayoutInflater inflater) {
        this.context = context;
        this.items = items;
        this.images = images;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        GridItemBinding binding ;
        if(convertView == null){
            binding = GridItemBinding.inflate(inflater,parent,false);
            convertView = binding.getRoot();
            convertView.setTag(binding);
        }else{
            binding = (GridItemBinding) convertView.getTag();
        }

        binding.itemImage.setImageResource(images[position]);
        binding.itemText.setText(items[position]);

        return convertView;
    }
}
