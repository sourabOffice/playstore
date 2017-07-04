package com.ggktech.playstore.playstore.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.ggktech.playstore.playstore.models.Item;
import com.ggktech.playstore.playstore.models.User;

import java.util.UUID;

/**
 * Created by Sourabh.Wasnik on 6/28/2017.
 */

public class DataBaseAdapter {

    // Database fields

    private final Context context;
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public DataBaseAdapter(Context context) {
        this.context = context;

    }

    public DataBaseAdapter open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
//        database.close();
    }

    public Cursor fetchAllLoginTableData(){
        return database.query(DBHelper.DATABASE_LOGIN_TABLE, new String[]{"USERNAME",
                "PASSWORD" },null,null,null,null,null);
    }

    public Cursor fetchAllItemTableData(){
        return database.query(DBHelper.DATABASE_ITEM_TABLE, new String[]{"TITLE",
                "DESCRIPTION","RATING","URI","CHECKBOX" },null,null,null,null,null);
    }

    public void deleteTable(String tablename){
        database.execSQL("drop table if exists "+tablename+';');
    }

    public void createIndividualTable(String query){
        database.execSQL(query);
    }

    public void insertEntryIntoLoginTable(User user) {
        ContentValues values = new ContentValues();
        values.put("USERNAME", user.getEmail());
        values.put("PASSWORD", user.getPassword());

        database.insert(DBHelper.DATABASE_LOGIN_TABLE, null, values);

    }

    public void insertEntryIntoItemTable(Item item){
        ContentValues values = new ContentValues();

        values.put("TITLE",item.getTitle());
        values.put("DESCRIPTION",item.getDescription());
        values.put("RATING",item.getmItemRating());
        values.put("URI",item.getmImageUri().toString());

        values.put("CHECKBOX",(item.isSolved()) ? 1 : 0);

        database.insert(DBHelper.DATABASE_ITEM_TABLE,null,values);
    }

    public ContentValues createContentValues(String category, String summary,String description){
       ContentValues values = new ContentValues();
        return values;
    }

    public String getSinlgeEntryLOGIN(String mEmail) {
        Cursor cursor = database.query(DBHelper.DATABASE_LOGIN_TABLE, null, " USERNAME=?", new String[]{mEmail}, null, null, null);
        if (cursor.getCount() < 1) // mEmail does Not Exist
        {
            cursor.close();
            return "DOES NOT EXIST";
        }
        cursor.moveToFirst();
        String password = cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }

    public Item getSingleEntryITEM(String secretTitle){
        Item item = new Item();

        Cursor cursor = database.query(DBHelper.DATABASE_ITEM_TABLE, null, " TITLE=?", new String[]{secretTitle},
                null, null, null);
        if (cursor.getCount() < 1) {// item does Not Exist
            cursor.close();
            return null;
        }
        cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndex("TITLE"));
        String description = cursor.getString(cursor.getColumnIndex("DESCRIPTION"));
        String rating = cursor.getString(cursor.getColumnIndex("RATING"));
        String imageUri = cursor.getString(cursor.getColumnIndex("URI"));
        int checkbox = cursor.getInt(cursor.getColumnIndex("CHECKBOX"));

        item.setTitle(title);
        item.setDescription(description);
        item.setmItemRating(rating);
        item.setmImageUri(Uri.parse(imageUri));


        boolean convertedBoolean;
        if (checkbox == 1)
            convertedBoolean = true;
        else
            convertedBoolean = false;


        item.setSolved(convertedBoolean);

        cursor.close();

        return item;
    }

    public void updateEntry(String userName, String password) {
        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD", password);

        String where = "USERNAME = ?";
        database.update(DBHelper.DATABASE_LOGIN_TABLE, updatedValues, where, new String[]{userName});
    }

    public int deleteEntryLogin(String UserName) {
        //String id=String.valueOf(ID);
        String where = "USERNAME=?";
        int numberOFEntriesDeleted = database.delete(DBHelper.DATABASE_LOGIN_TABLE, where, new String[]{UserName});
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public int deleteEntryItem(String title) {
        //String id=String.valueOf(ID);
        String where = "TITLE=?";
        int numberOFEntriesDeleted = database.delete(DBHelper.DATABASE_ITEM_TABLE, where, new String[]{title});
        // Toast.makeText(context, "Number fo Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }

    public SQLiteDatabase getDatabaseInstance() {
        return database;
    }

    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.

}
