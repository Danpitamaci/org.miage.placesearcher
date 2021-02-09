package org.miage.placesearcher.ui.ui.dashboard;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.ValueFormatter;

import org.miage.placesearcher.R;
import org.miage.placesearcher.ui.ui.SimpleFragment;

import java.util.ArrayList;

public class DashboardFragment extends SimpleFragment  {


    @NonNull
    public static Fragment newInstance() {
        return new DashboardFragment();
    }

    private BarChart chart;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Commerce");
        xAxisLabel.add("Gare Sud");
        xAxisLabel.add("Atlantis");
        xAxisLabel.add("Orvault GV");
        xAxisLabel.add("Vincent Gâche");
        xAxisLabel.add("Univ nantes");

        chart = v.findViewById(R.id.chart1);

        chart.getDescription().setEnabled(false);


        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Light.ttf");

        chart.setData(generateMostSearchedAddressData());


        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        chart.getAxisRight().setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(true);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xAxisLabel.get((int) value);

            }
        });
        return v;
    }
}