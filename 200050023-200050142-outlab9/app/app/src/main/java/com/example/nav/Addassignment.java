package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addassignment extends AppCompatActivity {

    private EditText name,discription, duedate;
    private Button save;
    DBHelper db=new DBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addassignment_layout);

        name=findViewById(R.id.assname);
        discription=findViewById(R.id.assdiscription);
        duedate=findViewById(R.id.assduedate);
        save=findViewById(R.id.asssave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt=name.getText().toString();
                String discriptiontxt=discription.getText().toString();
                int duedateint;
                try {
                    duedateint=Integer.parseInt(duedate.getText().toString());
                }
                catch (Exception e){
                    duedateint=0;
                }

                Boolean check=db.addass(nametxt,discriptiontxt,duedateint);
                if(check==true){
                    Toast.makeText(Addassignment.this, "Saved the assignment", Toast.LENGTH_SHORT).show();
                    Intent intent;
                    intent = new Intent(Addassignment.this,MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(Addassignment.this, "Not saved the assignment", Toast.LENGTH_SHORT).show();

            }
        });
    }
}