package com.pohil.vcards.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.pohil.vcards.model.Card;
import com.pohil.vcards.model.Pile;

import java.util.ArrayList;

public class PileDAO extends BaseDAO{

    public PileDAO(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public String getTableName() {
        return DbOpenHelper.TABLE_PILE;
    }

    public Pile creare(String name, String description) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("description", description);
        int id = (int) db.insert(getTableName(), null, cv);
        Pile pile = new Pile();
        pile.setName(name);
        pile.setDescription(description);
        pile.setId(id);
        return pile;
    }

    public Cursor get(int id) {
        String sql = "SELECT card._id, card.word, card.translation, pile.name FROM " +
                "pile LEFT OUTER JOIN card ON pile._id=card.pile_id " +
                "WHERE pile._id="+id;
        return db.rawQuery(sql, null);
    }

    public Cursor getList() {
        String sql = "select * from pile";
        return db.rawQuery(sql, null);
    }

    public void addCard(Pile pile, Card card) {
        CardDAO cardDao = new CardDAO(db);
        cardDao.create(pile.getId(), card);
    }

}
