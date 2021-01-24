package com.ltthuong.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {
    public Button btnSave;
    public Button btnCancel;
    public Button btnTinhTien;
    Spinner spinSoBan;
    EditText txtThucDon;
    EditText txtSoLuong;
    EditText txtDonGia;
    TextView tvThanhTien;
    EditText txtPhuThu;
    TextView tvTongTien;
    Spinner spinTrangThai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        ArrayList<PhieuThucDon> list;
        list =  db.getAllNotes();


        //Add
        spinSoBan = (Spinner) findViewById(R.id.spinSoBan);
        txtThucDon = (EditText) findViewById(R.id.txtThucDon);
        txtSoLuong = (EditText) findViewById(R.id.txtSoLuong);
        txtDonGia = (EditText) findViewById(R.id.txtDonGia);
        tvThanhTien = (TextView) findViewById(R.id.txtThanhTien);
        txtPhuThu = (EditText) findViewById(R.id.txtPhuThu);
        tvTongTien = (TextView) findViewById(R.id.txtTongTien);
        spinTrangThai = (Spinner) findViewById(R.id.spinTrangThai);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnTinhTien = (Button) findViewById(R.id.btnTinhTien);
        //Spin Số bàn
        ArrayList<String> arrSoBan = new ArrayList<>();
        arrSoBan.add("1");
        arrSoBan.add("2");
        arrSoBan.add("3");
        arrSoBan.add("4");
        arrSoBan.add("5");
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arrSoBan);
        spinSoBan.setAdapter(adapter1);
        //Spin Trạng thái

        ArrayList<String> arrTrangThai = new ArrayList<>();
        arrTrangThai.add("Chưa thu");
        arrTrangThai.add("Đã thu");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arrTrangThai);
        spinTrangThai.setAdapter(adapter2);
        //
        btnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                String soBan = spinSoBan.getSelectedItem().toString();
                String thucDon = txtThucDon.getText().toString();
                String soLuong = txtSoLuong.getText().toString();
                String donGia = txtDonGia.getText().toString();
                int thanhTien = 0;
                if (!tvThanhTien.getText().toString().equals(""))
                {
                    thanhTien = Integer.parseInt(tvThanhTien.getText().toString());
                }
                int phuThu = 0;
                if (!txtPhuThu.getText().toString().equals(""))
                {
                    phuThu = Integer.parseInt(txtPhuThu.getText().toString());
                }
                else phuThu = 0;

                int tongTien = 0;
                if (!tvTongTien.getText().toString().equals(""))
                {
                    tongTien = Integer.parseInt(tvTongTien.getText().toString());
                }
                int posTrangThai = spinTrangThai.getSelectedItemPosition();
                int trangThai = 0;
                if (posTrangThai != 0)
                {
                    trangThai = 1;
                }
                //int trangThai = Integer.parseInt(spinTrangThai.getSelectedItem().toString());

                if(!thucDon.equals("") && !soLuong.equals("") && !donGia.equals("") && !tvThanhTien.getText().toString().equals("") && !tvTongTien.getText().toString().equals(""))
                {
                    PhieuThucDon phieuThucDon = new PhieuThucDon(soBan, thucDon, soLuong, donGia, phuThu, thanhTien, tongTien, "time", trangThai);
                    db.addPhieu(phieuThucDon);
                    //reLoad();
                    Toast.makeText(getApplicationContext(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập thêm thông tin!", Toast.LENGTH_SHORT).show();

            }
        });
        //Cancel
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
               finish();
            }
        });
        //Tinh tien
        btnTinhTien.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                if (!txtSoLuong.getText().toString().equals("") && !txtDonGia.getText().toString().equals(""))
                {
                    String soLuong = txtSoLuong.getText().toString();
                    String donGia = txtDonGia.getText().toString();
                    int thanhTien = 0;
                    int tongTien = 0;
                    int phuThu = 0;

                    String[] separated_soLuong = soLuong.split(",");
                    String[] separated_donGia = donGia.split(",");
                    for (int i = 0; i < separated_soLuong.length; i ++)
                    {
                        thanhTien = thanhTien + Integer.parseInt(separated_soLuong[i].replaceAll(" ",""))*Integer.parseInt(separated_donGia[i].replaceAll(" ", ""));
                    }
                    //int thanhTien = Integer.parseInt(soLuong)*Integer.parseInt(donGia);
                    if(!txtPhuThu.getText().toString().equals(""))
                        phuThu = Integer.parseInt(txtPhuThu.getText().toString());
                    tongTien = thanhTien + phuThu;
                    String txtThanhTien = thanhTien + "";
                    String txtTongTien = tongTien + "";
                    tvThanhTien.setText(txtThanhTien);
                    tvTongTien.setText(txtTongTien);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập thêm thông tin!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}