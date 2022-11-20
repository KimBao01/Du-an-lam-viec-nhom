package com.example.project_english_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

class English_CorrectWord {
    public String hint, question, answer;
}

public class Activity_correct_play extends AppCompatActivity {
    private Button btnBack, btnAnswer, btnSkip;
    GridView GvChuCai;
    TextView txtHint, txtAnswer, txtTimer, txtCounterStar,txtCorrectAnswer;
    ArrayList<ChuCai_CorrectWord> ChuCai;
    ProgressBar Pgb;
    ChuCai_Adapter ChuCaiAdapter;
    ArrayList<English_CorrectWord> L = new ArrayList();
    ArrayList<Integer> Arr_MaintainAnswer;
    String FullChuCai = "";
    ArrayList<String>question;
    int Level = 0, pos = 0, current, StartTimeInt = 30, Check_Maintain_Count_Star = 0, posIndex = 0, High_Star = 0,CorrectAnswer=0;
    Random random = new Random();
    boolean Frag_Easy[] = new boolean[1000], Frag_Medium[] = new boolean[1000], Frag_Hard[] = new boolean[1000], CheckAnswer = false;
    CountDownTimer Count_Down_Timer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_correct_play);
        AnhXa();
//        while(true)
//        {
        Log.e("true ", "Asd");
        ReadData(CheckAnswer);
        CountDown();
        GvChuCai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setVisibility(View.INVISIBLE);
                FullChuCai += ChuCai.get(i).getChuCai();
                txtAnswer.setText(FullChuCai);
            }
        });
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Arr_MaintainAnswer.add(-1);
                if (Arr_MaintainAnswer.size() > 2) {
                    Check_Maintain_Answer();
                }
                CheckAnswer = false;
                Count_Down_Timer.cancel();
                StartTimeInt = 30;
                pos++;
                ReadData(CheckAnswer);
                CountDown();
                FullChuCai = "";
                txtAnswer.setText(FullChuCai);
                current = Pgb.getProgress();
                Pgb.setProgress(current + 10);
            }
        });
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtAnswer.getText().toString().trim().equals(L.get(pos).answer.trim())) {
                    Arr_MaintainAnswer.add(1);
                    if (Arr_MaintainAnswer.size() > 2) {
                        Check_Maintain_Answer();
                    }
                    CheckAnswer = true;
                    Count_Down_Timer.cancel();
                    StartTimeInt = 30;
                    current = Pgb.getProgress();
                    Pgb.setProgress(current + 10);
                    Toast.makeText(Activity_correct_play.this, "Right answer ", Toast.LENGTH_LONG).show();
                    FullChuCai = "";
                    txtAnswer.setText("");
                    pos++;
                    ReadData(CheckAnswer);
                    CountDown();
                    Log.e("level " + Level, "asd");
                    CorrectAnswer++;
                    txtCorrectAnswer.setText("Correct answers : "+String.valueOf(CorrectAnswer));
//                    pos++;
//                    Display(pos);
                } else {
                    Log.e(txtAnswer.getText().toString().trim() + " " + L.get(pos).answer, "asd");
                    Toast.makeText(Activity_correct_play.this, "Wrong " + FullChuCai, Toast.LENGTH_LONG).show();
                    FullChuCai = "";
                    txtAnswer.setText(FullChuCai);
                    GvChuCai.setAdapter(ChuCaiAdapter);

                }
            }
        });
//            if(L.size()<10)
//            {
//                break;
//            }
//        }


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Count_Down_Timer.cancel();
                NextPage();
            }
        });
    }


    public void AnhXa() {
        txtCorrectAnswer = (TextView)findViewById(R.id.CorrectAnswer);
        Arr_MaintainAnswer = new ArrayList<Integer>();
        txtCounterStar = (TextView) findViewById(R.id.CounterStar);
        txtTimer = (TextView) findViewById(R.id.textTimer);
        Arrays.fill(Frag_Easy, false);
        Arrays.fill(Frag_Medium, false);
        Arrays.fill(Frag_Hard, false);
        GvChuCai = (GridView) findViewById(R.id.Gv_Question);
        btnBack = (Button) findViewById(R.id.btn_Correct_goBack);
        txtAnswer = (TextView) findViewById(R.id.TxtAnswerCorrectWord);
        txtHint = (TextView) findViewById(R.id.HintCorrectWord);
        btnAnswer = (Button) findViewById(R.id.btn_Answer);
        btnSkip = (Button) findViewById(R.id.BtnSkip);
        Pgb = (ProgressBar) findViewById(R.id.progressBar2);

    }

    void Display(int i) {
        question = new ArrayList<>();
        ChuCai = new ArrayList<>();
        String test="";
        String[] Split_Ques = L.get(i).question.split(" ");
        for(int a=0;a<Split_Ques.length;a++)
        {
            Log.e(Split_Ques[a]+"","asd");
        }
        for(int k=0;k<Split_Ques.length;k++)
        {
            question.add(Split_Ques[k]);
        }
        Log.e("origin "+question,"1");
        Collections.shuffle(question);
        Log.e("shuffer "+question,"1");
        while(true)
        {
            for (int j = 0; j < question.size(); j++) {
                test+=question.get(j);
            }
            if(!test.equals(L.get(i).answer.trim()))
            {
                break;
            }
            else test = "";
        }
        Log.e("test "+test,"1");

        for (int j = 0; j < question.size(); j++) {
            ChuCai.add(new ChuCai_CorrectWord(question.get(j)));
        }
        ChuCaiAdapter = new ChuCai_Adapter(ChuCai, this, R.layout.dong_correct_word);
        GvChuCai.setAdapter(ChuCaiAdapter);
        txtHint.setText(L.get(i).hint);
//        B.setText(L.get(i).AnswerB);
//        C.setText(L.get(i).AnswerC);
//        D.setText(L.get(i).AnswerD);
//        Ketqua.setText("Câu đúng:" + kq);
//        RG.clearCheck(); //xóa checked
    }

    int CheckLevel(boolean CheckAnswer) {

        if (CheckAnswer)//Dúng
        {
            Level += 1;//TB
            if (Level > 2) {
                Level = 2;
            }
        } else//Sai
        {
            Level = Level - 1;
            if (Level < 0) {
                Level = 0;//De~
            }
        }
        Log.e("CheckAnswer " + CheckAnswer + "Level " + Level, "asd");
        return Level;
    }


    void ReadData(boolean CheckAnswer) {
        int level = CheckLevel(CheckAnswer);
        English_CorrectWord Q1 = new English_CorrectWord();
        Log.e("lv read " + level, "asd");
//        if(L.size()<10)
//        {
//            Log.e("success","asdsad");
//        }
//        else
//        {
        if (L.size() < 10) {
//            Log.e("Size " + L.size(), "asd");
            if (level == 0) {
                try {
                    //Tạo đối tượng DocumentBuilder (builder )
                    DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = DBF.newDocumentBuilder();
                    //Tạo FileInputStream từ tập tin XML nguồn
                    InputStream in = getAssets().open("data_english_easy.xml");
                    //Dùng phương thức parse của đối tượng builder để tạo Document
                    Document doc = builder.parse(in);
                    Element root = doc.getDocumentElement();//lấy tag Root
                    NodeList list = root.getChildNodes();// lấy toàn bộ node con của Root
//                    for(int i=0;i<list.getLength();i++)
//                    {
//                        if(i%2!=0)
//                        {
//                            if(Frag_Easy[i]==true)
//                            {
//                                Log.e("full Easy ","asd");
//                            }
//                        }
//                    }
                    Log.e("list length Easy " + list.getLength(), "asd");
//                Frag_Easy[Cauhoi_Easy] = true;
//                Log.e("Frag Easy "+Cauhoi_Easy,"asd");
                    int Cauhoi_Easy = 0;
                    while (true) {
                        Cauhoi_Easy = random.nextInt(list.getLength() - 1);

                        if (Cauhoi_Easy % 2 != 0) {
                            if (Frag_Easy[Cauhoi_Easy] == false) {
                                break;
                            }
                        }
                    }
                    for (int i = 0; i < list.getLength(); i++) {
                        Node node = list.item(Cauhoi_Easy);// mỗi lần duyệt thì lấy ra 1 node
                        if (node instanceof Element) {
                            if (Frag_Easy[Cauhoi_Easy] == false) {
//                                Log.e("i1 Cauhoi_Easy" + Cauhoi_Easy, "Asd");
                                Element Item = (Element) node;// lấy được tag Item
                                // lấy tag ID bên trong của tag Item
                                NodeList listChild = Item.getElementsByTagName("Question");
                                String question = listChild.item(0).getTextContent();
                                listChild = Item.getElementsByTagName("Answer");
                                String answer = listChild.item(0).getTextContent();
                                listChild = Item.getElementsByTagName("Hint");
                                String hint = listChild.item(0).getTextContent();
                                //lưu vào list

                                Q1.question = question;
                                Q1.hint = hint;
                                Q1.answer = answer;
//                                Log.e("i2 Cauhoi_Easy " + Cauhoi_Easy, "Asd");
                                Frag_Easy[Cauhoi_Easy] = true;
                                break;
                            }
                        }
//                    else
//                    {
//                        Log.e("i3 Cauhoi_Easy "+i,"Asd");
//                    }
//                }
//                }

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
            } else if (level == 1) {
                try {
                    //Tạo đối tượng DocumentBuilder (builder )
                    DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = DBF.newDocumentBuilder();
                    //Tạo FileInputStream từ tập tin XML nguồn
                    InputStream in = getAssets().open("data_english_medium.xml");
                    //Dùng phương thức parse của đối tượng builder để tạo Document
                    Document doc = builder.parse(in);
                    Element root = doc.getDocumentElement();//lấy tag Root
                    NodeList list = root.getChildNodes();// lấy toàn bộ node con của Root
//                    for(int i=0;i<list.getLength();i++)
//                    {
//                        if(i%2!=0)
//                        {
//                            if(Frag_Medium[i]==true)
//                            {
//                                Log.e("full Medium ","asd");
//                            }
//                        }
//                    }
//                    Log.e("list length Medium " + list.getLength(), "asd");
//                Frag_Easy[Cauhoi_Easy] = true;
//                Log.e("Frag Easy "+Cauhoi_Easy,"asd");
                    int Cauhoi_Medium = 0;
                    while (true) {
                        Cauhoi_Medium = random.nextInt(list.getLength() - 1);
                        if (Cauhoi_Medium % 2 != 0) {
                            if (Frag_Medium[Cauhoi_Medium] == false) {
                                break;
                            }
                        }
                    }
                    for (int i = 0; i < list.getLength(); i++) {
                        Node node = list.item(Cauhoi_Medium);// mỗi lần duyệt thì lấy ra 1 node
                        if (node instanceof Element) {
//                        if(Frag_Easy[Cauhoi_Easy]==false)
                            {
//                                Log.e("i1 Cauhoi_Medium" + Cauhoi_Medium, "Asd");
                                Element Item = (Element) node;// lấy được tag Item
                                // lấy tag ID bên trong của tag Item
                                NodeList listChild = Item.getElementsByTagName("Question");
                                String question = listChild.item(0).getTextContent();
                                listChild = Item.getElementsByTagName("Answer");
                                String answer = listChild.item(0).getTextContent();
                                listChild = Item.getElementsByTagName("Hint");
                                String hint = listChild.item(0).getTextContent();
                                //lưu vào list

                                Q1.question = question;
                                Q1.hint = hint;
                                Q1.answer = answer;
//                                Log.e("i2 Cauhoi_Medium " + Cauhoi_Medium, "Asd");
                                Frag_Medium[Cauhoi_Medium] = true;
                                break;
                            }
                        }
//                    else
//                    {
//                        Log.e("i3 Cauhoi_Easy "+i,"Asd");
//                    }
//                }
//                }

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
            } else if (level == 2) {
                try {
                    //Tạo đối tượng DocumentBuilder (builder )
                    DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = DBF.newDocumentBuilder();
                    //Tạo FileInputStream từ tập tin XML nguồn
                    InputStream in = getAssets().open("data_english_hard.xml");
                    //Dùng phương thức parse của đối tượng builder để tạo Document
                    Document doc = builder.parse(in);
                    Element root = doc.getDocumentElement();//lấy tag Root
                    NodeList list = root.getChildNodes();// lấy toàn bộ node con của Root
//                    for(int i=0;i<list.getLength();i++)
//                    {
//                        if(i%2!=0)
//                        {
//                            if(Frag_Easy[i]==true)
//                            {
//                                Log.e("full Easy ","asd");
//                            }
//                        }
//                    }
                    Log.e("list length Easy " + list.getLength(), "asd");
//                Frag_Easy[Cauhoi_Easy] = true;
//                Log.e("Frag Easy "+Cauhoi_Easy,"asd");
                    int Cauhoi_Hard = 0;
                    while (true) {
                        Cauhoi_Hard = random.nextInt(list.getLength() - 1);

                        if (Cauhoi_Hard % 2 != 0) {
                            if (Frag_Easy[Cauhoi_Hard] == false) {
                                break;
                            }
                        }
                    }
                    for (int i = 0; i < list.getLength(); i++) {
                        Node node = list.item(Cauhoi_Hard);// mỗi lần duyệt thì lấy ra 1 node
                        if (node instanceof Element) {
                            if (Frag_Easy[Cauhoi_Hard] == false) {
                                Log.e("i1 Cauhoi_Hard " + Cauhoi_Hard, "Asd");
                                Element Item = (Element) node;// lấy được tag Item
                                // lấy tag ID bên trong của tag Item
                                NodeList listChild = Item.getElementsByTagName("Question");
                                String question = listChild.item(0).getTextContent();
                                listChild = Item.getElementsByTagName("Answer");
                                String answer = listChild.item(0).getTextContent();
                                listChild = Item.getElementsByTagName("Hint");
                                String hint = listChild.item(0).getTextContent();
                                //lưu vào list

                                Q1.question = question;
                                Q1.hint = hint;
                                Q1.answer = answer;
                                Log.e("i2 Cauhoi_Hard  " + Cauhoi_Hard, "Asd");
                                Frag_Easy[Cauhoi_Hard] = true;
                                break;
                            }
                        } else {
                            Log.e("i3 Cauhoi_Hard " + i, "Asd");
                        }
//                }
//                }

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
            }
            L.add(Q1);
            Log.e("pos " + pos, "asd");
            if (pos == 0) {
                Display(pos);
            } else {
                Display(pos);
            }
            ;//Hiển thị câu hỏi kế tiếp
//            if (pos >= L.size()) {
////            Intent intent = new Intent(MainActivity.this,activity_ketqua.class);
////            Bundle bundle = new Bundle();
////            bundle.putInt("KQ",kq);
////            bundle.putInt("Socau",pos);
////            intent.putExtra("MyPackage",bundle);
////            startActivity(intent);
//                pos =0; //Cho vị trí pos về câu hỏi đầu tiên
////            kq =0; //cho số câu hỏi đúng bằng 0, để làm lại
//                Display(pos); // Hiển thị lại nội dung
//            }
//            else {
//
//
//            }
        } else {
            Intent intent = new Intent(Activity_correct_play.this, Second.class);
            startActivity(intent);
            finish();
        }
    }

    void CountDown() {
        Count_Down_Timer = new CountDownTimer(31000, 1000) {
            @Override
            public void onTick(long l) {
                String StartTimeString = String.valueOf(StartTimeInt);
                if (StartTimeInt >= 10) {
                    txtTimer.setText("00:" + StartTimeString);
                } else {
                    txtTimer.setText("00:0" + StartTimeString);
                }
                StartTimeInt--;


            }

            @Override
            public void onFinish() {
                StartTimeInt = 30;
                Arr_MaintainAnswer.add(-1);
                if (Arr_MaintainAnswer.size() > 2) {
                    Check_Maintain_Answer();
                }
                pos++;
                ReadData(CheckAnswer);
                CheckAnswer = false;
                CountDown();
                FullChuCai = "";
                txtAnswer.setText(FullChuCai);
                current = Pgb.getProgress();
                Pgb.setProgress(current + 10);
            }
        }.start();

    }

    void Check_Maintain_Answer() {
        Log.e("pos :" + posIndex, "ArrCheck :" + Arr_MaintainAnswer.size());
        String Str_HighStar;
        while (posIndex < Arr_MaintainAnswer.size()) {
            Log.e("i " + posIndex, "1");
            if (Arr_MaintainAnswer.get(posIndex) != Arr_MaintainAnswer.get(posIndex + 1)) {
                Arr_MaintainAnswer = new ArrayList<>();
                posIndex=0;
                Check_Maintain_Count_Star = 0;
                break;
            }
            else {
                Check_Maintain_Count_Star++;
                Log.e("asdjklasdjlkasjdk ","1");
                if (Check_Maintain_Count_Star == 2) {
                    if (Arr_MaintainAnswer.get(posIndex) == 1) {
                        High_Star++;
                        Str_HighStar = String.valueOf(High_Star);
                        txtCounterStar.setText(Str_HighStar);
                        Check_Maintain_Count_Star = 0;
                        Arr_MaintainAnswer = new ArrayList<>();
                        posIndex = 0;
                        break;
                    } else if (Arr_MaintainAnswer.get(posIndex) == -1) {
                        if (txtCounterStar.getText().toString().equals("0")) {
                            Intent intent = new Intent(Activity_correct_play.this, Second.class);
                            startActivity(intent);
//                            finish();
                            break;
                        }
                        High_Star--;
                        Str_HighStar = String.valueOf(High_Star);
                        txtCounterStar.setText(Str_HighStar);
                        Check_Maintain_Count_Star = 0;
                        Arr_MaintainAnswer = new ArrayList<>();
                        posIndex = 0;
                        break;
                    }
                }
                posIndex = posIndex + 1;
            }
        }
    }
    void NextPage()
    {
        Intent intent = new Intent(this, Activity_correct_home.class);
        startActivity(intent);
//        overridePendingTransition(R.anim.nextpage,R.anim.backpage);
    }



}