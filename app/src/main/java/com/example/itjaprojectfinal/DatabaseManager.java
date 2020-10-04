package com.example.itjaprojectfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.itjaprojectfinal.pojo.User;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";
    // User table name
    private static final String TABLE_USER = "user";
    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_FIRST = "user_firstname";
    private static final String COLUMN_LAST = "user_lastname";
    private static final String COLUMN_EMAIL = "user_email";
    private static final String COLUMN_PASSWORD = "user_password";
    private static final String COLUMN_MOBILE = "user_mobile";
    private static final String COLUMN_GENDER = "user_gender";
    private static final String COLUMN_MAIN_ACCOUNT = "user_main_account";
    private static final String COLUMN_SAVINGS_ACCOUNT = "user_savings_account";
    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FIRST + " TEXT,"
            + COLUMN_LAST + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_PASSWORD + " TEXT," + COLUMN_MOBILE + " TEXT," + COLUMN_GENDER + " TEXT," + COLUMN_MAIN_ACCOUNT + " INTEGER," + COLUMN_SAVINGS_ACCOUNT + " INTEGER" +  ")";
    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    /**
     * Constructor
     */
    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseManager(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST, user.getFirstName());
        values.put(COLUMN_LAST, user.getLastName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_MOBILE, user.getMobile());
        values.put(COLUMN_GENDER, user.getGender());
        values.put(COLUMN_MAIN_ACCOUNT, user.getMainAccount());
        values.put(COLUMN_SAVINGS_ACCOUNT,user.getSavingsAccount());
        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_FIRST,
                COLUMN_LAST,
                COLUMN_EMAIL,
                COLUMN_PASSWORD,
                COLUMN_MOBILE,
                COLUMN_GENDER,
                COLUMN_MAIN_ACCOUNT,
                COLUMN_SAVINGS_ACCOUNT

        };
        // sorting orders
        String sortOrder =
                COLUMN_FIRST + " ASC";
        List<User> userList = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the user table

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST)));
                user.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_LAST)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                user.setMobile(cursor.getString(cursor.getColumnIndex(COLUMN_MOBILE)));
                user.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
                user.setMainAccount(cursor.getInt(cursor.getColumnIndex(COLUMN_MAIN_ACCOUNT)));
                user.setSavingsAccount(cursor.getInt(cursor.getColumnIndex(COLUMN_SAVINGS_ACCOUNT)));
                // Adding user record to list
                userList.add(user);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return userList;
    }

    public User getUser(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE " + COLUMN_EMAIL + "=?", new String[]{email});
        cursor.moveToFirst();

        User user = new User();
        user.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_USER_ID)));
        user.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRST)));
        user.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_LAST)));
        user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
        user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
        user.setMobile(cursor.getString(cursor.getColumnIndex(COLUMN_MOBILE)));
        user.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)));
        user.setMainAccount(cursor.getInt(cursor.getColumnIndex(COLUMN_MAIN_ACCOUNT)));
        user.setSavingsAccount(cursor.getInt(cursor.getColumnIndex(COLUMN_SAVINGS_ACCOUNT)));

        cursor.close();
        db.close();

        return user;
    }




    /**
     * This method to update user record
     */
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST, user.getFirstName());
        values.put(COLUMN_LAST, user.getLastName());
        values.put(COLUMN_EMAIL, user.getEmail());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_MOBILE, user.getMobile());
        values.put(COLUMN_GENDER, user.getGender());
        values.put(COLUMN_MAIN_ACCOUNT,user.getMainAccount());
        values.put(COLUMN_SAVINGS_ACCOUNT,user.getSavingsAccount());
        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
    /**
     * This method is to delete user record
     */
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        // delete user record by id
        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
    /**
     * This method to check user exist or not
     */
    public boolean checkUser(String email) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_EMAIL + " = ?";
        // selection argument
        String[] selectionArgs = {email};
        // query user table with condition

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
}
