package com.example.piotrek.todolistv3.model;
import android.database.MatrixCursor;
import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.ArrayList;


public class DbHelper extends SQLiteOpenHelper {
    private static final String DEFAULT_LIST = "defaultList";
    private static final int DB_VER = 1;
    private static final String DB_NAME = "databasetodolist";
    private static final String DB_TASK_TABLE = "toDoTaskTable";
    private static final String DB_LIST_TABLE = "toDoListTable";
    private static final String TASK_ID = "idTask";
    private static final String TASK_ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final String TASK_ID_CATEGORY_ID = "taskCategory";
    private static final String TASK_ID_CATEGORY_ID_OPTIONS = "INTEGER";
    private static final String TASK_TITLE = "description";
    private static final String TITLE_OPTIONS = "TEXT NOT NULL";
    private static final String CATEGORY_ID = "idCategory";
    private static final String CATEGORY_ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final String CATEGORY_TILE = "category";
    private static final String CATEGORY_TITLE_OPTIONS = "TEXT NOT NULL";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createListTable = "CREATE TABLE " + DB_LIST_TABLE
                + "( "
                + CATEGORY_ID
                + " "
                + CATEGORY_ID_OPTIONS
                + ", "
                + CATEGORY_TILE
                + " "
                + CATEGORY_TITLE_OPTIONS +
                ");";
        Log.d("DbHelper", createListTable);

        String createTaskTable = " CREATE TABLE " + DB_TASK_TABLE
                + "( "
                + TASK_ID
                + " "
                + TASK_ID_OPTIONS
                + ", "
                + TASK_TITLE
                + " "
                + TITLE_OPTIONS
                + ", "
                + TASK_ID_CATEGORY_ID
                + " "
                + TASK_ID_CATEGORY_ID_OPTIONS
                + ", FOREIGN KEY (" +
                TASK_ID_CATEGORY_ID
                + ") REFERENCES "
                + DB_LIST_TABLE
                + "("
                + CATEGORY_ID
                + ")" + " ON DELETE CASCADE "
                + ");";
        Log.d("DbHelper", createTaskTable);

        db.execSQL(createListTable);
        db.execSQL(createTaskTable);
        onConfigure(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + DB_TASK_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + DB_LIST_TABLE);
            onCreate(db);

            Log.d("onupgrade", "upgrade");
        }

        onCreate(db);

    }

    public void insertNewTask(Task task) {

        SQLiteDatabase db = getSqLiteDatabase();
        ContentValues values = new ContentValues();
        values.put(TASK_TITLE, task.getNameTask());
        values.put(TASK_ID_CATEGORY_ID, task.getCategoryId());
        db.insertWithOnConflict(DB_TASK_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void insertNewCategory(String table) {
        SQLiteDatabase db = getSqLiteDatabase();
        ContentValues values = new ContentValues();
        values.put(CATEGORY_TILE, table);
        db.insertWithOnConflict(DB_LIST_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();

    }

    public void deleteTask(Task task) {
        String idTask = String.valueOf(task.getIdTask());
        SQLiteDatabase db = getSqLiteDatabase();
        db.delete(DB_TASK_TABLE, TASK_ID + " = ?", new String[]{idTask});
        db.close();
    }


    public void deleteCategory(String categoryId) {
        SQLiteDatabase db = getSqLiteDatabase();
        db.delete(DB_LIST_TABLE, CATEGORY_ID + " = ?", new String[]{categoryId});

        db.close();
    }

    private SQLiteDatabase getSqLiteDatabase() {
        return this.getWritableDatabase();
    }


    public ArrayList<Task> getTaskList(int idCategory) {
        String categoryId = String.valueOf(idCategory);
        String whereClause = "taskcategory = ?";
        String[] whereArgs = new String[]{categoryId};
        ArrayList<Task> tasksList = new ArrayList<>();
        String[] columns = {TASK_ID, TASK_TITLE, TASK_ID_CATEGORY_ID};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TASK_TABLE, columns, whereClause, whereArgs, null, null, null);
        if (cursor != null && cursor.moveToFirst())
            do {
                int id = cursor.getColumnIndex(TASK_ID);
                int description = cursor.getColumnIndex(TASK_TITLE);
                tasksList.add(new Task(cursor.getInt(id), cursor.getString(description)));
            } while (cursor.moveToNext());
        cursor.close();
        db.close();

        return tasksList;
    }

    public ArrayList<Category> getCategoryList() {
        ArrayList<Category> listOfList = new ArrayList<>();
        String[] columns = {CATEGORY_ID, CATEGORY_TILE};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_LIST_TABLE, columns, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst())
            do {

                int id = cursor.getColumnIndex(CATEGORY_ID);
                int index = cursor.getColumnIndex(CATEGORY_TILE);
                listOfList.add(new Category(cursor.getInt(id), cursor.getString(index)));
            } while (cursor.moveToNext());
        cursor.close();
        db.close();

        return listOfList;

    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = getSqLiteDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }

}