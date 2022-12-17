package com.example.project_english_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

class QuestionNare {
    public String ID;
    public String Q;
    public String AnswerA, AnswerB, AnswerC, AnswerD, Answer;

}

public class Activity_quiz_play extends Activity {
    //    Activity_quiz_chooseQuestion Au = new Activity_quiz_chooseQuestion() ;
    int AA = 0;
    private Button btn;
    TextView Cauhoi, Ketqua;
    RadioGroup RG;
    Button BT;
    Button Skip;
    ArrayList<member_quiz> MemberList;
    private static final long COUNTDOWN_IN_MILLIS = 30000;
    private TextView textViewCountDown;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;
    MediaPlayer Media_Correct_Answer;
    MediaPlayer Media_Win;
    MediaPlayer Media_Lose;
    MediaPlayer Media_False_Answer;


    int score = 0;
    RadioButton A, B, C, D;
    int pos = 0;//vị trí câu hỏi trong danh sách
    //lưu số câu trả lời đúng
    ArrayList<QuestionNare> L = new ArrayList(); //chứa câu hỏi
    int HighScore = 0;
    int High1 = 0, High2 = 0, High3 = 0, High4 = 0, High5 = 0;
    int total_Time = 0;
    private static final String FILE_NAME = "rankUpdateQuiz.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("MyPackage1");
        AA = packageFromCaller.getInt("SL1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quiz_play);
        Media_False_Answer =  MediaPlayer.create(Activity_quiz_play.this,R.raw.doulingo_wrong_answer);
        Media_Correct_Answer = MediaPlayer.create(Activity_quiz_play.this,R.raw.duolingo_true_anwser);
        Media_Lose = MediaPlayer.create(Activity_quiz_play.this,R.raw.doulingo_lose);
        Media_Win = MediaPlayer.create(Activity_quiz_play.this,R.raw.doulingo_win);
        MemberList = new ArrayList<>();
        Cauhoi = (TextView) findViewById(R.id.txtCauhoi);
        Ketqua = (TextView) findViewById(R.id.txt_ketqua);
        RG = (RadioGroup) findViewById(R.id.radioGroup);
        BT = (Button) findViewById(R.id.btn_Answer);
        Skip = (Button) findViewById(R.id.btn_Skip);
        textViewCountDown = findViewById(R.id.textTimer);
        A = (RadioButton) findViewById(R.id.rb1);
        B = (RadioButton) findViewById(R.id.rb2);
        C = (RadioButton) findViewById(R.id.rb3);
        D = (RadioButton) findViewById(R.id.rb4);
        textColorDefaultCd = textViewCountDown.getTextColors();
        timeLeftInMillis = COUNTDOWN_IN_MILLIS;
        startCountDown();
        editscore();
        LoadHighScore();
        ReadData();
        Display(pos);

        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean a = false;
                int idCheck = RG.getCheckedRadioButtonId();
                countDownTimer.cancel();
//                    onDestroy();
//                    timeLeftInMillis = COUNTDOWN_IN_MILLIS;
//                    startCountDown();
                switch (idCheck) {
                    case R.id.rb1:
                        if (L.get(pos).Answer.compareTo("A") == 0) {
                            score = score + 1;
                            Media_Correct_Answer.start();
                        }
                        else
                        {
                            Media_False_Answer.start();
                        }
                        a = true;
                        break;
                    case R.id.rb2:
                        if (L.get(pos).Answer.compareTo("B") == 0) {
                            score = score + 1;
                            Media_Correct_Answer.start();
                        }
                        else
                        {
                            Media_False_Answer.start();
                        }
                        a = true;
                        break;
                    case R.id.rb3:
                        if (L.get(pos).Answer.compareTo("C") == 0){
                            score = score + 1;
                            Media_Correct_Answer.start();
                        }
                        else
                        {
                            Media_False_Answer.start();
                        }
                        a = true;
                        break;
                    case R.id.rb4:
                        if (L.get(pos).Answer.compareTo("D") == 0) {
                            score = score + 1;
                            Media_Correct_Answer.start();
                        }
                        else
                        {
                            Media_False_Answer.start();
                        }
                        a = true;
                        break;
                }
                if (a == true) {
                    pos++;
                    onDestroy();
                    timeLeftInMillis = COUNTDOWN_IN_MILLIS;
                    startCountDown();

                    if (pos >= L.size()) {
                        if(Media_Correct_Answer.isPlaying())
                        {
                            Media_Correct_Answer.stop();
                        }
                        if(Media_False_Answer.isPlaying())
                        {
                            Media_False_Answer.stop();
                        }
                        Media_Win.start();
                        Intent intent = new Intent(Activity_quiz_play.this, Activity_Result_Quiz.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("KQ", score);
                        bundle.putInt("Socau", pos);
                        MemberList();
                        save();
                        intent.putExtra("MyPackage", bundle);
                        startActivity(intent);
//                        if (kq > HighScore) {
//                            HighScore = kq;
//                            SaveHighScore();
//                        }
                        finish();
//                    timeLeftInMillis = COUNTDOWN_IN_MILLIS;

                    } else {

//                    startCountDown();
                        Display(pos); //Hiển thị câu hỏi kế tiếp
                    }
                }
            }
        });

        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                pos++;
                onDestroy();
                timeLeftInMillis = COUNTDOWN_IN_MILLIS;
                startCountDown();

                if (pos >= L.size()) {
                    if(Media_Correct_Answer.isPlaying())
                    {
                        Media_Correct_Answer.stop();
                    }
                    if(Media_False_Answer.isPlaying())
                    {
                        Media_False_Answer.stop();
                    }
                    Media_Win.start();
                    Intent intent = new Intent(Activity_quiz_play.this, Activity_Result_Quiz.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("KQ", score);
                    bundle.putInt("Socau", pos);
                    intent.putExtra("MyPackage", bundle);
                    MemberList();
                    save();
                    startActivity(intent);
                    if (score > High1) {
                        int change = High1;
                        High1 = score;
                        score = change;

                    }
                    if (score > High2) {
                        int change = High2;
                        High2 = score;
                        score = change;
                    }
                    if (score > High3) {
                        int change = High3;
                        High3 = score;
                        score = change;
                    }
                    if (score > High4) {
                        int change = High4;
                        High4 = score;
                        score = change;
                    }
                    if (score > High5) {
                        int change = High5;
                        High5 = score;
                        score = change;
                    }
                    SaveHighScore();
                    finish();
//                    timeLeftInMillis = COUNTDOWN_IN_MILLIS;

                } else {

//                    startCountDown();
                    Display(pos); //Hiển thị câu hỏi kế tiếp
                }
            }
        });

        btn = (Button) findViewById(R.id.btn_Quiz_goBack);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gobackAcivity_quiz_home();
            }
        });

    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timeLeftInMillis = millisUntilFinished;
                updateCountDownText(timeLeftInMillis);
            }

            @Override
            public void onFinish() {
                if(Media_Correct_Answer.isPlaying())
                {
                    Media_Correct_Answer.stop();
                }
                Media_False_Answer.start();
                timeLeftInMillis = 30;
//                Toast.makeText(Activity_quiz_play.this, "Het gio", Toast.LENGTH_SHORT).show();
                pos++;
                if (pos >= L.size()) {
                    if(Media_Correct_Answer.isPlaying())
                    {
                        Media_Correct_Answer.stop();
                    }
                    if(Media_False_Answer.isPlaying())
                    {
                        Media_False_Answer.stop();
                    }
                    Media_Win.start();
                    Intent intent = new Intent(Activity_quiz_play.this, Activity_Result_Quiz.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("KQ", score);
                    bundle.putInt("Socau", pos);
                    MemberList();
                    save();
                    intent.putExtra("MyPackage", bundle);
                    startActivity(intent);
                } else {
                    Display(pos);
                    timeLeftInMillis = COUNTDOWN_IN_MILLIS;
                    startCountDown();
                }

            }
        }.start();
    }

    private void updateCountDownText(long timeLeftInMillis) {
        int count = 0;
        count++;
        total_Time += count;
        Log.e("Count", "" + total_Time);
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountDown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewCountDown.setTextColor(Color.RED);
        } else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }

    public void gobackAcivity_quiz_home() {
        countDownTimer.cancel();
        Intent intent = new Intent(this, Activity_quiz_home.class);
        startActivity(intent);
    }

    void Display(int i) {

        Cauhoi.setText(L.get(i).Q);
        A.setText(L.get(i).AnswerA);
        B.setText(L.get(i).AnswerB);
        C.setText(L.get(i).AnswerC);
        D.setText(L.get(i).AnswerD);
        Ketqua.setText("Câu đúng:" + score);
        RG.clearCheck(); //xóa checked
    }

    void ReadData() {
        try {
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
            InputStream in = getAssets().open("data.xml");
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();//lấy tag Root
            NodeList list = root.getChildNodes();// lấy toàn bộ node con của Root
//            double b = Au.A/1;
            for (int i = 0; i < AA * 2; i++)
//                list.getLength()


//                list.getLength()
            {// duyệt từ node đầu tiên cho tới
                Random a;
                Node node = list.item(i);// mỗi lần duyệt thì lấy ra 1 node
                if (node instanceof Element) {
                    Element Item = (Element) node;// lấy được tag Item
                    NodeList listChild = Item.getElementsByTagName("ID");
                    String ID = listChild.item(0).getTextContent();//lấy nội dung của tag ID
                    listChild = Item.getElementsByTagName("Question");// lấy tag Question
                    String Question = listChild.item(0).getTextContent();//lấy nội dung Question
                    listChild = Item.getElementsByTagName("AnswerA");
                    String AnswerA = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerB");
                    String AnswerB = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerC");
                    String AnswerC = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerD");
                    String AnswerD = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Answer");
                    String Answer = listChild.item(0).getTextContent();
                    QuestionNare Q1 = new QuestionNare();
                    Q1.ID = ID;
                    Q1.Q = Question;
                    Q1.AnswerA = AnswerA;
                    Q1.AnswerB = AnswerB;
                    Q1.AnswerC = AnswerC;
                    Q1.AnswerD = AnswerD;
                    Q1.Answer = Answer;
                    L.add(Q1);
                }
                ;
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(L);
    }

    void LoadHighScore() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData",
                Context.MODE_PRIVATE);
        High1 = sharedPreferences.getInt("H", 0);
        High2 = sharedPreferences.getInt("C", 0);
        High3 = sharedPreferences.getInt("B", 0);
        High4 = sharedPreferences.getInt("D", 0);
        High5 = sharedPreferences.getInt("Z", 0);
    }

    void SaveHighScore() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("H", High1);
        editor.putInt("C", High2);
        editor.putInt("B", High3);
        editor.putInt("D", High4);
        editor.putInt("Z", High5);
//        editor.putInt("H",0);
//        editor.putInt("C",0);
//        editor.putInt("B",0);
//        editor.putInt("D",0);
//        editor.putInt("Z",0);


        editor.apply();
    }

    void editscore() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("H", 0);
        editor.putInt("C", 0);
        editor.putInt("B", 0);
        editor.putInt("D", 0);
        editor.putInt("Z", 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    void save() {
        boolean check = false;
        SharedPreferences myPrefs;
        String email = "";
        myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        email = myPrefs.getString("Email1", "");

//        MemberList = new ArrayList<>();
//        if (MemberList.size() >= 0) {
//            Log.e("not null", "asdasd");
//            MemberList = MemberList();
//
//            Log.e("" + MemberList.size(), "size");
//        }
        for (int i = 0; i < MemberList.size(); i++) {
            Log.e("for", "");
            Log.e("membername", "" + MemberList.get(i).getName());
            Log.e("email", "" + email);
            if (MemberList.get(i).getName().trim().compareTo(email.trim()) == 0) {
                Log.e("name", "");
                MemberList.remove(i);
                MemberList.add(i, new member_quiz(email, score, total_Time));
                check = true;
                break;
            }
        }
        if (MemberList.size() == 0) {//Neu File Rong thi Add 1 Member
            MemberList.add(new member_quiz(email, score, total_Time));
            check = true;
        }
//        if (MemberList.size() > 10)//Loc file cho gon gang
//        {
//            for (int i = MemberList().size() - 1; i > 5; i--)//5 : 0 -> 4
//            {
//                MemberList.remove(i);
//            }
//        }
        if (!check) {
            MemberList.add(new member_quiz(email, score, total_Time));
        }
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            PrintWriter pw = new PrintWriter(fos);
            for (member_quiz mb : MemberList) {
                pw.println(mb.toStringQuiz());
            }
//            edtID.getText().clear();
            Toast.makeText(Activity_quiz_play.this, "Saved to " + getFilesDir() + "/" + FILE_NAME, Toast.LENGTH_LONG).show();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    void MemberList() {
        Log.e("asdjklasdjlasdjk", "asdasdasdasdasdsad");
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
                    MemberList.add(new member_quiz(list[0], Integer.parseInt(list[1]), Integer.parseInt(list[2])));
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


