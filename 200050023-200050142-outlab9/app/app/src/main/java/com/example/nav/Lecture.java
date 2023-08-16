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

public class Lecture extends Fragment {

    private Button newlecture;
    RecyclerView recyclerView;

    ArrayList<model_lecture> dataholder=new ArrayList<model_lecture>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.lecture_layout,container,false);

        newlecture=view.findViewById(R.id.add_lecture);
        newlecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Addlecture.class));
            }
        });
        recyclerView=view.findViewById(R.id.recview_lectures);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        dataholder.clear();

        DBHelper db=new DBHelper(getActivity());
        Cursor cursor=db.readAllectures();

        while (cursor.moveToNext()){
            model_lecture obj=new model_lecture(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            dataholder.add(obj);
        }

        lectureadapter adapter=new lectureadapter(dataholder,db);
        recyclerView.setAdapter(adapter);




        newlecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Addlecture.class);
                startActivity(intent);
            }
        });






        return view;
    }
}
