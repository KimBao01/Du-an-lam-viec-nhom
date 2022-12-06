package com.example.project_english_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Activity_result extends AppCompatActivity {
    private PieChart pieChart;
    private Button btnResult, btnNextOneItem;
    int Correct_Answer,Wrong_Answer;
    float Per_Correct_Ans,Per_Wrong_Ans;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_result);
        AnhXa();
        Intent callerIntent=getIntent();
        Bundle packageFromCaller= callerIntent.getBundleExtra("correct_play");
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_correct_home();
            }
        });

        btnNextOneItem = (Button) findViewById(R.id.btn_nextOneItem);
        btnNextOneItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_one_item_rank();
            }
        });
        Correct_Answer = packageFromCaller.getInt("CorrectAnswer");
//        Log.e("result",""+Correct_Answer);
        Wrong_Answer = 10-Correct_Answer;
//        Log.e("result",""+Wrong_Answer);
        Per_Correct_Ans = (float)Correct_Answer/10;
//        Log.e("result",""+Per_Correct_Ans);
        Per_Wrong_Ans = (float)Wrong_Answer/10;
//        Log.e("result",""+Per_Wrong_Ans);


        setupPieChart();
        loadPieChartData();
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(14);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Spending by Category");
        pieChart.setCenterTextSize(20);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(true);
        l.setTextSize(18f);
    }
    private void loadPieChartData () {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(Per_Correct_Ans, "% câu đúng"));
        entries.add(new PieEntry(Per_Wrong_Ans, "% câu sai"));
//            entries.add(new PieEntry(95.10f, "Entertainment"));
//            entries.add(new PieEntry(0.25f, "Electricity and Gas"));
//            entries.add(new PieEntry(0.3f, "Housing"));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color: ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
        dataSet.getFormLineWidth();
        dataSet.setColors(colors);
        dataSet.setFormSize(18f);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }

    public void openActivity_correct_home() {
        Intent intent = new Intent(this, Activity_correct_home.class);
        startActivity(intent);
    }

    public void openActivity_one_item_rank() {
        Intent intent = new Intent(this, Activity_Highscore.class);
        startActivity(intent);
        finish();
    }
    void AnhXa()
    {
        btnResult = (Button) findViewById(R.id.btn_result);
        pieChart = findViewById(R.id.activity_main_piechart);

    }
}