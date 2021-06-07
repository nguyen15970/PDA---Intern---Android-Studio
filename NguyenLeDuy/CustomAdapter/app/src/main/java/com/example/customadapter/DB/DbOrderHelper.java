package com.example.customadapter.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbOrderHelper extends SQLiteOpenHelper {
    public static final String DbOrder = "Order.db";

    public DbOrderHelper(@Nullable Context context) {
        super(context, DbOrder, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists Orders (id TEXT primary key, date, customer, phone_number, address, note)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Orders");
    }
    public Boolean insertData(String id, long date, String customer, String phone_number, String address, String note) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("id", id);
        contentValues.put("date", date);
        contentValues.put("customer", customer);
        contentValues.put("phone_number", phone_number);
        contentValues.put("address", address);
        contentValues.put("note", note);

        long result = MyDB.insert("Orders", null, contentValues);
        if (result == 1) return false;
        else return true;
    }

    public List<String> getOrderInfoFromDB(String id)
    {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Orders where id = ?", new String[]{id});
        if(cursor.moveToFirst())
        {
            String datetime = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).
                    format(new Date(cursor.getLong(1)));
            list.add(datetime);
            String customer = cursor.getString(2);
            list.add(customer);
            String phone = cursor.getString(3);
            list.add(phone);
            String address = cursor.getString(4);
            list.add(address);
            String note = cursor.getString(5);
            list.add(note);
        }
        return list;
    }

    public void updateCustomerInfo(String id, String customer, String phone_number, String address, String note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("customer", customer);
        values.put("phone_number", phone_number);
        values.put("address", address);
        values.put("note", note);

        db.update("Orders", values, "id = ? ", new String[]{id});
    }

    public Boolean checkOrderExists(String id)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from Orders where id = ?", new String[]{id});
        if (cursor.getCount() > 0)
            return true;
        else return false;
    }

    public void deleteOrder(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from Orders where id = ? ", new String[]{id});
    }
}
