package com.example.huongthutran.btvn_buoi5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnExercise1,btnExercise2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnExercise1=(Button)findViewById(R.id.btnExercise1);
        btnExercise2=(Button)findViewById(R.id.btnExercise2);
        btnExercise1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Exercise1Activity.class);
                startActivity(intent);
            }
        });
        btnExercise2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Exercise2Activity.class);
                startActivity(intent);
            }
        });
    }
}
