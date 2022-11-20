package com.example.project_english_app;

import android.widget.TextView;

public class ChuCai_CorrectWord {
    String ChuCai;

    public String getChuCai() {
        return ChuCai;
    }

    public void setText(TextView text) {
        this.ChuCai = ChuCai;
    }

    public ChuCai_CorrectWord(String ChuCai) {
        this.ChuCai = ChuCai;
    }
}
