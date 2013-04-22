package com.pohil.vcards;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.MenuItem;
import com.pohil.vcards.db.DbOpenHelper;
import com.pohil.vcards.db.PileDAO;

public class MainActivity extends SherlockListActivity implements AdapterView.OnItemLongClickListener {

    SQLiteDatabase db;
    ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        db = new DbOpenHelper(this).getWritableDatabase();
        PileDAO pileDoa = new PileDAO(db);
        //pileDoa.creare("name1", "description1");

        String[] from = {"name"};
        int[] to = {R.id.card_name};
        ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.card_list_item, pileDoa.getList(), from, to);
        setListAdapter(adapter);
        getListView().setOnItemLongClickListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (db != null) {
            db.close();
        }
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        actionMode = startActionMode(new CardActionMode());
        return true;
    }

    class CardActionMode implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, com.actionbarsherlock.view.Menu menu) {
            getSupportMenuInflater().inflate(R.menu.card, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, com.actionbarsherlock.view.Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.edit_card:

            }
            actionMode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
        }
    }
}
