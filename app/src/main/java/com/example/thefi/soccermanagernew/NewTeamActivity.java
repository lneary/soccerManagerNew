package com.example.thefi.soccermanagernew;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thefi.soccermanagernew.data.TeamContract.TeamEntry;


public class NewTeamActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{


    private static final int EDIT_TEAM_LOADER = 2;
    private Uri currentTeamUri;
    private Intent intent;
    private EditText mNameEditText;
    private boolean mTeamHasChanged = false;
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch (View v, MotionEvent event){
            mTeamHasChanged = true;
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_team);

        intent = getIntent();
        currentTeamUri = intent.getData();

        if (currentTeamUri == null){
            setTitle("Add a Team");
            invalidateOptionsMenu();
        }
        else {
            setTitle("Edit a Team");
            getLoaderManager().initLoader(EDIT_TEAM_LOADER, null, this);
        }

        mNameEditText = (EditText) findViewById(R.id.edit_team_name);
        mNameEditText.setOnTouchListener(touchListener);


    }

    private void saveTeam(){

        long rowId;

        String nameString = mNameEditText.getText().toString().trim();

        if (currentTeamUri == null && TextUtils.isEmpty(nameString))
            return;



        ContentValues values = new ContentValues();
        values.put(TeamEntry.COLUMN_TEAM_NAME, nameString);
        values.put(TeamEntry.COLUMN_TEAM_GOALS_FOR, 0);
        values.put(TeamEntry.COLUMN_TEAM_GOALS_ALLOWED, 0);
        values.put(TeamEntry.COLUMN_TEAM_WINS, 0);
        values.put(TeamEntry.COLUMN_TEAM_DRAWS, 0);
        values.put(TeamEntry.COLUMN_TEAM_LOSSES, 0);


        if (currentTeamUri == null){
            rowId = ContentUris.parseId(getContentResolver().insert(TeamEntry.CONTENT_URI, values));

            if (rowId == -1) {
                Toast.makeText(this, "Error Saving Team", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Team Saved" , Toast.LENGTH_SHORT).show();
            }

        } else {
            rowId = getContentResolver().update(currentTeamUri, values, null, null);

            //Toast to show whether the Updation in database is sucessful or not
            if (rowId == -1) {
                Toast.makeText(this, "Error Updating Team", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Team Saved", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        super.onPrepareOptionsMenu(menu);
        if (currentTeamUri == null){
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.action_save:
                saveTeam();
                finish();
                return true;
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
        if (data.moveToFirst()){
            String name = data.getString(data.getColumnIndex(TeamEntry.COLUMN_TEAM_NAME));
            mNameEditText.setText(name);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){
        mNameEditText.setText("");
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete this Team?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteTeam();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteTeam() {
        if (currentTeamUri != null) {
            getContentResolver().delete(currentTeamUri, null, null);
            Toast.makeText(this, "Team Deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error Deleting Team", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
