package com.adjinurdiman.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "notes";
    private static final String TABLE_NAME = "tbl_notes";
    private static final String KEY_ID = "_id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESC = "description";
    private static final String KEY_DATE = "datetime";

    public DatabaseHelper (Context context){
        super(context, DB_NAME, null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TITLE + " TEXT, " + KEY_DESC + " TEXT, "+KEY_DATE+" TEXT)";
        db.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public void insert(NoteModel notesModels){
        SQLiteDatabase db =getWritableDatabase();
        ContentValues values=new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        values.put(KEY_DATE,dateFormat.format(date));
        values.put(KEY_TITLE,notesModels.getJudul());
        values.put(KEY_DESC,notesModels.getDeskripsi());
        db.insert(TABLE_NAME,null,values);
    }

    public List<NoteModel> selectUserData(){
        ArrayList<NoteModel> notesList=new ArrayList<NoteModel>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while (c.moveToNext()){
            int id = c.getInt(0);
            String title=c.getString(1);
            String desc=c.getString(2);
            String date=c.getString(3);
            NoteModel notesModels = new NoteModel();
            notesModels.setId(id);
            notesModels.setJudul(title);
            notesModels.setDeskripsi(desc);
            notesModels.setDate(date);
            notesList.add(notesModels);
        }
        return notesList;
    }

    public void delete(int id){
        SQLiteDatabase db =getWritableDatabase();
        String whereClause=KEY_ID+"='"+id+"'";
        Log.d("idvalues", ""+id);
        db.delete(TABLE_NAME,whereClause,null);
    }
    public void update(NoteModel notesModels, int id){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_TITLE,notesModels.getJudul());
        values.put(KEY_DESC,notesModels.getDeskripsi());
        String whereClause=KEY_ID+"='"+notesModels.getId()+"'";
        Log.d("idvalues", ""+values+" "+whereClause);
        db.update(TABLE_NAME, values, KEY_ID + "=" + id, null);
    }
}
