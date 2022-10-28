package com.example.project_english_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_quiz_instruction extends AppCompatActivity {
    private Button btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quiz_instruction);

        btn = (Button) findViewById(R.id.btn_QuizBack_Instruc);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gobackActivity_quiz_home();
            }
        });
    }

    public void gobackActivity_quiz_home() {
        Intent intent = new Intent(this, Activity_quiz_home.class);
        startActivity(intent);
    }
}