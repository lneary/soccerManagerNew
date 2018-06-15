package com.example.thefi.soccermanagernew.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.example.thefi.soccermanagernew.data.TeamContract.TeamEntry;

public class TeamProvider extends ContentProvider {

    private TeamDbHelper teamDbHelper;

    private static final int TEAMS = 100;
    private static final int TEAM_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        sUriMatcher.addURI(TeamContract.CONTENT_AUTHORITY, TeamContract.PATH_TEAMS, TEAMS);

        sUriMatcher.addURI(TeamContract.CONTENT_AUTHORITY, TeamContract.PATH_TEAMS + "/#", TEAM_ID);

    }

    public static final String LOG_TAG = TeamProvider.class.getSimpleName();

    @Override
    public boolean onCreate(){
        teamDbHelper = new TeamDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){

        SQLiteDatabase db = teamDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);

        switch (match){
            case TEAMS:
                cursor = db.query(TeamEntry.TABLE_NAME, projection, selection,selectionArgs,null,null,sortOrder);
                break;
            case TEAM_ID:
                selection = TeamEntry._ID+"=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(TeamEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues){
        final int match = sUriMatcher.match(uri);
        switch (match){
            case TEAMS:
                return insertTeam(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for "+ uri);


        }
    }

    private Uri insertTeam(Uri uri, ContentValues contentValues){
        String name = contentValues.getAsString(TeamEntry.COLUMN_TEAM_NAME);
        if (name == null)
            throw new IllegalArgumentException("Team requires a name");
        SQLiteDatabase db = teamDbHelper.getWritableDatabase();
        long id = db.insert(TeamEntry.TABLE_NAME, null, contentValues);
        getContext().getContentResolver().notifyChange(uri,null);
        return ContentUris.withAppendedId(uri,id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs){
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case TEAMS:
                return updateTeam(uri,contentValues,selection,selectionArgs);
            case TEAM_ID:
                selection = TeamEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                return updateTeam(uri,contentValues,selection,selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);

        }
    }

    private int updateTeam(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        if (values.containsKey(TeamEntry.COLUMN_TEAM_NAME)){
            String name = values.getAsString(TeamEntry.COLUMN_TEAM_NAME);
            if (name == null)
                throw new IllegalArgumentException("Team requires a name");
        }

        if (values.size()==0)
            return 0;

        SQLiteDatabase db = teamDbHelper.getWritableDatabase();
        int rowsUpdated = db.update(TeamEntry.TABLE_NAME,values,selection,selectionArgs);
        if (rowsUpdated != 0)
            getContext().getContentResolver().notifyChange(uri,null);
        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        SQLiteDatabase db = teamDbHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;
        switch (match) {
            case TEAMS:
                rowsDeleted = db.delete(TeamEntry.TABLE_NAME,selection,selectionArgs);
                break;
            case TEAM_ID:
                selection = TeamEntry._ID+"=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                rowsDeleted = db.delete(TeamEntry.TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }
        if (rowsDeleted != 0)
            getContext().getContentResolver().notifyChange(uri,null);
        return rowsDeleted;
    }

    @Override public String getType(Uri uri){
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case TEAMS:
                return TeamEntry.CONTENT_LIST_TYPE;
            case TEAM_ID:
                return TeamEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }
}
