package com.example.nav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Addplan extends AppCompatActivity {

    private EditText name, plan;
    private Button save;
    DBHelper db=new DBHelper(this);;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addplan_layout);


        name=findViewById(R.id.Enter_planname);
        plan=findViewById(R.id.Enter_plan);
        save=findViewById(R.id.btn_saveplan);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt=name.getText().toString();
                String plantxt=plan.getText().toString();


                Boolean checkinsertdata=db.addplan(nametxt,plantxt);
                if(checkinsertdata==true){
                    Toast.makeText(Addplan.this, "Saved the plan", Toast.LENGTH_SHORT).show();
                    Intent intent;
                    intent = new Intent(Addplan.this,MainActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(Addplan.this, "Not saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}