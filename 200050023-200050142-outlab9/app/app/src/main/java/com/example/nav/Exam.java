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

public class Exam extends Fragment {

    private Button newexam;
    RecyclerView recyclerView;
    ArrayList<model_exam> dataholder= new ArrayList<model_exam>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.exam_layout,container,false);

        newexam=view.findViewById(R.id.add_exam);
        newexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Addexam.class));
            }
        });

        recyclerView=view.findViewById(R.id.recview_exam);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        dataholder.clear();

        DBHelper db=new DBHelper(getActivity());
        Cursor cursor=db.readAllexams();

        while(cursor.moveToNext()){
            model_exam obj=new model_exam(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4));
            dataholder.add(obj);
        }

        Examadapter adapter=new Examadapter(dataholder,db);

        recyclerView.setAdapter(adapter);


        newexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Addexam.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
