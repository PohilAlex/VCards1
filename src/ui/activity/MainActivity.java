package ui.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.actionbarsherlock.app.SherlockListActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.MenuItem;
import com.pohil.vcards.R;
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
        int[] to = {R.id.pile_name};
        ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.pile_list_item, pileDoa.getList(), from, to);
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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(MainActivity.this, EditCardActivity.class);
        intent.putExtra(EditCardActivity.PILE_ID_KEY, id);
        startActivity(intent);
        //Toast.makeText(this, id + "", Toast.LENGTH_SHORT).show();
    }

    class CardActionMode implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, com.actionbarsherlock.view.Menu menu) {
            getSupportMenuInflater().inflate(R.menu.pile_edit, menu);
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
