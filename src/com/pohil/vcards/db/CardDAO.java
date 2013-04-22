package com.pohil.vcards.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.pohil.vcards.model.Card;

public class CardDAO  extends BaseDAO {

    public CardDAO(SQLiteDatabase db) {
        super(db);
    }

    @Override
    public String getTableName() {
        return DbOpenHelper.TABLE_CARD;
    }

    public Card getCard(int id) {
        return null;
    }

    public int create(int  pileId, Card card) {
        ContentValues cv = toContentValue(card);
        cv.put("pile_id", pileId);
        return (int)db.insert(getTableName(), null, toContentValue(card));
    }

    protected ContentValues toContentValue(Card card) {
        ContentValues cv = new ContentValues();
        if (card.getId() > 0) {
            cv.put("_id", card.getId());
        }
        cv.put("word", card.getWord());
        cv.put("translation", card.getTranslation());
        return cv;
    }

}
