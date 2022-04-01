package com.example.barbercornerproj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.barbercornerproj.model.CustomerModel;
import com.example.barbercornerproj.model.DataModel;
import com.example.barbercornerproj.model.StaffModel;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    // User table
    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_NAME = "userName";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_USERID = "USER" + COLUMN_ID;
    public static final String COLUMN_PASSWORD = "PASSWORD";
    public static final String COLUMN_TITLE = "TITLE";

    static class STAFF_TABLE {
        public static final String NAME = "STAFF_TABLE";
        public static final String COLUMN_ID = "ID";
        public static final String COLUMN_USER_ID = "staffId";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_BIOGRAPHY = "biography";
        public static final String COLUMN_SHIFT = "SHIFT";
        public static final String COLUMN_LOCATION = "LOCATION";
    }

    // Staff table
    /*public static final String STAFF_TABLE = "STAFF_TABLE";
    public static final String COL_ID = "ID";
    public static final String COL_uID = "staffId";
    public static final String COL_NAME = "name";
    public static final String COL_TITLE = "title";
    public static final String COL_BIO = "biography";
    public static final String COL_SHIFT = "SHIFT";
    public static final String COL_LOCATION = "LOCATION";*/

    //Customer Table
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COL_USERID = "ID";
    public static final String COL_ADDRESS = "address";
    public static final String COL_AGE = "age";

    //Booking Table
    static class BOOKING_TABLE {
        public static final String NAME = "BOOKING_TABLE";
        public static final String COLUMN_ID = "bookingId";
        public static final String COLUMN_CUSTOMER_ID = "customerId";
        public static final String COLUMN_BARBER_ID = "barberId";
        public static final String COLUMN_DATETIME = "bookingDateTime";
    }


    public DatabaseHelper(@Nullable Context context) {
        super(context, "barbershopData.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create user table user
        String userTable = "CREATE TABLE " + USER_TABLE + "(" + COLUMN_USER_NAME + " VARCHAR(500), " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME + " TEXT, " + COLUMN_TITLE + " TEXT,"+ COLUMN_USERID + " TEXT, " + COLUMN_PASSWORD + " TEXT )";
        db.execSQL(userTable);

        //Create staff table
        /*String staffTable = "CREATE TABLE " + STAFF_TABLE + "(" + COL_ID + "" +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_uID + "TEXT, " + COL_TITLE + " TEXT, " + COL_NAME + " TEXT, "  + " TEXT, " + COL_BIO + " TEXT, " + COL_SHIFT + " TEXT," + COL_LOCATION + "TEXT)" ;
        */
        String staffTable = "CREATE TABLE " + STAFF_TABLE.NAME + "(" +
                STAFF_TABLE.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STAFF_TABLE.COLUMN_USER_ID + " TEXT, " +
                STAFF_TABLE.COLUMN_TITLE + " TEXT, " +
                STAFF_TABLE.COLUMN_NAME + " TEXT, " +
                STAFF_TABLE.COLUMN_BIOGRAPHY + " TEXT, " +
                STAFF_TABLE.COLUMN_SHIFT + " TEXT, " +
                STAFF_TABLE.COLUMN_LOCATION + "TEXT" +
                ")" ;
        db.execSQL(staffTable);

        //create customer table
        String customerTable = "CREATE TABLE " + CUSTOMER_TABLE + "(" + COL_USERID +
                " TEXT, " + COL_ADDRESS + " TEXT, " + COL_AGE + " TEXT )";
        db.execSQL(customerTable);

        //Create booking table
        String bookingTable =
                "CREATE TABLE " + BOOKING_TABLE.NAME + "(" +
                BOOKING_TABLE.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                BOOKING_TABLE.COLUMN_CUSTOMER_ID + " INTEGER, " +
                BOOKING_TABLE.COLUMN_BARBER_ID + " INTEGER, " +
                BOOKING_TABLE.COLUMN_DATETIME + " DATETIME " +
                ")";
        Log.i("DATABASE", bookingTable);
        db.execSQL(bookingTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP table if exists " + USER_TABLE);
        db.execSQL("DROP TABLE if exists " + CUSTOMER_TABLE);
        db.execSQL("DROP TABLE if exists " + STAFF_TABLE.NAME);
        db.execSQL("DROP TABLE if exists " + BOOKING_TABLE.NAME);

        onCreate(db);
    }

    public boolean addUser(DataModel dataModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USER_NAME, dataModel.getUserName());
        cv.put(COLUMN_NAME, dataModel.getName());
        cv.put(COLUMN_TITLE, dataModel.getTitle());
        cv.put(COLUMN_USERID, dataModel.getUserId());
        cv.put(COLUMN_PASSWORD, dataModel.getPassword());

        long insert = db.insert(USER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        }
        return true;
    }

    public boolean addCustomerInfo(CustomerModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_USERID, model.getUserId());
        cv.put(COL_ADDRESS, model.getAddress());
        cv.put(COL_AGE, model.getAge());


        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addStaff(StaffModel staffModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STAFF_TABLE.COLUMN_ID, staffModel.getUserID());
        values.put(STAFF_TABLE.COLUMN_TITLE, staffModel.getTitle());
        values.put(STAFF_TABLE.COLUMN_LOCATION, staffModel.getLocation());
        values.put(STAFF_TABLE.COLUMN_SHIFT, staffModel.getShift());
        values.put(STAFF_TABLE.COLUMN_BIOGRAPHY, staffModel.getBio());

        long r = sqLiteDatabase.insert(STAFF_TABLE.NAME, null, values);
        if (r > 0)
            return true;
        else
            return false;

    }

    public ArrayList<StaffModel> allStaffs() {
        ArrayList<StaffModel> doctorModels = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + STAFF_TABLE.NAME;
        Cursor c = sqLiteDatabase.rawQuery(query, null);

        while (c.moveToNext()) {
            int id = c.getInt(0);
            String userid = c.getString(1);
            String name = c.getString(3);
            String title = c.getString(2);
            String location = c.getString(4);
            String shift = c.getString(5);
            String bio = c.getString(6);

            StaffModel model = new StaffModel(id, userid, name, title, location, shift, bio);
            doctorModels.add(model);
        }
        System.out.println("Doctor: " + doctorModels.size());
        return doctorModels;
    }

    public ArrayList<DataModel> retrieveAllUsers() {
        ArrayList<DataModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            // loop through the cursor and put them into the return list
            do {
                int ID = cursor.getInt(0);
                String Name = cursor.getString(1);
                String Title = cursor.getString(2);
                String UserID = cursor.getString(3);
                String Password = cursor.getString(4);

                DataModel newUser = new DataModel(ID, Name, Title, UserID, Password);
                returnList.add(newUser);
            }
            while (cursor.moveToNext());
        } else {
            // error -> don't add anything to the list
        }
        // close the cursor and db after using them
        cursor.close();
        db.close();
        return returnList;
    }

    public Cursor getUserByUserName(String userName) {
        userName = "\"" + userName + "\"";
        String query = "SELECT * FROM " + USER_TABLE + " WHERE " + COLUMN_USER_NAME + " = " + userName;
        Cursor cursor = getReadableDatabase().rawQuery(query, null);
        return cursor;
    }

    /*BOOKING TABLE*/
    public boolean addBooking(int userId, int barberId, int day, int month, int year, int hour, int minute) {
        String sDay = String.format("%02d", day);
        String sMonth = String.format("%02d", month);
        String sYear = String.format("%04d", year);
        String sHour = String.format("%02d", hour);
        String sMinute = String.format("%02d", minute);
        String dateTime = sYear + "-" + sMonth + "-" + sDay + " " + sHour + ":" + sMinute + ":00";
        dateTime = "'" + dateTime + "'";
        String query =
                "INSERT INTO " + BOOKING_TABLE.NAME + "(" +
                BOOKING_TABLE.COLUMN_CUSTOMER_ID + ", " +
                BOOKING_TABLE.COLUMN_BARBER_ID + ", " +
                BOOKING_TABLE.COLUMN_DATETIME + ")" +
                " VALUES(" + userId + ", " + barberId + ", " + dateTime + ")";
        System.out.println(query);
        getWritableDatabase().execSQL(query);
        return true;
    }

    //  For testing
    public void insertTestBarber() {
        String query = "INSERT OR IGNORE INTO " + STAFF_TABLE.NAME +
                "(" + STAFF_TABLE.COLUMN_ID + ", " + STAFF_TABLE.COLUMN_NAME + ")" +
                "VALUES(0, \"Chris Evans\"), (1, 'Chris Hemsworth') , (2, 'Paul Rudd '), (3, 'Benedict Cumberbatch')";
        getWritableDatabase().execSQL(query);
    }
}
