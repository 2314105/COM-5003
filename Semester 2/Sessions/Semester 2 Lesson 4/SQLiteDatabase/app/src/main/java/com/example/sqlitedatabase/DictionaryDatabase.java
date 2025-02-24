package com.example.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DictionaryDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dictionary.db";
    private static final String TABLE_DICTIONARY = "dictionary";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_WORD = "word";
    private static final String COLUMN_DEFINITION = "definition";

    private static final int DATABASE_VERSION = 1;

    public DictionaryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_DICTIONARY + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_WORD + " TEXT UNIQUE, "
                + COLUMN_DEFINITION + " TEXT);";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DICTIONARY);
        onCreate(db);
    }

    public void saveRecord(String word, String definition) {
        long id = findWordId(word);
        if (id > 0) {
            updateRecord(id, definition);
        } else {
            addRecord(word, definition);
        }
    }

    public long addRecord(String word, String definition) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_WORD, word);
            values.put(COLUMN_DEFINITION, definition);
            return db.insert(TABLE_DICTIONARY, null, values);
        }
    }

    public int updateRecord(long id, String definition) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_DEFINITION, definition);
            return db.update(TABLE_DICTIONARY, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        }
    }

    public int deleteRecord(long id) {
        try (SQLiteDatabase db = this.getWritableDatabase()) {
            return db.delete(TABLE_DICTIONARY, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        }
    }

    public long findWordId(String word) {
        long id = -1;
        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.rawQuery("SELECT " + COLUMN_ID + " FROM " + TABLE_DICTIONARY + " WHERE " + COLUMN_WORD + " = ?", new String[]{word})) {
            if (cursor.moveToFirst()) {
                id = cursor.getLong(0);
            }
        }
        return id;
    }

    public String getDefinition(long id) {
        String definition = "";
        try (SQLiteDatabase db = this.getReadableDatabase();
             Cursor cursor = db.rawQuery("SELECT " + COLUMN_DEFINITION + " FROM " + TABLE_DICTIONARY + " WHERE " + COLUMN_ID + " = ?", new String[]{String.valueOf(id)})) {
            if (cursor.moveToFirst()) {
                definition = cursor.getString(0);
            }
        }
        return definition;
    }

    public Cursor getWordList() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT " + COLUMN_ID + ", " + COLUMN_WORD + " FROM " + TABLE_DICTIONARY + " ORDER BY " + COLUMN_WORD + " ASC", null);
    }
}