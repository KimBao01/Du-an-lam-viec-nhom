package com.example.project_english_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Activity_Highscore_Correct extends AppCompatActivity {

    ArrayList<member> MemberList;
    Mem_Adapter_Correct MemberAdapter;
    ArrayList<member> MemberListTemp;
    ListView Lv_Rank_Member;
    Button BtnBack;
    private static final String FILE_NAME = "rankUpdate.txt";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_highscore);
        AnhXa();
        MemberAdapter = new Mem_Adapter_Correct(MemberListTemp, this, R.layout.layout_one_item_rank);
        Lv_Rank_Member.setAdapter(MemberAdapter);
        Lv_Rank_Member.setDivider(null);
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    void AnhXa() {
        MemberList = new ArrayList<>();
        MemberListTemp = new ArrayList<>();
        Lv_Rank_Member = (ListView) findViewById(R.id.Lv_HighScore);
        MemberList();
        Collections.sort(MemberList);
        for (int i = 0; i < MemberList.size(); i++) {
            if (i == 5) {
                break;
            }
            MemberListTemp.add(MemberList.get(i));
        }
        Log.e("" + MemberList, "");
        Log.e("" + MemberList, "");
        BtnBack = (Button) findViewById(R.id.BtnBack);
    }

    void Display(int i) {
//        load();
    }

    void MemberList() {
//        ArrayList<member> getMemberList = new ArrayList<>();
        FileInputStream fis = null;
        try {
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
//            StringBuilder sb = new StringBuilder();
            String text = null;
            while (true) {
                if ((text = br.readLine()) != null) {
                    if (text.trim() == " ") continue;
                    String[] list = text.split(" ");
                    MemberList.add(new member(list[0], Integer.parseInt(list[1]), Integer.parseInt(list[2]), Integer.parseInt(list[3])));
//                    sb.append(text).append("\n");
                    Log.e("size", "" + MemberList.size());
                } else {
                    break;
                }

            }

//            Log.e("test",""+sb.toString());
//            for(member mb:MemberList)
//            {
//                Log.e("in ",""+mb.toString());
//
//            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
