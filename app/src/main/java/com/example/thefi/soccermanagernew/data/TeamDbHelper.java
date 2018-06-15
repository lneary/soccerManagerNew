package com.example.thefi.soccermanagernew.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.thefi.soccermanagernew.data.TeamContract.TeamEntry;

public class TeamDbHelper extends SQLiteOpenHelper{

    public final static String LOG_TAG = TeamDbHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "soccerManager.db";

    public TeamDbHelper(Context context){super(context,DATABASE_NAME,null,DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db){
        final String SQL_CREATE_TEAMS_TABLE = "CREATE TABLE " + TeamEntry.TABLE_NAME + " ("
                + TeamEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TeamEntry.COLUMN_TEAM_NAME + " TEXT NOT NULL, "
                + TeamEntry.COLUMN_TEAM_GOALS_FOR + " INTEGER NOT NULL DEFAULT 0, "
                + TeamEntry.COLUMN_TEAM_GOALS_ALLOWED + " INTEGER NOT NULL DEFAULT 0, "
                + TeamEntry.COLUMN_TEAM_WINS + " INTEGER NOT NULL DEFAULT 0, "
                + TeamEntry.COLUMN_TEAM_LOSSES + " INTEGER NOT NULL DEFAULT 0, "
                + TeamEntry.COLUMN_TEAM_DRAWS + " INTEGER NOT NULL DEFAULT 0"
                + ");";

        db.execSQL(SQL_CREATE_TEAMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
