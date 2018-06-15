package com.example.thefi.soccermanagernew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

import com.example.thefi.soccermanagernew.data.PlayerContract.PlayerEntry;


public class NewPlayerActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{


    private static final int EDIT_PLAYER_LOADER = 2;
    private Uri currentPlayerUri;
    private Intent intent;
    private EditText mPlayerNameEditText;
    private EditText mTeamNameEditText;
    private EditText mJerseyNumberEditText;
    private boolean mPlayerHasChanged = false;
    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch (View v, MotionEvent event){
            mPlayerHasChanged = true;
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);

        intent = getIntent();
        currentPlayerUri = intent.getData();

        if (currentPlayerUri == null){
            setTitle("Add a Player");
            invalidateOptionsMenu();
        }
        else {
            setTitle("Edit a Player");
            getLoaderManager().initLoader(EDIT_PLAYER_LOADER, null, this);
        }

        mPlayerNameEditText = (EditText) findViewById(R.id.edit_player_name);

        mTeamNameEditText = (EditText) findViewById(R.id.edit_player_team_name);

        mJerseyNumberEditText = (EditText) findViewById(R.id.edit_player_jersey_number);



    }

    private void savePlayer(){

        long rowId;
        int playerJerseyNumber = 0;

        String playerNameString = mPlayerNameEditText.getText().toString().trim();
        String playerTeamNameString = mTeamNameEditText.getText().toString().trim();
        String num = mJerseyNumberEditText.getText().toString().trim();
        if (!num.equals(""))
            playerJerseyNumber = Integer.parseInt(num);

        if (currentPlayerUri == null)
            return;



        ContentValues values = new ContentValues();
        values.put(PlayerEntry.COLUMN_PLAYER_NAME, playerNameString);
        values.put(PlayerEntry.COLUMN_PLAYER_TEAM_NAME, playerTeamNameString);
        values.put(PlayerEntry.COLUMN_PLAYER_JERSEY_NUMBER, playerJerseyNumber);


        if (currentPlayerUri == null){
            rowId = ContentUris.parseId(getContentResolver().insert(PlayerEntry.CONTENT_URI, values));

            if (rowId == -1) {
                Toast.makeText(this, "Error Saving Player", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Player Saved" , Toast.LENGTH_SHORT).show();
            }

        } else {
            rowId = getContentResolver().update(currentPlayerUri, values, null, null);

            //Toast to show whether the Updation in database is sucessful or not
            if (rowId == -1) {
                Toast.makeText(this, "Error Updating Player", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Player Updated", Toast.LENGTH_SHORT).show();
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
        if (currentPlayerUri == null){
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.action_save:
                savePlayer();
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
                currentPlayerUri,
                null,
                null,
                null,
                null
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data){
        if (data.moveToFirst()){
            String playerName = data.getString(data.getColumnIndex(PlayerEntry.COLUMN_PLAYER_TEAM_NAME));
            mPlayerNameEditText.setText(playerName);
            String playerTeamName = data.getString(data.getColumnIndex(PlayerEntry.COLUMN_PLAYER_TEAM_NAME));
            mTeamNameEditText.setText(playerTeamName);
            int playerJerseyNumber = data.getInt(data.getColumnIndex(PlayerEntry.COLUMN_PLAYER_JERSEY_NUMBER));
            mJerseyNumberEditText.setText(playerJerseyNumber+"");
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader){
        mPlayerNameEditText.setText("");
        mTeamNameEditText.setText("");
        mJerseyNumberEditText.setText("");
    }

    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Delete this Player?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deletePlayer();
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

    private void deletePlayer() {
        if (currentPlayerUri != null) {
            getContentResolver().delete(currentPlayerUri, null, null);
            Toast.makeText(this, "Player Deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error Deleting Playing", Toast.LENGTH_SHORT).show();
        }

        finish();
    }
}
