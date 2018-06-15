package com.example.thefi.soccermanagernew.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.thefi.soccermanagernew.data.PlayerContract.PlayerEntry;

public class PlayerDbHelper extends SQLiteOpenHelper{
    public final static String LOG_TAG = TeamDbHelper.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "players.db";

    public PlayerDbHelper(Context context){super(context,DATABASE_NAME,null,DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db){
        final String SQL_CREATE_TEAMS_TABLE = "CREATE TABLE " + PlayerEntry.TABLE_NAME + " ("
                + PlayerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PlayerEntry.COLUMN_PLAYER_NAME + " TEXT NOT NULL, "
                + PlayerEntry.COLUMN_PLAYER_TEAM_NAME + " TEXT NOT NULL, "
                + PlayerEntry.COLUMN_PLAYER_JERSEY_NUMBER + " INTEGER NOT NULL DEFAULT 0, "
                + PlayerEntry.COLUMN_PLAYER_MINUTES_PLAYED + " REAL NOT NULL DEFAULT 0, "
                + PlayerEntry.COLUMN_PLAYER_GAMES_PLAYED + " INTEGER NOT NULL DEFAULT 0, "
                + PlayerEntry.COLUMN_PLAYER_GOALS + " INTEGER NOT NULL DEFAULT 0, "
                + PlayerEntry.COLUMN_PLAYER_ASSISTS + " INTEGER NOT NULL DEFAULT 0, "
                + PlayerEntry.COLUMN_PLAYER_SAVES + " INTEGER NOT NULL DEFAULT 0, "
                + PlayerEntry.COLUMN_PLAYER_YELLOW_CARDS + " INTEGER NOT NULL DEFAULT 0, "
                + PlayerEntry.COLUMN_PLAYER_RED_CARDS + " INTEGER NOT NULL DEFAULT 0"
                + ");";

        db.execSQL(SQL_CREATE_TEAMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }
}
