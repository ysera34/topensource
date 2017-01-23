package com.opensource.mpandroidchart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BarChart mBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBarChart = (BarChart) findViewById(R.id.bar_chart);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createChart();
            }
        });
    }

    private void createChart() {
        ArrayList<IBarDataSet> dataSets = createDataSet();
        ArrayList<String> xAxisList = createXAxis();

        Description description = new Description();
        description.setText("매출관리");
        mBarChart.setDescription(description);

        BarData data = new BarData(dataSets);
        mBarChart.setData(data);
        mBarChart.animateY(1500);
        mBarChart.invalidate();
    }

    private ArrayList<IBarDataSet> createDataSet() {
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 80.0f));
        barEntries.add(new BarEntry(2, 20.0f));
        barEntries.add(new BarEntry(3, 70.0f));
        barEntries.add(new BarEntry(4, 50.0f));
        barEntries.add(new BarEntry(5, 10.0f));

        BarDataSet dataSet = new BarDataSet(barEntries, "매출");
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);

        dataSets.add(dataSet);

        return dataSets;
    }

    private ArrayList<String> createXAxis() {
        ArrayList<String> axisList = new ArrayList<>();
        axisList.add("1월 매출");
        axisList.add("2월 매출");
        axisList.add("3월 매출");
        axisList.add("4월 매출");
        axisList.add("5월 매출");
        return axisList;
    }
}
