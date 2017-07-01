package com.ggktech.playstore.playstore.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sourabh.Wasnik on 6/28/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "playstore.db";
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_LOGIN_TABLE ="LOGIN";
    public static final String DATABASE_ITEM_TABLE = "ITEM";

    // SQL Statement to create a new database.
    private static final String DATABASE_LOGIN_TABLE_QUERY = "create table "+ DATABASE_LOGIN_TABLE +
            "( " +"ID"+" integer primary key autoincrement,"+ "USERNAME  text,PASSWORD text); ";



    private static final String DATABASE_ITEM_TABLE_QUERY = "create table "+DATABASE_ITEM_TABLE +
            "( "+ "_id"+" integer primary key autoincrement,"+ "TITLE text,"+
            "DESCRIPTION text); ";
//            "DESCRIPTION text,"+"Amount text,"+"Banks text); ";

//    private static final String DATABASE_CREATECUS =
//            "create table Customer (_id integer primary key autoincrement, "
//                    + "BusinessName text not null,"+"Address text not null,"+"ContactPerson text not null,"+"PhoneNumber text not null,);";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        _db.execSQL(DATABASE_LOGIN_TABLE_QUERY);
        _db.execSQL(DATABASE_ITEM_TABLE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        // Log the version upgrade.
        Log.w(DBHelper.class.getName(), "Upgrading from version " +_oldVersion + " to " +_newVersion + ", which will destroy all old data");


        // Upgrade the existing database to conform to the new version. Multiple
        // previous versions can be handled by comparing _oldVersion and _newVersion
        // values.
        // The simplest case is to drop the old table and create a new one.
        _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_LOGIN_TABLE_QUERY);
        _db.execSQL("DROP TABLE IF EXISTS " + DATABASE_ITEM_TABLE_QUERY);


        onCreate(_db);
    }

    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DATABASE_NAME);
    }

}
