package com.example.nav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addexam extends AppCompatActivity {

    private EditText name,discription,quizdate;
    private Button save;
    DBHelper db=new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addexam_layout);


        name=findViewById(R.id.examname);
        discription=findViewById(R.id.examdiscription);
        quizdate=findViewById(R.id.examdate);
        save=findViewById(R.id.examsave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt=name.getText().toString();
                String discriptiontxt=discription.getText().toString();
                int quizdateint;
                try {
                    quizdateint=Integer.parseInt(quizdate.getText().toString());
                }catch (Exception e){
                    quizdateint=0;
                }

                Boolean check=db.addexam(nametxt,discriptiontxt,quizdateint);
                if(check==true){
                    Toast.makeText(Addexam.this, "Saved the Quiz", Toast.LENGTH_SHORT).show();
                    Intent intent;
                    intent=new Intent(Addexam.this,MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Addexam.this, "Not saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}