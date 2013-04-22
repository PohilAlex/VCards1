package com.pohil.vcards.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper{
	
    public static final String DB_NAME = "vcards.db";
    public static final String TABLE_CARD = "card";
    public static final String TABLE_PILE = "pile";

    private static final int DB_VERSION = 2;
    private static final String CREATE_TABLE_PILE = "CREATE TABLE " + TABLE_PILE +
            "(_id INTEGER PRIMARY KEY," +
            "name STRING NOT NULL," +
            "description STRING);";

    private static final String CREATE_TABLE_CARD = "CREATE TABLE " + TABLE_CARD +
            "(_id INTEGER PRIMARY KEY," +
            "word STRING NOT NULL," +
            "translation STRING NOT NULL," +
            "pile_id INTEGER NOT NULL," +
            "FOREIGN KEY(pile_id) REFERENCES pile(_id));";

	public DbOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE_PILE);
        database.execSQL(CREATE_TABLE_CARD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database,int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_CARD);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_PILE);
	}
	
}
