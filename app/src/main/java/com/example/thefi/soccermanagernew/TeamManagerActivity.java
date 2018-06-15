package com.example.thefi.soccermanagernew;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.widget.TextView;

import com.example.thefi.soccermanagernew.data.TeamContract.TeamEntry;

public class TeamManagerActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private static final int MANAGER_LOADER = 4;
    private Uri currentTeamUri;
    private Intent intent;
    private String mTeamManagerTeamName;
    private int mTeamManagerNumOfPlayers = 0;
    private int mTeamManagerGoalsFor;
    private int mTeamManagerGoalsAllowed;
    private int mTeamManagerWins;
    private int mTeamManagerLosses;
    private int mTeamManagerDraws;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_manager);




        intent = getIntent();
        currentTeamUri = intent.getData();

        getLoaderManager().initLoader(MANAGER_LOADER, null, this);

        TeamManagerHomeFragment.updateInfo(mTeamManagerTeamName,mTeamManagerNumOfPlayers,mTeamManagerGoalsFor,mTeamManagerGoalsAllowed,mTeamManagerWins,mTeamManagerLosses,mTeamManagerDraws);

        // View Pager and Tab Setup
        ViewPager viewPager = findViewById(R.id.teamManagerViewPager);
        TeamManagerFragmentPagerAdapter teamManagerFragmentPagerAdapter = new TeamManagerFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(teamManagerFragmentPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.teamManagerTabs);
        tabLayout.setupWithViewPager(viewPager);




        //
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args){
        return new CursorLoader(
                this,
                currentTeamUri,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        if (data.moveToFirst()) {
            mTeamManagerTeamName = data.getString(data.getColumnIndex(TeamEntry.COLUMN_TEAM_NAME));
            mTeamManagerGoalsFor = data.getInt(data.getColumnIndex(TeamEntry.COLUMN_TEAM_GOALS_FOR));
            mTeamManagerGoalsAllowed = data.getInt(data.getColumnIndex(TeamEntry.COLUMN_TEAM_GOALS_ALLOWED));
            mTeamManagerWins = data.getInt(data.getColumnIndex(TeamEntry.COLUMN_TEAM_WINS));
            mTeamManagerLosses = data.getInt(data.getColumnIndex(TeamEntry.COLUMN_TEAM_LOSSES));
            mTeamManagerDraws = data.getInt(data.getColumnIndex(TeamEntry.COLUMN_TEAM_DRAWS));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("teamManagerTeamName", mTeamManagerTeamName);
        outState.putInt("teamManagerNumOfPlayers", mTeamManagerNumOfPlayers);
        outState.putInt("teamManagerGoalsFor", mTeamManagerGoalsFor);
        outState.putInt("teamManagerGoalsAllowed", mTeamManagerGoalsAllowed);
        outState.putInt("teamManagerWins", mTeamManagerWins);
        outState.putInt("teamManagerLosses", mTeamManagerLosses);
        outState.putInt("teamManagerDraws", mTeamManagerDraws);
    }

}
