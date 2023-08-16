package com.example.nav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Studyplan.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Plans(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, myplan TEXT,datetime TEXT)");
        db.execSQL("create Table Assignments(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, discription TEXT, duedate INTEGER , createdtime TEXT)");
        db.execSQL("create Table Exams(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, discription TEXT, quizdate INTEGER, createdtime TEXT)");
        db.execSQL("create Table Lectures(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT, discription TEXT, createdtime TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Plans");
        db.execSQL("DROP TABLE IF EXISTS Assignments");
        db.execSQL("DROP TABLE IF EXISTS Exams");
        db.execSQL("DROP TABLE IF EXISTS Lectures");
        onCreate(db);
    }

    public boolean addplan(String name, String plan){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("myplan",plan);

        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy", Locale.getDefault());
        String datetime = sdf.format(new Date());
        contentValues.put("datetime",datetime);

        long result=db.insert("Plans",null,contentValues);
        if (result==-1){
            return false;
        }else {
            return true;
        }

    }

    public Cursor readAllplans(){
        SQLiteDatabase db= this.getReadableDatabase();
        String qry="select * from Plans";
        Cursor cursor=db.rawQuery(qry,null);
        return cursor;
    }

    public boolean del_plan(int id){
        String i=String.valueOf(id);
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Plans where id = ?",new String[]{i});
        if (cursor.getCount()>0){
            long result=db.delete("Plans", "id=?",new String[]{i});
            if(result==-1){
                return false;
            }
            else
                return true;
        }else
            return false;
    }

    public boolean addlecture(String name,String discription){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("discription",discription);

        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy", Locale.getDefault());
        String createdtime=sdf.format(new Date());
        contentValues.put("createdtime",createdtime);

        long result=db.insert("Lectures",null,contentValues);
        if(result==-1){
            return false;
        }
        else
            return true;
    }

    public Cursor readAllectures(){
        SQLiteDatabase db=this.getReadableDatabase();
        String qry="select * from Lectures";
        Cursor cursor=db.rawQuery(qry,null);
        return cursor;
    }

    public boolean del_lecture(int id){
        String i=String.valueOf(id);
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Lectures where id = ?", new String[]{i});
        if(cursor.getCount()>0){
            long result=db.delete("Lectures","id=?",new String[]{i});
            if(result==-1){
                return false;
            }
            else
                return true;
        }else
            return false;
    }

    public boolean addass(String name,String discription, int duedate){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("discription",discription);
        contentValues.put("duedate",duedate);

        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy", Locale.getDefault());
        String createdtime=sdf.format(new Date());
        contentValues.put("createdtime",createdtime);

        long result=db.insert("Assignments",null,contentValues);
        if(result==-1){
            return false;
        }
        else
            return true;
    }

    public Cursor readAllasss(){
        SQLiteDatabase db=this.getReadableDatabase();
        String qry="select * from Assignments";
        Cursor cursor=db.rawQuery(qry,null);
        return cursor;
    }

    public boolean del_ass(int id){
        String i=String.valueOf(id);
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Assignments where id = ?",new String[]{i});
        if(cursor.getCount()>0){
            long result=db.delete("Assignments","id=?",new String[]{i});
            if(result==-1){
                return false;
            }
            else
                return true;
        }
        else
            return false;
    }

    public boolean addexam(String name,String discription, int quizdate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("discription", discription);
        contentValues.put("quizdate", quizdate);

        SimpleDateFormat sdf = new SimpleDateFormat("dd:MM:yyyy", Locale.getDefault());
        String createdtime = sdf.format(new Date());
        contentValues.put("createdtime", createdtime);

        long result = db.insert("Exams", null, contentValues);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    public Cursor readAllexams(){
        SQLiteDatabase db= this.getReadableDatabase();
        String qry="select * from Exams";
        Cursor cursor=db.rawQuery(qry,null);
        return cursor;
    }

    public boolean del_exam(int id){
        String i=String.valueOf(id);
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Exams where id = ?",new String[]{i});
        if(cursor.getCount()>0){
            long result=db.delete("Exams","id=?",new String[]{i});
            if(result==-1){
                return false;
            }
            else
                return true;
        }
        else
            return false;
    }


}