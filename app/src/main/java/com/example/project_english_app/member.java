package com.example.project_english_app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class member implements Comparable<member> {
    //    int id;
    String name;
    int SoCau;
    int Correct_Star;
    int Ear_Finish_Time;

    public member(String name, int SoCau, int Correct_Star, int Ear_Finish_Time) {
//        this.id = id;
        this.name = name;
        this.SoCau = SoCau;
        this.Correct_Star = Correct_Star;
        this.Ear_Finish_Time = Ear_Finish_Time;
    }
    public member(String name, int SoCau, int Ear_Finish_Time) {
//        this.id = id;
        this.name = name;
        this.SoCau = SoCau;
        this.Ear_Finish_Time = Ear_Finish_Time;
    }


    public member() {
        this.name = name;
        this.SoCau = SoCau;
        this.Correct_Star = Correct_Star;
        this.Ear_Finish_Time = Ear_Finish_Time;
    }

    public int getEar_Finish_Time() {
        return Ear_Finish_Time;
    }

    public void setEar_Finish_Time(int ear_Finish_Time) {
        Ear_Finish_Time = ear_Finish_Time;
    }
    //
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSoCau() {
        return SoCau;
    }

    public void setSoCau(int soCau) {
        SoCau = soCau;
    }

    public int getCorrect_Star() {
        return Correct_Star;
    }

    public void setCorrect_Star(int correct_Star) {
        Correct_Star = correct_Star;
    }

    @Override
    public int compareTo(member member) {
        if (SoCau < member.SoCau) {
            return 1;
        } else if (SoCau == member.SoCau) {
            if (Ear_Finish_Time > member.Ear_Finish_Time) return 1;
            else if (Ear_Finish_Time == member.Ear_Finish_Time) return 0;
            else return -1;
        } else return -1;
    }
    public String toStringCorrect()
    {

        return getName()+" "+getSoCau()+" "+getCorrect_Star()+" "+getEar_Finish_Time();
    }
    public String toStringQuiz()
    {

        return getName()+" "+getSoCau()+" "+getEar_Finish_Time();
    }
}
