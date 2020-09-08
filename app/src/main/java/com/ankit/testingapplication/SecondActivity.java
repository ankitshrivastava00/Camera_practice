package com.ankit.testingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    EditText edtText;
    Button buttonId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        edtText=(EditText)findViewById(R.id.edtText);
        buttonId=(Button)findViewById(R.id.submitId);
        buttonId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

String data =edtText.getText().toString().trim();
                Intent i= new Intent();
                i.putExtra("data",data);
                setResult(1,i);
                finish();
            }
        });
    }
}
