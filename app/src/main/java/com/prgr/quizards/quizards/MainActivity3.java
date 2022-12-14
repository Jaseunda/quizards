package com.prgr.quizards.quizards;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jaseunda.scodetools.SCodeDesign;
import com.jaseunda.scodetools.SCodeUtil;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {
    //Calls
    public ConstraintLayout iqa;
    public ConstraintLayout qa;
    private TextView amount;
    private TextView quest;
    private TextView count;
    private TextView icount;
    private TextInputEditText input;
    private TextInputEditText answer;
    private AppCompatButton btn1;
    private AppCompatButton prev;
    private AppCompatButton btn;
    private AppCompatButton btn2;
    private Intent intent = new Intent();
    private HashMap<String, Object> que = new HashMap<>();
    private HashMap<String, Object> map = new HashMap<>();
    private ArrayList<HashMap<String, Object>> qmap = new ArrayList<>();
    private int val;
    private SharedPreferences jshared;
    private SharedPreferences jsharedq;
    private int dat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        iqa(); //Input Q/A
        qa(); //Question Answering
        tools(); // Tools to Define stuff
    }
    private void iqa(){
        //Input Q/A
        icount = findViewById(R.id.text3);
        prev = findViewById(R.id.button1);
        btn1 = findViewById(R.id.nxt);
        btn = findViewById(R.id.button2);
        iqa = findViewById(R.id.iqa);
        prev.setOnClickListener(v -> prev());
        btn1.setOnClickListener(v -> met());
        btn.setOnClickListener(v -> logic());
        iqa.setEnabled(true);
        SCodeDesign.ExportDesign(iqa, true);
    }
    private void qa(){
        //Question Answering
        btn2 = findViewById(R.id.button3);
        qa = findViewById(R.id.qa);
        btn2.setOnClickListener(v -> back());
        qa.setEnabled(false);
        SCodeDesign.ExportDesign(qa, false);
        jsharedq = getSharedPreferences("Q-QA_DATA-SET-2", Activity.MODE_PRIVATE);
    }
    private void tools(){
        // Tools to define stuff
        amount = findViewById(R.id.amount);
        quest = findViewById(R.id.question);
        input = findViewById(R.id.inop);
        val = 0;

        // 3rd Page
        jshared = getSharedPreferences("Q-TDA_MAPD-SET-1", Activity.MODE_PRIVATE);
        answer = findViewById(R.id.ans);
        count = findViewById(R.id.text3);
       // Map<String,Object> result = new ObjectMapper().readValue(JSON_SOURCE, HashMap.class);

        //Number indicator
        String ca = jshared.getString("amount", "");
        String up = Integer.toString(val);
        String note = up + " / " + ca;
        icount.setText(note);
    }


    //Design

    private void back(){
        ConstraintLayout qa = findViewById(R.id.qa);
        ConstraintLayout iqa = findViewById(R.id.iqa);
        if(qa.isEnabled()){
            SCodeDesign.ExportDesign(iqa, true);
            SCodeDesign.ExportDesign(qa, false);
        }else{
            SCodeDesign.ExportDesign(iqa, false);
            SCodeDesign.ExportDesign(qa, true);
        }
    }
    private void prev(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
    private void logic(){
        String ca = jshared.getString("amount", "");
        dat = Integer.parseInt(ca);
        boolean b = val == dat;
        if(b){
            //Data Collector Logic
            jshared.edit().putString("QUES", new Gson().toJson(qmap)).commit();
            SCodeUtil.CustomToast(getApplicationContext(), "hello", 0xffffffff, 15,0xff212121,10,3);
        }else{
            que = new HashMap<>();
            que.put("question", quest.getText().toString());
            que.put("answer", input.getText().toString());
            SCodeUtil.MaptoListMap(que, qmap);
            que.clear();
            val++;
            //Value Indicator
            String up = Integer.toString(val);
            String note = up + " / " + ca;
            icount.setText(note);
        }
        //        ConstraintLayout iqa = findViewById(R.id.iqa);
//        ConstraintLayout qa = findViewById(R.id.qa);
//        if(iqa.isEnabled()){
//            SCodeDesign.ExportDesign(iqa, false);
//            SCodeDesign.ExportDesign(qa, true);
//        }else{
//            SCodeDesign.ExportDesign(iqa, true);
//            SCodeDesign.ExportDesign(qa, false);
//        }
//    }
    }
    //Page Mover
    public void met(){

//        Intent intent = new Intent(this, MainActivity4.class);
//        startActivity(intent);
    }
}