package com.ltthuong.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {
    Button btnBack;
    Button btnReport;
    EditText txtTuNgay;
    EditText txtDenNgay;
    TextView tvKetQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //Cancel
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                finish();
            }
        });

        //Report
        txtTuNgay = (EditText) findViewById(R.id.txtTuNgay);
        txtDenNgay = (EditText) findViewById(R.id.txtDenNgay);
        btnReport = (Button) findViewById(R.id.btnReport);
        tvKetQua = (TextView) findViewById(R.id.tvKetQua);
        MyDatabaseHelper db = new MyDatabaseHelper(this);

        btnReport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if(!txtTuNgay.getText().toString().equals("") && !txtDenNgay.getText().toString().equals(""))
                {
                    String tuNgay = txtTuNgay.getText().toString();
                    String denNgay = txtDenNgay.getText().toString();

                    tvKetQua.setText(db.Report(tuNgay, denNgay)+ "");
                }
            }
        });
    }
}