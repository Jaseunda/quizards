package com.prgr.quizards.quizards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.jaseunda.scodetools.SCodeDesign;
import com.jaseunda.scodetools.SCodeUtil;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatButton btn = findViewById(R.id.button);
        btn.setOnClickListener(v -> gotohome());
    }
    private void gotohome(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
}