package com.example.thefi.soccermanagernew;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.thefi.soccermanagernew.data.TeamContract.TeamEntry;

public class TeamSelectionActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int TEAM_LOADER = 1;

    TeamCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_selection);

        Button newTeamButton = findViewById(R.id.newTeamButton);
        newTeamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeamSelectionActivity.this,NewTeamActivity.class);
                startActivity(intent);
            }
        });

        ListView teamListView = (ListView) findViewById(R.id.list_view_team);

        View emptyView = findViewById(R.id.empty_view);
        teamListView.setEmptyView(emptyView);


        mCursorAdapter = new TeamCursorAdapter(this, null);
        teamListView.setAdapter(mCursorAdapter);

        teamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TeamSelectionActivity.this,TeamManagerActivity.class);
                Uri currentTeamUri = ContentUris.withAppendedId(TeamEntry.CONTENT_URI, id);
                intent.setData(currentTeamUri);
                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(TEAM_LOADER, null, this);
    }

    private void insertDummyData(){
        ContentValues values = new ContentValues();
        values.put(TeamEntry.COLUMN_TEAM_NAME, "Scranton Prep");
        values.put(TeamEntry.COLUMN_TEAM_GOALS_FOR, 17);
        values.put(TeamEntry.COLUMN_TEAM_GOALS_ALLOWED, 6);
        values.put(TeamEntry.COLUMN_TEAM_WINS, 7);
        values.put(TeamEntry.COLUMN_TEAM_DRAWS, 1);
        values.put(TeamEntry.COLUMN_TEAM_LOSSES, 0);

        getContentResolver().insert(TeamEntry.CONTENT_URI, values);
    }

    private void deleteAllTeams(){getContentResolver().delete(TeamEntry.CONTENT_URI,null,null);}

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_team_selection,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_insert_dummy_data:
                insertDummyData();
                return true;
            case R.id.action_delete_all_entries:
                deleteAllTeams();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projections = {TeamEntry._ID, TeamEntry.COLUMN_TEAM_NAME};
        return new CursorLoader(this,TeamEntry.CONTENT_URI,projections,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){
        mCursorAdapter.swapCursor(null);
    }
}
