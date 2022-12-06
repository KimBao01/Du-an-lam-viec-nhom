package com.example.project_english_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_correct_home extends AppCompatActivity {
    private Button btnCorrecStart, btnCorrecHigSco, btnCorrecInstuc, btnCorrecBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_correct_home);

        btnCorrecStart = (Button) findViewById(R.id.btn_CorrectStart);
        btnCorrecStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_correct_play();
            }
        });

        btnCorrecHigSco = (Button) findViewById(R.id.btn_CorrectHighSco);
        btnCorrecHigSco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_result();
            }
        });

        btnCorrecInstuc = (Button) findViewById(R.id.btn_CorrectInstruc);
        btnCorrecInstuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_correct_intruction();
            }
        });

        btnCorrecBack  = (Button) findViewById(R.id.btn_CorrectBack_HomeMenu);
        btnCorrecBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gobackActivity_menu();
            }
        });
    }

    public void openActivity_correct_play() {
        Intent intent = new Intent(this, Activity_correct_play.class);
        startActivity(intent);
    }

    public void openActivity_result() {
        Intent intent = new Intent(this, Activity_Highscore.class);
        startActivity(intent);
    }

    public void openActivity_correct_intruction() {
        Intent intent = new Intent(this, Activity_correct_instruction.class);
        startActivity(intent);
    }

    public void gobackActivity_menu() {
        Intent intent = new Intent(this, Activity_menu.class);
        startActivity(intent);
    }

}