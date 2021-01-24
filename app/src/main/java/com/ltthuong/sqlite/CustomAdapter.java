package com.ltthuong.sqlite;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<PhieuThucDon> {
    private Context context;
    private int resource;
    private ArrayList<PhieuThucDon> arrPhieuThucDon;

    public CustomAdapter(Context context, int resource, ArrayList<PhieuThucDon> arrPhieuThucDon) {
        super(context, resource, arrPhieuThucDon);
        this.context = context;
        this.resource = resource;
        this.arrPhieuThucDon = arrPhieuThucDon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_one_item_listview, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.tvID = (TextView) convertView.findViewById(R.id.tvID);
            viewHolder.tvSoBan = (TextView) convertView.findViewById(R.id.tvSoBan);
            viewHolder.tvThucDon = (TextView) convertView.findViewById(R.id.tvThucDon);
            //viewHolder.tvSoLuong= (TextView) convertView.findViewById(R.id.tvSoLuong);
            //viewHolder.tvDonGia = (TextView) convertView.findViewById(R.id.tvDonGia);
            viewHolder.tvTrangThai = (TextView) convertView.findViewById(R.id.tvTrangThai);
            viewHolder.tvThanhTien = (TextView) convertView.findViewById(R.id.tvThanhTien);
            viewHolder.tvPhuThu = (TextView) convertView.findViewById(R.id.tvPhuThu);
            viewHolder.tvTongTien= (TextView) convertView.findViewById(R.id.tvTongTien);
            viewHolder.tvNgayDat= (TextView) convertView.findViewById(R.id.tvNgayDat);
            viewHolder.btnUpdate = (Button) convertView.findViewById(R.id.btnUpdate);
            viewHolder.btnDelete = (Button) convertView.findViewById(R.id.btnDelete);


            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PhieuThucDon phieuThucDon = arrPhieuThucDon.get(position);
        viewHolder.tvID.setText(phieuThucDon.getID()+ "");
        viewHolder.tvSoBan.setText(phieuThucDon.getSoBan());

        //
        String[] separated_thucDon = phieuThucDon.getThucDon().split(",");
        String[] separated_soLuong = phieuThucDon.getSoLuong().split(",");
        String[] separated_donGia = phieuThucDon.getDonGia().split(",");
        String thucDon = "";
        for (int i = 0; i < separated_soLuong.length; i ++)
        {
            thucDon = thucDon + separated_thucDon[i].replaceAll(" ", "") + " x "
                    + separated_soLuong[i].replaceAll(" ", "") + " x "
                    + separated_donGia[i].replaceAll(" ", "")
                    + "\r\n";
        }
        //
        viewHolder.tvThucDon.setText(thucDon);
        //viewHolder.tvSoLuong.setText(phieuThucDon.getSoLuong());
        //viewHolder.tvDonGia.setText(phieuThucDon.getDonGia());
        String trangThai = "";
        if (phieuThucDon.getTrangThai() != 0)
        {
            trangThai = "Đã thu";
        }
        else trangThai = "Chưa thu";
        viewHolder.tvTrangThai.setText(trangThai);
        viewHolder.tvThanhTien.setText(phieuThucDon.getThanhTien()+"");
        viewHolder.tvPhuThu.setText(phieuThucDon.getTienPhuThu()+"");
        viewHolder.tvTongTien.setText(phieuThucDon.getTongTien()+"");
        viewHolder.tvNgayDat.setText(phieuThucDon.getNgayDat());

        viewHolder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UpdateActivity.class);
                intent.putExtra("ID", phieuThucDon.getID()+"" );
                intent.putExtra("soban", phieuThucDon.getSoBan());
                intent.putExtra("thucdon", phieuThucDon.getThucDon());
                intent.putExtra("soluong", phieuThucDon.getSoLuong());
                intent.putExtra("dongia", phieuThucDon.getDonGia() );
                intent.putExtra("thanhtien", phieuThucDon.getThanhTien()+"");
                intent.putExtra("phuthu", phieuThucDon.getTienPhuThu()+"");
                intent.putExtra("tongtien", phieuThucDon.getTongTien()+"");
                intent.putExtra("trangthai", phieuThucDon.getTrangThai()+"");
                view.getContext().startActivity(intent);
            }
        });
        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
            @Override
            public void onClick(View view) {
                alert.setTitle("Delete entry");
                alert.setMessage("Are you sure you want to delete?");
                alert.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        MyDatabaseHelper db = new MyDatabaseHelper(getContext());
                        db.deletePhieu(phieuThucDon);
                        Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        context.startActivity(intent);
                    }
                });
                alert.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // close dialog
                        dialog.cancel();
                    }
                });
                alert.show();
            }
        });
        return convertView;
    }

    public class ViewHolder {
        TextView tvID, tvSoBan, tvThucDon, tvSoLuong, tvDonGia, tvPhuThu, tvTrangThai, tvThanhTien, tvTongTien, tvNgayDat;
        Button btnUpdate, btnDelete;
    }
}
