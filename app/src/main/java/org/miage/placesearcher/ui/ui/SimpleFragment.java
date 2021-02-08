package org.miage.placesearcher.ui.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FileUtils;

import java.util.ArrayList;

public abstract class SimpleFragment extends Fragment {

    private Typeface tf;
    protected Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public SimpleFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * generate the histogram for the most searched address in Nantes in 2020
     * @return barData
     */
    protected BarData generateMostSearchedAddressData() {

        ArrayList<IBarDataSet> sets = new ArrayList<>();

        ArrayList<BarEntry> entries = new ArrayList<>();

        int [] nbResearch = {1000,700,900,400,2000};
        String[] address = new String[] { "Commerce", "Gare Sud", "Atlantis", "Orvault GV", "Vincent Gâche"};
        for(int i = 0; i < address.length; i++) {
            int count = 5;
            for (int j = 0; j < count; j++) {
                entries.add(new BarEntry(j, nbResearch[j]));
            }

            BarDataSet ds = new BarDataSet(entries, address[i]);
            ds.setColors(ColorTemplate.COLORFUL_COLORS);
            sets.add(ds);

        }
        BarData d = new BarData(sets);
        d.setValueTypeface(tf);
        return d;
    }


    /**
     * Generate a pie data for the five countries that have more addresses
     * @return PieData
     */
    protected PieData generateCountriesData() {

        int count = 5;
        String [] countries = {"Nantes","Rezé", "Orvault","Carquefou","Coueron"};
        int [] nbAddresses = {500,200,250,145,90};

        ArrayList<PieEntry> entries1 = new ArrayList<>();

        for(int i = 0; i < count; i++) {
            entries1.add(new PieEntry(nbAddresses[i], countries[i]));
        }

        PieDataSet ds1 = new PieDataSet(entries1, "Most address by country");
        ds1.setColors(ColorTemplate.COLORFUL_COLORS);
        ds1.setSliceSpace(2f);
        ds1.setValueTextColor(Color.BLACK);
        ds1.setValueTextSize(12f);

        PieData d = new PieData(ds1);
        d.setValueTypeface(tf);

        return d;
    }

    /**
     * generate a Line data for Place searcher app usage evolution between 2019 and 2020
     * @return LineData
     */
    protected LineData generateUsageEvolutionData() {

        ArrayList<ILineDataSet> sets = new ArrayList<>();
        LineDataSet ds1 = new LineDataSet(FileUtils.loadEntriesFromAssets(context.getAssets(), "2019.txt"), "2019");
        LineDataSet ds2 = new LineDataSet(FileUtils.loadEntriesFromAssets(context.getAssets(), "2020.txt"), "2020");

        ds1.setLineWidth(2f);
        ds2.setLineWidth(2f);

        ds1.setDrawCircles(false);
        ds2.setDrawCircles(false);

        ds1.setColor(ColorTemplate.COLORFUL_COLORS[0]);
        ds2.setColor(ColorTemplate.COLORFUL_COLORS[1]);

        // load DataSets from files in assets folder
        sets.add(ds1);
        sets.add(ds2);

        LineData d = new LineData(sets);
        d.setValueTypeface(tf);
        return d;
    }

}
