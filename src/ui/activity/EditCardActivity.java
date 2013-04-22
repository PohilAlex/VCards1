package ui.activity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.pohil.vcards.R;
import com.pohil.vcards.db.DbOpenHelper;
import com.pohil.vcards.db.PileDAO;

public class EditCardActivity extends SherlockListActivity {

    public final static String PILE_ID_KEY = "EditCardActivity.PILE_ID_KEY";
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        int pileId = getIntent().getExtras().getInt(PILE_ID_KEY);
        db = new DbOpenHelper(this).getWritableDatabase();
        PileDAO pileDao = new PileDAO(db);

        String[] from = {"card.word, card.translation"};
        int[] to = {R.id.card_word, R.id.card_translation};
        ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.card_list_item, pileDao.get(pileId), from, to);
        setListAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (db != null) {
            db.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.card, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return super.onMenuItemSelected(featureId, item);
    }
}
