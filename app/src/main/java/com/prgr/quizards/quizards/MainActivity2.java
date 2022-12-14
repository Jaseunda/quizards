package com.prgr.quizards.quizards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.jaseunda.scodetools.SCodeDesign;
import com.jaseunda.scodetools.SCodeUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {
    /*
    Logic
         */
    private TextInputEditText title;
    private TextInputEditText desc;
    private TextInputEditText amt;
    private SharedPreferences jshared;
    private SharedPreferences jshared2;
    private HashMap<String, Object> qtest = new HashMap<>();
    private ArrayList<HashMap<String, Object>> amap = new ArrayList<>();
    public MainActivity2() {
    }

    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main2);
    AppCompatButton btn = findViewById(R.id.button2);
    ConstraintLayout home = findViewById(R.id.home);
    btn.setOnClickListener(v -> logic());
    home.setEnabled(true);
    SCodeDesign.ExportDesign(home, true);
    initializelogic();
    /*
    Calls for TDA Screen
    */
        ConstraintLayout newquiz = findViewById(R.id.newquiz);
        AppCompatButton btn3 = findViewById(R.id.button);
        AppCompatButton btn2 = findViewById(R.id.button3);
        btn2.setOnClickListener(v -> back());
        btn3.setOnClickListener(v -> gotom3());
        newquiz.setEnabled(false);
        SCodeDesign.ExportDesign(newquiz, false);
   }
    private void logic(){
        ConstraintLayout newquiz = findViewById(R.id.newquiz);
        ConstraintLayout home = findViewById(R.id.home);
        if(newquiz.isEnabled()){
            SCodeDesign.ExportDesign(newquiz, false);
            SCodeDesign.ExportDesign(home, true);
        }else{
            SCodeDesign.ExportDesign(newquiz, true);
            SCodeDesign.ExportDesign(home, false);
        }
    }
    private void back(){
        ConstraintLayout home = findViewById(R.id.home);
        ConstraintLayout newquiz = findViewById(R.id.newquiz);
        if(home.isEnabled()){
            SCodeDesign.ExportDesign(newquiz, true);
            SCodeDesign.ExportDesign(home, false);
        }else{
            SCodeDesign.ExportDesign(newquiz, false);
            SCodeDesign.ExportDesign(home, true);
        }
    }
    private void initializelogic(){
        title = findViewById(R.id.quizname);
        desc = findViewById(R.id.desc);
        amt = findViewById(R.id.amount);
        jshared = getSharedPreferences("Q-TDA_DATA-SET-1", Activity.MODE_PRIVATE);
        jshared2 = getSharedPreferences("Q-TDA_MAPD-SET-1", Activity.MODE_PRIVATE);
    }
    private void gotom3(){
        //Values
         jshared.edit().putString("quizname", title.getText().toString()).apply();
         jshared.edit().putString("desc", desc.getText().toString()).apply();
         jshared.edit().putString("amount", amt.getText().toString()).apply();

         //Get Data
        qtest = new HashMap<>();
        qtest.put("title", jshared.getString("quizname",""));
        qtest.put("Description", jshared.getString("desc",""));
        qtest.put("AQA", jshared.getString("amount",""));
        SCodeUtil.MaptoListMap(qtest, amap);
        jshared2.edit().putString("Q-TDA", new Gson().toJson(amap)).commit();
        SCodeUtil.CustomToast(getApplicationContext(), "Quiz Amount: "+amap.get(0).get("AQA").toString(),0xffffffff, 15, 0xff212121,10, 3);
        //Move to new page
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }
}