package com.example.nav;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Assignment extends Fragment {

    private Button newass;

    RecyclerView recyclerView;
    ArrayList<model_ass> dataholder= new ArrayList<model_ass>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.assignment_layout,container,false);

        newass=view.findViewById(R.id.add_ass);
        newass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Addassignment.class));
            }
        });

        recyclerView=(RecyclerView) view.findViewById(R.id.recview_ass);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        dataholder.clear();

        DBHelper db= new DBHelper(getActivity());
        Cursor cursor=db.readAllasss();

        while(cursor.moveToNext()){
            model_ass obj= new model_ass(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4));
            dataholder.add(obj);
        }

        assadapter adapter=new assadapter(dataholder,db);

        recyclerView.setAdapter(adapter);












        newass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Addassignment.class);
                startActivity(intent);
            }
        });









        return view;
    }
}
