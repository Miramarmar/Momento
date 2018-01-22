package com.example.kyle.reminder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kyle on 05/09/16.
 * <p>
 * SQLite database for storing notes/alerts
 */
public class ReminderDataHelper extends SQLiteOpenHelper {

  private static final String DATABASE_NAME = "reminderData.db";
  private static final int DATABASE_VERSION = 1;
  private static final String DB_TABLE_NAME = "reminders";

  public static final String USER_TABLE_NAME = "user";

  private static final String DB_COLUMN_ID = "_id";
  private static final String DB_COLUMN_TYPE = "type";
  private static final String DB_COLUMN_TITLE = "title";
  private static final String DB_COLUMN_CONTENT = "content";
  private static final String DB_COLUMN_CATEG = "categ";
  private static final String DB_COLUMN_TIME = "time";
  private static final String DB_COLUMN_FREQUENCY = "frequency";

  //Attributs de la table user
  public static final String USER_ID = "id";
  public static final String USER_NAME = "name";
  public static final String USER_PWD = "pwd";

  //requête de création de la table user
  public static final String USER_TABLE_CREATE =
          "CREATE TABLE " + USER_TABLE_NAME + " (" +
                  USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                  USER_NAME + " TEXT, " +
                  USER_PWD + " TEXT); " ;


  public static final String USER_TABLE_DROP = "DROP TABLE IF EXISTS " + USER_TABLE_NAME + ";";

  public ReminderDataHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + DB_TABLE_NAME + "(" +
            DB_COLUMN_ID + " INTEGER PRIMARY KEY, " +
            DB_COLUMN_TYPE + " TEXT, " +
            DB_COLUMN_TITLE + " TEXT, " +
            DB_COLUMN_CONTENT + " TEXT, " +
            DB_COLUMN_CATEG + " TEXT, "+
            DB_COLUMN_FREQUENCY + " TEXT, " +
            DB_COLUMN_TIME + " LONG)"
    );

    db.execSQL(USER_TABLE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
    db.execSQL(USER_TABLE_DROP);
    onCreate(db);
  }

}
