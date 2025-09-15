package com.example.myapplication.Adaptar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.myapplication.databinding.ListItemBinding;

public class CustomAdapterList extends BaseAdapter {
    private Context context ;
    private int[] images;
    private String[] items;
    private LayoutInflater inflater ;

    public CustomAdapterList(Context context, int[] images, String[] items, LayoutInflater inflater) {
        this.context = context;
        this.images = images;
        this.items = items;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return images.length;
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
        ListItemBinding binding ;
        if(convertView == null){
            binding = ListItemBinding.inflate(inflater,parent,false);
            convertView = binding.getRoot() ;
            convertView.setTag(binding);
        }else{
            binding = (ListItemBinding) convertView.getTag();
        }

        binding.itemImage.setImageResource(images[position]);
        binding.itemText.setText(items[position]);


        return convertView;
    }
}
