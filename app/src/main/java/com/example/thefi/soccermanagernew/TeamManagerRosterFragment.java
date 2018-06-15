package com.example.thefi.soccermanagernew;


import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.thefi.soccermanagernew.data.TeamContract;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamManagerRosterFragment extends Fragment {
    private static final int TEAM_LOADER = 1;

    TeamCursorAdapter mCursorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_team_manager_roster, container, false);


//        Button newTeamButton = findViewById(R.id.newTeamButton);
//        newTeamButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(TeamSelectionActivity.this, NewTeamActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        ListView teamListView = (ListView) findViewById(R.id.list_view_team);
//
//        View emptyView = findViewById(R.id.empty_view);
//        teamListView.setEmptyView(emptyView);
//
//
//        mCursorAdapter = new TeamCursorAdapter(this, null);
//        teamListView.setAdapter(mCursorAdapter);
//
//        teamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(TeamSelectionActivity.this, TeamManagerActivity.class);
//                Uri currentTeamUri = ContentUris.withAppendedId(TeamContract.TeamEntry.CONTENT_URI, id);
//                intent.setData(currentTeamUri);
//                startActivity(intent);
//            }
//        });
//
//        getLoaderManager().initLoader(TEAM_LOADER, null, this);
//    }
//
//    private void insertDummyData() {
//        ContentValues values = new ContentValues();
//        values.put(TeamContract.TeamEntry.COLUMN_TEAM_NAME, "Scranton Prep");
//        values.put(TeamContract.TeamEntry.COLUMN_TEAM_GOALS_FOR, 17);
//        values.put(TeamContract.TeamEntry.COLUMN_TEAM_GOALS_ALLOWED, 6);
//        values.put(TeamContract.TeamEntry.COLUMN_TEAM_WINS, 7);
//        values.put(TeamContract.TeamEntry.COLUMN_TEAM_DRAWS, 1);
//        values.put(TeamContract.TeamEntry.COLUMN_TEAM_LOSSES, 0);
//
//        getContentResolver().insert(TeamContract.TeamEntry.CONTENT_URI, values);
//    }
//
//    private void deleteAllTeams() {
//        getContentResolver().delete(TeamContract.TeamEntry.CONTENT_URI, null, null);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_team_selection, menu);
//        return true;
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_insert_dummy_data:
//                insertDummyData();
//                return true;
//            case R.id.action_delete_all_entries:
//                deleteAllTeams();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
//        String[] projections = {TeamContract.TeamEntry._ID, TeamContract.TeamEntry.COLUMN_TEAM_NAME};
//        return new CursorLoader(this, TeamContract.TeamEntry.CONTENT_URI, projections, null, null, null);
//    }
//
//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        mCursorAdapter.swapCursor(data);
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//        mCursorAdapter.swapCursor(null);
//    }

        Button newPlayerButton = rootView.findViewById(R.id.newPlayerButton);
        newPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),NewPlayerActivity.class);
                startActivity(i);
            }
        });
        return rootView;
    }
}

