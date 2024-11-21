package com.example.rockpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {
    Button btnSimpleMode;
    Button btnExtended;

    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        btnSimpleMode = findViewById(R.id.btn_simple);
        btnExtended = findViewById(R.id.btn_multiple);
        userName = findViewById(R.id.userName);

        Intent intent = getIntent();
        String username = intent.getStringExtra("name");
        userName.setText(username);

        btnExtended.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Log.i("blck", username);
                nextPage(username, "multiple");
            }
        });

        btnSimpleMode.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Log.i("blck", username);
                nextPage(username, "single");
            }
        });
    }

    private void nextPage(String pName, String mode) {
        Intent intent = new Intent(this, Activity3.class);
        intent.putExtra("name", pName);
        intent.putExtra("mode", mode);
        startActivity(intent);
    }
}