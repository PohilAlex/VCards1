package com.pohil.vcards.db;

import android.database.sqlite.SQLiteDatabase;

public abstract class BaseDAO {
    SQLiteDatabase db;

    public BaseDAO(SQLiteDatabase db) {
        this.db = db;
    }

    abstract public String getTableName();
}
