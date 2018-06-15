package com.example.thefi.soccermanagernew;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.thefi.soccermanagernew.data.TeamContract.TeamEntry;

public class TeamCursorAdapter extends CursorAdapter {

    public TeamCursorAdapter(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor c, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.list_team_item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor c){

        TextView name_textvw = (TextView) view.findViewById(R.id.name);

        String name = c.getString(c.getColumnIndex(TeamEntry.COLUMN_TEAM_NAME));

        name_textvw.setText(name);
    }
}
