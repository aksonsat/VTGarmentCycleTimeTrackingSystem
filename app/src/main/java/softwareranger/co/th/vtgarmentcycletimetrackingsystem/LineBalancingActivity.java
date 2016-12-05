package softwareranger.co.th.vtgarmentcycletimetrackingsystem;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class LineBalancingActivity extends AppCompatActivity {

    private Spinner spinnerLocation, spinnerCustomer, spinnerLine;
    private Button buttonLineBalancing, buttonGSDAnalysis, buttonOKNGCard, buttonAdd, buttonFinish;

    private ArrayList<String> valueSet = new ArrayList<String>();

    private BarChart barChart;

    // Timer
    Button butnstart, butnreset;
    TextView time;
    long starttime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedtime = 0L;
    int t = 1;
    int secs = 0;
    int mins = 0;
    int milliseconds = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_balancing);

        spinnerLocation = (Spinner) findViewById(R.id.spinner_location);
        spinnerCustomer = (Spinner) findViewById(R.id.spinner_customer);
        spinnerLine = (Spinner) findViewById(R.id.spinner_line);

        buttonLineBalancing = (Button) findViewById(R.id.button_line_balancing);
        buttonGSDAnalysis = (Button) findViewById(R.id.button_gsd_analysis);
        buttonAdd = (Button) findViewById(R.id.button_add);
        buttonFinish = (Button) findViewById(R.id.button_finish);

        barChart = (BarChart) findViewById(R.id.chart);

        buttonGSDAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LineBalancingActivity.this, GSDAnalysisActivity.class));
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                functionAdd();
            }
        });

        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(LineBalancingActivity.this);
                dialog.setContentView(R.layout.custom_dialog_finish);

                butnstart = (Button) dialog.findViewById(R.id.button_start_timer);
                butnreset = (Button) dialog.findViewById(R.id.button_reset_timer);
                time = (TextView) dialog.findViewById(R.id.timer);

                butnstart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (t == 1) {
                            butnstart.setText("Stop");
                            starttime = SystemClock.uptimeMillis();
                            handler.postDelayed(updateTimer, 0);
                            t = 0;
                        } else {
                            butnstart.setText("Start");
                            time.setTextColor(Color.BLACK);
                            timeSwapBuff += timeInMilliseconds;
                            handler.removeCallbacks(updateTimer);
                            t = 1;
                        }
                    }
                });

                butnreset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        starttime = 0L;
                        timeInMilliseconds = 0L;
                        timeSwapBuff = 0L;
                        updatedtime = 0L;
                        t = 1;
                        secs = 0;
                        mins = 0;
                        milliseconds = 0;
                        butnstart.setText("Start");
                        handler.removeCallbacks(updateTimer);
                        time.setText("00:00:00");
                    }
                });

                dialog.show();
            }
        });
        // Call value
        addValueSet();

        // Set value adapter location
        ArrayAdapter<String> adapterLocation = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,valueSet);
        spinnerLocation.setAdapter(adapterLocation);

        // Set value adapter customer
        ArrayAdapter<String> adapterCustomer = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,valueSet);
        spinnerCustomer.setAdapter(adapterCustomer);

        // Set value adapter customer
        ArrayAdapter<String> adapterLine = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,valueSet);
        spinnerLine.setAdapter(adapterLine);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(9f, 5));

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");

        BarData data = new BarData(labels,dataset);

        barChart.setData(data);
        barChart.animateY(5000);
    }

    // Add value test
    private void addValueSet() {
        valueSet.add("Value 1");
        valueSet.add("Value 2");
        valueSet.add("Value 3");
        valueSet.add("Value 4");
        valueSet.add("Value 5");
        valueSet.add("Value 6");
        valueSet.add("Value 7");
        valueSet.add("Value 8");
    }

    // Button Add
    private void functionAdd() {
        final Dialog dialog = new Dialog(LineBalancingActivity.this);
        dialog.setContentView(R.layout.custom_dialog_add);

        final EditText editTextEmployeeQty = (EditText) dialog.findViewById(R.id.field_employee_qty);
        final EditText editTextAfterSeq = (EditText) dialog.findViewById(R.id.field_after_seq);
        Button buttonOk = (Button) dialog.findViewById(R.id.button_ok);
        Button buttonCancel = (Button) dialog.findViewById(R.id.button_cancel);
        //dialog.setTitle("");

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public Runnable updateTimer = new Runnable() {

        public void run() {

            timeInMilliseconds = SystemClock.uptimeMillis() - starttime;

            updatedtime = timeSwapBuff + timeInMilliseconds;

            secs = (int) (updatedtime / 1000);
            mins = secs / 60;
            secs = secs % 60;
            milliseconds = (int) (updatedtime % 1000);
            time.setText("" + mins + ":" + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            time.setTextColor(Color.RED);
            handler.postDelayed(this, 0);
        }

    };
}
