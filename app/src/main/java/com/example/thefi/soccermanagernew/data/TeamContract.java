package com.example.thefi.soccermanagernew.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class TeamContract  {

    private TeamContract(){}

    public static final String CONTENT_AUTHORITY = "com.example.thefi.soccermanagernew";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_TEAMS = "teams";

    public static final class TeamEntry implements BaseColumns {

        public static final String TABLE_NAME = "teams";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, TABLE_NAME);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TEAMS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_TEAMS;

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_TEAM_NAME = "name";
        public static final String COLUMN_TEAM_GOALS_FOR = "goalsFor";
        public static final String COLUMN_TEAM_GOALS_ALLOWED = "goalsAllowed";
        public static final String COLUMN_TEAM_WINS = "wins";
        public static final String COLUMN_TEAM_LOSSES = "losses";
        public static final String COLUMN_TEAM_DRAWS = "draws";




    }
}
