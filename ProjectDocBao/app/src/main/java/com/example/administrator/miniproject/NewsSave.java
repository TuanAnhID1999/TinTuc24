package com.example.administrator.miniproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class NewsSave extends SQLiteOpenHelper {
    public  NewsSave(FragmenThree context){
        super(context, XMLTaq.DB_NAME, null ,XMLTaq.DB_VERSION );
    }
    public NewsSave(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public NewsSave(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }






    // Khỏi tạo bảng
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + XMLTaq.TITLE + "(" +
                XMLTaq.ITEM + "INTERGER primary key autoincrement, " +
                XMLTaq.DESCRIPTION + "text," +
                XMLTaq.PUB_DATE + "text," +
                XMLTaq.LINK + "text" +
                ")";
        db.execSQL(sql);
    }

    //
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<News> getData(){
        ArrayList<News> arr = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.query(XMLTaq.TITLE, null, null, null, null, null,null);
        int indexTitle = cursor.getColumnIndex(XMLTaq.TITLE);
        int indexItem = cursor.getColumnIndex(XMLTaq.ITEM);
        int indexDes = cursor.getColumnIndex(XMLTaq.DESCRIPTION);
        int indexDate = cursor.getColumnIndex(XMLTaq.PUB_DATE);
        int indexLink = cursor.getColumnIndex(XMLTaq.LINK);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            String title = cursor.getString(indexTitle);
            String item = cursor.getString(indexItem);
            String des = cursor.getString(indexDes);
            String date = cursor.getString(indexDate);
            String link = cursor.getString(indexLink);
            News news = new News(title, des,item, date, link);
            arr.add(news);
            cursor.moveToNext();
        }
        database.close();
        return  arr;
    }

    public long insert(News news) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(XMLTaq.TITLE, news.getTitle());
        values.put(XMLTaq.DESCRIPTION , news.getDesc());
        values.put(XMLTaq.PUB_DATE, news.getPubDate());
        long id = database.insert(XMLTaq.DB_NAME, null, values);
        database.close();
        return id;
    }

    public int update(News news){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(XMLTaq.TITLE, news.getTitle());
        values.put(XMLTaq.DESCRIPTION , news.getDesc());
        values.put(XMLTaq.PUB_DATE, news.getPubDate());
        String selection = XMLTaq.LINK + " = ?";
        String[] selectionAgr = {String.valueOf(news.getLink())};
        int rows = database.update(XMLTaq.DB_NAME, values,
                selection, selectionAgr);
        database.close();
        return rows;
    }


    public int delete(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String selection = XMLTaq.DB_NAME + " = ?";
        String[] selectionAgr = {String.valueOf(id)};
        int rows = database.delete(XMLTaq.TITLE,
                selection, selectionAgr);
        database.close();
        return rows;
    }
}
