package org.t_robop.kido.temperature2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.View;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lineChart = (LineChart) findViewById(R.id.line_chart);


    }

    //ボタン
    public void createLineChartData_week(View v) {
        createLineChart();
        //グラフの生成
        lineChart.setData(createLineChartDataWeek());
    }

    public void createLineChartData_month(View v) {
        createLineChart();
        //グラフの生成
        lineChart.setData(createLineChartDataMonth());
    }


    public void createLineChartData_year(View v) {
        createLineChart();
        //グラフの生成
        lineChart.setData(createLineChartDataYear());
    }


    //グラフの設定
    private void createLineChart() {

        lineChart.setDescription("さいたま市　平均気温");     //グラフの説明
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.setDrawGridBackground(true);  //グリッド線
        lineChart.setEnabled(true);


//        lineChart.setPinchZoom(true);
        lineChart.setDoubleTapToZoomEnabled(false); //ダブルタップズームの無効化

//        lineChart.setHighlightPerDragEnabled(true);



        lineChart.getLegend().setEnabled(true); //判例有効化
        lineChart.fitScreen();//拡大を初期化


        //X軸周り
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setSpaceBetweenLabels(0);

        //Y軸周り
        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        yAxis.setShowOnlyMinMax(true);
        yAxis.setStartAtZero(false);
        yAxis.setDrawGridLines(true);
        yAxis.setEnabled(true);


        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        rightAxis.setShowOnlyMinMax(true);
        rightAxis.setStartAtZero(false);
        rightAxis.setDrawGridLines(true);
        rightAxis.setEnabled(false);


        lineChart.invalidate();


    }

    //週間グラフを作成
    private LineData createLineChartDataWeek() {
        ArrayList<LineDataSet> LineDataSets = new ArrayList<>();


        Date now = new Date();
        // androidから日を取得
        int dayLimitLength = getLastDay(now) - getDay(now);

        int day = getDay(now);

        //週間の制限
        /*データは5つ以上でなければならない
            もし条件を満たさないのであれば、
            else文で処理する
         */


        ArrayList<String> xValues = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dayLimitLength--;
            if(dayLimitLength == -2) {
                day=-i+1;
            }
            xValues.add((i + day) + "日");
        }


        // 週間気温
        ArrayList<Entry> valuesA = new ArrayList<>();
        float valuesA_temp[] = {23.1F, 23.3F, 23.4F, 23.6F, 23.7F, 23.9F, 24F, 24.1F, 24.3F, 24.4F, 24.5F, 24.7F, 24.8F, 24.9F, 25F, 25.1F, 25.2F, 25.4F, 25.5F, 25.6F, 25.7F, 25.8F, 26F, 26.1F, 26.2F, 26.3F, 26.5F, 26.6F, 26.7F, 26.7F, 26.8F};

            for (int i = 0; i < 5; i++) {
            valuesA.add(new Entry(valuesA_temp[i], i));
        }


        LineDataSet valuesADataSet = new LineDataSet(valuesA, "平均気温");  //グラフ全体のラベル
        valuesADataSet.setColor(ColorTemplate.COLORFUL_COLORS[3]);  //グラフの色
        LineDataSets.add(valuesADataSet);   //グラフをセット


        LineData lineData = new LineData(xValues, LineDataSets); //グラフを返す
        return lineData;
    }


    // 月間グラフを作成
    private LineData createLineChartDataMonth() {
        ArrayList<LineDataSet> LineDataSets = new ArrayList<>();


        Date now = new Date();
        // androidから日付を取得
        int lastDay = getLastDay(now);


        ArrayList<String> xValues = new ArrayList<>();

        for (int i = 0; i < lastDay; i++) {
            xValues.add(String.valueOf(i + 1));
        }


        // 月間気温
        ArrayList<Entry> valuesA = new ArrayList<>();
        float valuesA_temp[] = {23.1F, 23.3F, 23.4F, 23.6F, 23.7F, 23.9F, 24F, 24.1F, 24.3F, 24.4F, 24.5F, 24.7F, 24.8F, 24.9F, 25F, 25.1F, 25.2F, 25.4F, 25.5F, 25.6F, 25.7F, 25.8F, 26F, 26.1F, 26.2F, 26.3F, 26.5F, 26.6F, 26.7F, 26.7F, 26.8F};
        for (int i = 0; i < lastDay; i++) {
            valuesA.add(new Entry(valuesA_temp[i], i));
        }

//        lineChart.highlightValues(LineDataSet[2]);
        LineDataSet valuesADataSet = new LineDataSet(valuesA, "平均気温");  //グラフ全体のラベル
        valuesADataSet.setColor(ColorTemplate.COLORFUL_COLORS[3]);  //グラフの色
        LineDataSets.add(valuesADataSet);   //グラフをセット


        LineData lineData = new LineData(xValues, LineDataSets); //グラフを返す
        return lineData;
    }

    //ゲッター
    //月末を取得
    static public int getLastDay(Date now) {

        Calendar c = Calendar.getInstance();
        c.setTime(now);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        c.set(year, month, 1);
        int lastDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return lastDay;
    }

    //日を取得
    static public int getDay(Date now) {
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return day;
    }




    // 年間グラフを作成
    private LineData createLineChartDataYear() {
        ArrayList<LineDataSet> LineDataSets = new ArrayList<>();
        lineChart.fitScreen();
        // X軸のラベル
        ArrayList<String> xValues = new ArrayList<>();
        String monthNum[] = {"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
        for (int i = 0; i < 12; i++) {
            xValues.add(monthNum[i]);
        }
        // 年間気温
        ArrayList<Entry> valuesA = new ArrayList<>();
        float valuesA_temp[] = {4.5F, 4.8F, 9.4F, 14.1F, 20.8F, 22.3F, 26.6F, 26.6F, 22.5F, 17.8F, 13.2F, 8.1F};
        for (int i = 0; i < 12; i++) {
            valuesA.add(new Entry(valuesA_temp[i], i));
        }

        LineDataSet valuesADataSet = new LineDataSet(valuesA, "平均気温");  //グラフ全体のラベル
        valuesADataSet.setColor(ColorTemplate.COLORFUL_COLORS[3]);  //グラフの色
        LineDataSets.add(valuesADataSet);   //グラフをセット


        LineData lineData = new LineData(xValues, LineDataSets); //グラフを返す
        return lineData;
    }
}


