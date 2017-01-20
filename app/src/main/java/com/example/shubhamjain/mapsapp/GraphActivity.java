package com.example.shubhamjain.mapsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        GraphView gv=(GraphView)findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series=new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(1,5),
                new DataPoint(2,4),
                new DataPoint(3,6),
                new DataPoint(4,6)

        });

        gv.addSeries(series);
    }
}
