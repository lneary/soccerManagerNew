package com.example.thefi.soccermanagernew;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamManagerHomeFragment extends Fragment{

    private boolean firstRun = true;

    private static String mTeamManagerTeamName;
    private static int mTeamManagerNumOfPlayers = 0;
    private static int mTeamManagerGoalsFor;
    private static int mTeamManagerGoalsAllowed;
    private static int mTeamManagerWins;
    private static int mTeamManagerLosses;
    private static int mTeamManagerDraws;
    private TextView mTeamManagerHomeName;
    private TextView mTeamManagerHomeNumOfPlayers;
    private TextView mTeamManagerHomeRecord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_team_manager_home, container, false);
        mTeamManagerHomeName = rootView.findViewById(R.id.teamManagerHomeName);
        mTeamManagerHomeName.setText(mTeamManagerTeamName);
        mTeamManagerHomeNumOfPlayers = rootView.findViewById(R.id.teamManagerHomePlayers);
        mTeamManagerHomeNumOfPlayers.setText(mTeamManagerNumOfPlayers+" Players");
        mTeamManagerHomeRecord = rootView.findViewById(R.id.teamManagerHomeRecord);
        mTeamManagerHomeRecord.setText(mTeamManagerWins+" W "+mTeamManagerLosses+" L "+mTeamManagerDraws+" D ");
        return rootView;
    }

    public static void updateInfo(String teamName, int numPlayer, int goalsFor,int goalsAllowed, int wins, int losses, int draws){
        mTeamManagerTeamName = teamName;
        mTeamManagerNumOfPlayers = numPlayer;
        mTeamManagerGoalsFor = goalsFor;
        mTeamManagerGoalsAllowed = goalsAllowed;
        mTeamManagerWins = wins;
        mTeamManagerLosses = losses;
        mTeamManagerDraws = draws;
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

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mTeamManagerTeamName = savedInstanceState.getString("teamManagerTeamName");
//        mTeamManagerNumOfPlayers = savedInstanceState.getInt("teamManagerNumOfPlayers");
//        mTeamManagerGoalsFor = savedInstanceState.getInt("teamManagerGoalsFor");
//        mTeamManagerGoalsAllowed = savedInstanceState.getInt("teamManagerGoalsAllowed");
//        mTeamManagerWins = savedInstanceState.getInt("teamManagerWins");
//        mTeamManagerLosses = savedInstanceState.getInt("teamManagerLosses");
//        mTeamManagerDraws = savedInstanceState.getInt("teamManagerDraws");
//    }

}
