package com.prgr.quizards.quizards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity4 extends AppCompatActivity {
    private AppCompatButton btn;
    private TextView time;
    private TextView total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(v -> home());
        time = findViewById(R.id.timeolut);
        total = findViewById(R.id.NoQA);
    }
    private void home(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}