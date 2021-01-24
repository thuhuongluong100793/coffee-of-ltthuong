package com.ltthuong.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public ListView lstView;
    public CustomAdapter arrayAdapterNote;
    public ArrayList<PhieuThucDon> arrayListPhieuThucDon = new ArrayList<>();
    public Button btnAdd;
    public Button btnOpenReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDatabaseHelper db = new MyDatabaseHelper(this);

        //Show
        ArrayList<PhieuThucDon> list;
        list =  db.getAllNotes();

        arrayListPhieuThucDon.addAll(list);
        lstView = (ListView) findViewById(R.id.lstView);
        arrayAdapterNote = new CustomAdapter(this, R.layout.layout_one_item_listview, arrayListPhieuThucDon);
        lstView.setAdapter(arrayAdapterNote);
        arrayAdapterNote.notifyDataSetChanged();

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
            }
        });
        btnOpenReport = (Button) findViewById(R.id.btnOpenReport);
        btnOpenReport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), ReportActivity.class);
                startActivity(intent);
            }
        });

    }



}