package com.najmul.femaleharasmentmonitoring;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

    EditText e1,e2;
    Button saveBtn, deleteBtn;
    TextView dataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        e1 = (EditText) findViewById(R.id.name);
        e2 = (EditText) findViewById(R.id.bir);
        saveBtn= (Button) findViewById(R.id.button);
        dataView = (TextView) findViewById(R.id.tvSave);
        deleteBtn = (Button) findViewById(R.id.delete);


       saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=e1.getText().toString();
                String phone=e2.getText().toString();
                final MyDBFunctions mf = new MyDBFunctions(getApplicationContext());
                DataTemp dt = new DataTemp(name,phone);
                mf.addingDataToTable(dt);

                Toast.makeText(getApplicationContext(),"Data added successfuly!", Toast.LENGTH_LONG).show();
                e1.setText("");
                e2.setText("");
               DataView();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MyDBFunctions mf = new MyDBFunctions(getApplicationContext());
              mf.Delete_Raw();
              DataView();
            }
        });





    }
    public void DataView(){
        MyDBFunctions mf = new MyDBFunctions(getApplicationContext());
        String[] data = mf.my_data() ;
        String s ="";
        for (int i = 0; i <data.length; i++){
            s = s + data[i]+"\n\n";
        }
dataView.setText(s);

    }


}
