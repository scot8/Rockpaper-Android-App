package com.example.rockpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button btnStart;

    //ItemDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btn_start);

        EditText nameText = findViewById(R.id.nameText);


        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String playerName = nameText.getText().toString();
                nextPage(playerName);
                //Item item = new Item();
                //item.setData("");
                //db.itemDao().insert()
            }
        });
    }

    private void nextPage(String pName) {
        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra("name", pName);
        startActivity(intent);

    }
}