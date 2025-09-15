package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.myapplication.Adaptar.CustomExpandableListAdapter;
import com.example.myapplication.databinding.ActivityExpandableListViewBinding;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;




public class ExpandableListViewActivity extends AppCompatActivity {


    private ActivityExpandableListViewBinding binding;

    List<String> listGroup;
    HashMap<String, List<String>> listItem;
    CustomExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityExpandableListViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        setTitle("Expandable list");
        // Enable the back/up button in the toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        listGroup = new ArrayList<>();
        listItem = new HashMap<>();
//        adapter = new CustomExpandableListAdapter(this, listGroup, listItem);
//        expandableListView.setAdapter(adapter);

         // Setup adapter with binding
        adapter = new CustomExpandableListAdapter(this, listGroup, listItem);
        binding.expandableListView.setAdapter(adapter);

         initData();

        binding.expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });

        binding.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
               String item = listItem.get(listGroup.get(groupPosition)).get(childPosition);
                Toast.makeText(ExpandableListViewActivity.this, item.toString(), Toast.LENGTH_SHORT).show();
            // Do something when child clicked
            return true;
            }
        });


    }


     private void initData() {
        listGroup.add("Programming Languages");
        listGroup.add("Frameworks");

        List<String> languages = new ArrayList<>();
        languages.add("Java");
        languages.add("Dart");
        languages.add("Kotlin");
        languages.add("JavaScript");

        List<String> frameworks = new ArrayList<>();
        frameworks.add("Flutter");
        frameworks.add("Spring Boot");
        frameworks.add("React");
        frameworks.add("Angular");

        listItem.put(listGroup.get(0), languages);
        listItem.put(listGroup.get(1), frameworks);

        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back when button is clicked
        return true;
    }


}