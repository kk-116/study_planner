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

public class StudyPlan extends Fragment {


    private Button newPlan;

    RecyclerView recyclerView;
    ArrayList<model_plan> dataholder = new ArrayList<model_plan>();
    //DBHelper db=new DBHelper(getContext());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.studyplan_layout,container,false);

        newPlan=view.findViewById(R.id.add_studyplan);
        newPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Addplan.class));
            }
        });
        recyclerView=(RecyclerView)view.findViewById(R.id.recview_plans);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        dataholder.clear();

        DBHelper db= new DBHelper(getActivity());
        Cursor cursor=db.readAllplans();

        while (cursor.moveToNext()){
            model_plan obj = new model_plan(cursor.getInt(0), cursor.getString(1),cursor.getString(2),cursor.getString(3));
            dataholder.add(obj);
        }
        planadapter adapter=new planadapter(dataholder,db);
        recyclerView.setAdapter(adapter);

        newPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),Addplan.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
