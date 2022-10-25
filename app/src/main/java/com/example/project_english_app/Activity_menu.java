package com.example.project_english_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class Activity_menu extends AppCompatActivity {
    Button btnEngQuiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menu);

        AnhXa();
        btnEngQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_menu.this,Activity_quiz_home.class);
                startActivity(intent);
            }
        });
    }
    public void AnhXa() {
        btnEngQuiz = (Button) findViewById(R.id.btn_Quiz);
    }


}