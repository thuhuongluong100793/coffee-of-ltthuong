package com.ltthuong.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    TextView tvID;
    Spinner spinSoBan;
    EditText txtThucDon;
    EditText txtSoLuong;
    EditText txtDonGia;
    TextView tvThanhTien;
    EditText txtPhuThu;
    TextView tvTongTien;
    Spinner spinTrangThai;
    Button btnSaveUpdate;
    Button btnBack;
    Button btnTinhTien;
    public ListView lstView;
    public CustomAdapter arrayAdapter;
    public ArrayList<PhieuThucDon> arrayListPhieuThucDon = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        //Update
        Bundle bundle = getIntent().getExtras();
        if(bundle!= null)
        {
            tvID = (TextView) findViewById(R.id.txtUpdateID);
            spinSoBan = (Spinner) findViewById(R.id.spinUpdateSoBan);
            txtThucDon = (EditText) findViewById(R.id.txtUpdateThucDon);
            txtSoLuong = (EditText) findViewById(R.id.txtUpdateSoLuong);
            txtDonGia = (EditText) findViewById(R.id.txtUpdateDonGia);
            tvThanhTien = (TextView) findViewById(R.id.txtUpdateThanhTien);
            txtPhuThu = (EditText) findViewById(R.id.txtUpdatePhuThu);
            tvTongTien = (TextView) findViewById(R.id.txtUpdateTongTien);
            spinTrangThai = (Spinner) findViewById(R.id.spinUpdateTrangThai);
            //
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

            tvID.setText(bundle.get("ID").toString());
            txtThucDon.setText(bundle.get("thucdon").toString());
            txtSoLuong.setText(bundle.get("soluong").toString());
            txtDonGia.setText(bundle.get("dongia").toString());
            tvThanhTien.setText(bundle.get("thanhtien").toString());
            txtPhuThu.setText(bundle.get("phuthu").toString());
            tvTongTien.setText(bundle.get("tongtien").toString());
            spinSoBan.setSelection(adapter1.getPosition(bundle.get("soban").toString()));
            String trangThai = "Chưa thu";
            if (!bundle.get("trangthai").toString().equals("0"))
            {
                trangThai = "Đã thu";
            }
            else trangThai = "Chưa thu";
            spinTrangThai.setSelection(adapter2.getPosition(bundle.get("trangthai").toString()));
        }
        MyDatabaseHelper db = new MyDatabaseHelper(this);
        btnSaveUpdate = (Button) findViewById(R.id.btnSaveUpdate);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnTinhTien = (Button) findViewById(R.id.btnUpdateTinhTien);
        btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                int ID = Integer.parseInt(tvID.getText().toString()) ;
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
                if(!thucDon.equals("") && !soLuong.equals("") && !donGia.equals("") && !tvThanhTien.getText().toString().equals("") && !tvTongTien.getText().toString().equals(""))
                {
                    PhieuThucDon phieuThucDon = new PhieuThucDon(ID, soBan, thucDon, soLuong, donGia, phuThu, thanhTien, tongTien, "time", trangThai);
                    db.updatePhieu(phieuThucDon);
                    Toast.makeText(getApplicationContext(), "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                    finish();
                    //reLoad();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập thêm thông tin!", Toast.LENGTH_SHORT).show();
                }



            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v)
           {
               finish();
           }
        });
        btnTinhTien.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
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