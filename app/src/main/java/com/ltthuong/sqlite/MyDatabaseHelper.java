package com.ltthuong.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "PhieuThucDon.db";

    // Table name: PhieuThucDon.
    private static final String TABLE_PHIEU = "PhieuThucDon";

    private static final String COLUMN_ID ="ID";
    private static final String COLUMN_SOBAN ="Soban";
    private static final String COLUMN_THUCDON = "Thucdon";
    private static final String COLUMN_DONGIA = "Dongia";
    private static final String COLUMN_SOLUONG = "Soluong";
    private static final String COLUMN_THANHTIEN ="Thanhtien";
    private static final String COLUMN_PHUTHU = "Phuthu";
    private static final String COLUMN_TONGTIEN = "Tongtien";
    private static final String COLUMN_TRANGTHAI = "Trangthai";
    private static final String COLUMN_NGAYDAT = "Ngaydat";



    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String script = "CREATE TABLE " + TABLE_PHIEU + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_SOBAN + " TEXT,"
                + COLUMN_THUCDON + " TEXT,"
                + COLUMN_DONGIA + " TEXT,"
                + COLUMN_SOLUONG + " TEXT,"
                + COLUMN_THANHTIEN + " NUMBER,"
                + COLUMN_PHUTHU + " NUMBER,"
                + COLUMN_TONGTIEN + " NUMBER,"
                + COLUMN_TRANGTHAI + " BOOLEAN,"
                +  COLUMN_NGAYDAT + " TEXT"
                + ")";

        // Execute Script.
        System.out.println(script);
        db.execSQL(script);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHIEU);

        // Create tables again
        onCreate(db);
    }


    public void addPhieu(PhieuThucDon phieuThucDon) {
        Log.i(TAG, "MyDatabaseHelper.add ... " + phieuThucDon.getID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SOBAN, phieuThucDon.getSoBan());
        values.put(COLUMN_THUCDON, phieuThucDon.getThucDon());
        values.put(COLUMN_DONGIA, phieuThucDon.getDonGia());
        values.put(COLUMN_SOLUONG, phieuThucDon.getSoLuong());
        values.put(COLUMN_THANHTIEN, phieuThucDon.getThanhTien());
        values.put(COLUMN_PHUTHU, phieuThucDon.getTienPhuThu());
        values.put(COLUMN_TONGTIEN, phieuThucDon.getTongTien());
        values.put(COLUMN_TRANGTHAI, phieuThucDon.getTrangThai());


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentTime = dateFormat.format(calendar.getTime());

        values.put(COLUMN_NGAYDAT, currentTime.toString());

        // Inserting Row
        db.insert(TABLE_PHIEU, null, values);

        // Closing database connection
        db.close();
    }


    public PhieuThucDon getPhieu(int ID) {
        Log.i(TAG, "MyDatabaseHelper.get ... " + ID);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_PHIEU, new String[] { COLUMN_ID,
                        COLUMN_SOBAN, COLUMN_THUCDON, COLUMN_DONGIA, COLUMN_SOLUONG,
                        COLUMN_THANHTIEN, COLUMN_PHUTHU, COLUMN_TONGTIEN, COLUMN_TRANGTHAI, COLUMN_NGAYDAT}, COLUMN_ID + "=?",
                new String[] { String.valueOf(ID) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        PhieuThucDon phieuThucDon = new PhieuThucDon(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getInt(5), cursor.getInt(6), cursor.getInt(7), cursor.getString(8), cursor.getInt(9));
        // return phieuThucDon
        return phieuThucDon;
    }


    public ArrayList<PhieuThucDon> getAllNotes() {
        Log.i(TAG, "MyDatabaseHelper.getAll ... " );

        ArrayList<PhieuThucDon> phieuThucDonList = new ArrayList<PhieuThucDon>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PHIEU;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            PhieuThucDon phieuThucDon = new PhieuThucDon();
            phieuThucDon.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_ID))));
            phieuThucDon.setSoBan(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_SOBAN)));
            phieuThucDon.setThucDon(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_THUCDON)));
            phieuThucDon.setSoLuong(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_SOLUONG)));
            phieuThucDon.setDonGia(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_DONGIA)));
            phieuThucDon.setTienPhuThu(cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_PHUTHU)));
            phieuThucDon.setThanhTien(cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_THANHTIEN)));
            phieuThucDon.setTongTien(cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_TONGTIEN)));
            phieuThucDon.setTrangThai(cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_TRANGTHAI)));
            phieuThucDon.setNgayDat(cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_NGAYDAT)));
            // Adding phieuThucDon to list
            phieuThucDonList.add(phieuThucDon);
            cursor.moveToNext();
        }
        cursor.close();

        // return note list
        return phieuThucDonList;
    }

    public int Report(String tuNgay, String denNgay) {
        Log.i(TAG, "MyDatabaseHelper.getReport ... " );//24/01/2021
        String tuNgay1 = tuNgay.substring(6,10) + tuNgay.substring(3,5) + tuNgay.substring(0,2);
        String denNgay1 = denNgay.substring(6,10) + denNgay.substring(3,5) + denNgay.substring(0,2);
        String subQuery = "substr(" + COLUMN_NGAYDAT + ",7,4)||" +
                "substr(" + COLUMN_NGAYDAT + ",4,2)||" +
                "substr(" + COLUMN_NGAYDAT + ",1,2) ";

        String sumQuery = "SELECT  sum("+ COLUMN_TONGTIEN+") " +
                "FROM " + TABLE_PHIEU +
                " WHERE ("+ subQuery + ")"+
                " BETWEEN '" + tuNgay1 + "' AND '" + denNgay1 + "' " +
                " AND " + COLUMN_TRANGTHAI + " = 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sumQuery, null);
        int sum;
        if(cursor.moveToFirst())
            sum = cursor.getInt(0);
        else
            sum = 0;
        cursor.close();

        // return count
        return sum;
    }


    public int updatePhieu(PhieuThucDon phieuThucDon) {
        Log.i(TAG, "MyDatabaseHelper.updatePhieu ... "  + phieuThucDon.getID());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, phieuThucDon.getID());
        values.put(COLUMN_SOBAN, phieuThucDon.getSoBan());
        values.put(COLUMN_THUCDON, phieuThucDon.getThucDon());
        values.put(COLUMN_SOLUONG, phieuThucDon.getSoLuong());
        values.put(COLUMN_DONGIA, phieuThucDon.getDonGia());
        values.put(COLUMN_THANHTIEN, phieuThucDon.getThanhTien());
        values.put(COLUMN_PHUTHU, phieuThucDon.getTienPhuThu());
        values.put(COLUMN_TONGTIEN, phieuThucDon.getTongTien());
        values.put(COLUMN_TRANGTHAI, phieuThucDon.getTrangThai());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String currentTime = dateFormat.format(calendar.getTime());
        values.put(COLUMN_NGAYDAT, currentTime.toString());

        // updating row
        return db.update(TABLE_PHIEU, values, COLUMN_ID + " = ?",
                new String[]{String.valueOf(phieuThucDon.getID())});
    }

    public void deletePhieu(PhieuThucDon phieuThucDon) {
        Log.i(TAG, "MyDatabaseHelper.delete ... " + phieuThucDon.getID() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PHIEU, COLUMN_ID + " = ?",
                new String[] { String.valueOf(phieuThucDon.getID()) });
        db.close();
    }

}
