package com.example.thefi.soccermanagernew.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class PlayerContract {
    private PlayerContract(){}

    public static final String CONTENT_AUTHORITY = "com.example.thefi.soccermanagernew";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PLAYERS = "players";

    public static final class PlayerEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PLAYERS);

        public static final String TABLE_NAME = "players";

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLAYERS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PLAYERS;

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_PLAYER_NAME = "playerName";
        public static final String COLUMN_PLAYER_TEAM_NAME = "teamName";
        public static final String COLUMN_PLAYER_JERSEY_NUMBER = "jerseyNumber";
        public static final String COLUMN_PLAYER_MINUTES_PLAYED = "minutesPlayed";
        public static final String COLUMN_PLAYER_GAMES_PLAYED = "gamesPlayed";
        public static final String COLUMN_PLAYER_GOALS = "goals";
        public static final String COLUMN_PLAYER_ASSISTS = "assists";
        public static final String COLUMN_PLAYER_SAVES = "saves";
        public static final String COLUMN_PLAYER_YELLOW_CARDS = "yellowCards";
        public static final String COLUMN_PLAYER_RED_CARDS = "redCards";





    }
}
