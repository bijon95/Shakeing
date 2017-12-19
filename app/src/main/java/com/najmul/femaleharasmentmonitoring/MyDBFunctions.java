package com.najmul.femaleharasmentmonitoring;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HP-PC on 11/7/2017.
 */

public class MyDBFunctions extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "mydb";
    private static final String TABLE_NAME = "mytab";

    private static final String TAB_ID = "id";
    private static final String TAB_NAME = "name";
    private static final String TAB_DAYS = "days";

    MyDBFunctions(Context c){
        super(c,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String s="CREATE TABLE "+TABLE_NAME+"("+TAB_ID+" INTIGER PRIMARY KEY ,"+TAB_NAME+" TEXT ,"+TAB_DAYS+" TEXT)";
        db.execSQL(s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersoin) {

    }

    void addingDataToTable(DataTemp dt){

        SQLiteDatabase sqd = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(TAB_NAME, dt.getName());
        cv.put(TAB_DAYS, dt.getDay());

        sqd.insert(TABLE_NAME, null ,cv);
        sqd.close();

    }
    String[] my_data(){
        SQLiteDatabase sq =this.getReadableDatabase();
         String q="SELECT * FROM "+TABLE_NAME;
        Cursor c= sq.rawQuery(q,null);
        String[] recvied_data = new String[c.getCount()];

        c.moveToFirst();
        if(c.moveToFirst()){
            int counter = 0;
            do{
                recvied_data[counter]="Name: "+ c.getString(c.getColumnIndex(TAB_NAME+""))+"\nPhone: "+
                c.getString(c.getColumnIndex(TAB_DAYS+""));
                counter = counter + 1;
            }while (c.moveToNext());
        }
        return recvied_data;
    }

    public String[] phoneNumber(){

        SQLiteDatabase sq =this.getReadableDatabase();
        String q="SELECT * FROM "+TABLE_NAME;
        Cursor c= sq.rawQuery(q,null);
        String[] phoneList = new String[c.getCount()];

        c.moveToFirst();
        if(c.moveToFirst()){
            int counter = 0;
            do{
                phoneList[counter]= c.getString(c.getColumnIndex(TAB_DAYS+""));
                counter = counter + 1;
            }while (c.moveToNext());
        }
        return phoneList;
    }

    public Cursor singleData(String name){

        SQLiteDatabase sq =this.getReadableDatabase();


        String q="SELECT * FROM " +TABLE_NAME +" WHERE "+TAB_ID +"='" + "1'";
        Cursor c = sq.rawQuery(q, null);

        return c;

    }

    public void Delete_Raw()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME);
        db.close();
    }
    public String getEmployeeName(String empNo) {
        SQLiteDatabase sq =this.getReadableDatabase();
        Cursor cursor = null;
        String q="SELECT "+TAB_ID+" FROM " +TABLE_NAME +" WHERE "+TAB_ID +"=?";
        String empName = "";
        try {
            cursor = sq.rawQuery(q, new String[] {empNo + ""});
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                empName = cursor.getString(cursor.getColumnIndex(TAB_ID));
            }
            return empName;
        }finally {
            cursor.close();
        }
    }




}
