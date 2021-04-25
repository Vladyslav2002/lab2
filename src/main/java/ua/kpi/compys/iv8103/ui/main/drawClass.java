package ua.kpi.compys.iv8103.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.util.ArrayList;
import java.util.Objects;
import static android.view.View.INVISIBLE;
import ua.kpi.compys.iv8103.R;

public class drawClass extends Fragment {
    private LineChart lineChart;
    private PieChart pieChart;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Switch Switch = view.findViewById(R.id.graphSwitch);
        Switch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked){
                pieChart.setVisibility(View.VISIBLE);
                lineChart.setVisibility(INVISIBLE);
            } else {
                lineChart.setVisibility(View.VISIBLE);
                pieChart.setVisibility(INVISIBLE);
            }
        });
        initialiseLineFragment();
        initialisePieFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_second, container, false);
    }
    private void initialisePieFragment() {
        ArrayList<PieEntry> dataSet;
        dataSet = new ArrayList<>();
        dataSet.add(new PieEntry(15, 15));
        dataSet.add(new PieEntry(25, 25));
        dataSet.add(new PieEntry(45, 45));
        dataSet.add(new PieEntry(10, 10));
        dataSet.add(new PieEntry(5, 5));


        ArrayList<Integer> colors;
        colors = new ArrayList<>();
        colors.add(Color.parseColor("#fbff00"));
        colors.add(Color.parseColor("#6e320d"));
        colors.add(Color.parseColor("#706a66"));
        colors.add(Color.parseColor("#ff0000"));
        colors.add(Color.parseColor("#8400c2"));
        PieDataSet pieDataSet = new PieDataSet(dataSet,"");
        pieDataSet.setColors(colors);
        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(false);
        pieChart = Objects.requireNonNull(getView()).findViewById(R.id.diagramPlot);
        pieChart.setData(pieData);
        pieChart.invalidate();
        pieChart.getDescription().setEnabled(false);
        pieChart.animate();
        pieChart.setVisibility(INVISIBLE);
    }

    private void initialiseLineFragment() {
        lineChart = Objects.requireNonNull(getView()).findViewById(R.id.graphPlot);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setDrawAxisLine(true);
        yAxisLeft.setDrawGridLines(false);
        yAxisLeft.setDrawZeroLine(true);
        ArrayList<Entry> dataSet = new ArrayList<>();
        for (float x =-3; x < 3; x += 0.01f) {
            dataSet.add(new Entry(x, (float) x*x*x));
        }
        ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
        iLineDataSets.add(new LineDataSet(dataSet, " "));
        LineData lineData = new LineData(iLineDataSets);
        Description desc = new Description();
        desc.setText(" ");
        lineChart.setDescription(desc);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }
}
