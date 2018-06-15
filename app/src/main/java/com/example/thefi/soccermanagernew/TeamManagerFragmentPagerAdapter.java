package com.example.thefi.soccermanagernew;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TeamManagerFragmentPagerAdapter extends FragmentPagerAdapter {

    public TeamManagerFragmentPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int pos){
        if (pos == 0)
            return new TeamManagerHomeFragment();
        else if (pos == 1)
            return new TeamManagerLineupFragment();
        else if (pos == 2)
            return new TeamManagerLiveFragment();
        else if (pos == 3)
            return new TeamManagerRosterFragment();
        else
            return new TeamManagerStatsFragment();
    }

    @Override
    public CharSequence getPageTitle(int pos){
        if(pos==0)
            return "Home";
        else if(pos==1)
            return "Lineup";
        else if (pos == 2)
            return "Live";
        else if (pos == 3)
            return "Roster";
        else
            return "Stats";
    }

    @Override
    public int getCount(){
        return 5;
    }
}
