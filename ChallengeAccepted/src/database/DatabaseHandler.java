package database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "challengeAccepted";
 
    // Contacts table name
    private static final String TABLE= "WorkoutPlaner";
    private static final String TABLE2= "Workout";
    
    // WorkoutPlaner Table Column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    
    // Workout Table Column names
    private static final String KEY_ID2 = "id";
    private static final String KEY_REF = "challenge";  
    private static final String KEY_TASK = "task";
    private static final String KEY_REPEATS = "repeats";
    private static final String KEY_SETS = "sets";
    private static final String KEY_TIME = "time";
	
	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//Table 1 autoincrement
        String CREATE_WORKOUTPLANER_TABLE = "CREATE TABLE " + TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY autoincrement," + KEY_NAME + " TEXT," +  KEY_TIME + " TEXT)";
        db.execSQL(CREATE_WORKOUTPLANER_TABLE);
		
        //Table 2
        String CREATE_WORKOUT_TABLE = "CREATE TABLE " + TABLE2 + "("
                + KEY_ID2 + " INTEGER PRIMARY KEY autoincrement," + KEY_REF + " TEXT,"
                + KEY_TASK + " TEXT," + KEY_REPEATS + " TEXT," 
                 + KEY_SETS + " TEXT,"  
                + " FOREIGN KEY (" + KEY_REF + ") REFERENCES " + TABLE + " ("+ KEY_ID +"));";
        db.execSQL(CREATE_WORKOUT_TABLE);
        
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2);
        // Create tables again
        onCreate(db);
		
	}
	
	public void addWorkout(Workout w){
	    SQLiteDatabase db = this.getWritableDatabase();
	    
	    ContentValues values = new ContentValues();
	    values.put(KEY_REF, w.getRef()); 
	    values.put(KEY_TASK, w.getTask()); 
	    values.put(KEY_REPEATS, w.getRepeats()); 
	    values.put(KEY_SETS, w.getSets()); 
	 
	    // Inserting Row
	    db.insert(TABLE2, null, values);
	    db.close(); // Closing database connection
	}
	
	public Workout getWorkout(int id){
	    SQLiteDatabase db = this.getReadableDatabase();
	    
	    Cursor cursor = db.query(TABLE2, new String[] { KEY_ID,
	    		KEY_REF, KEY_TASK, KEY_REPEATS, KEY_SETS }, KEY_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
	    if (cursor != null)
	        cursor.moveToFirst();
//	    (int repeats, String task,
//				int sets, String time)
	    Workout w = new Workout(
	    		 Integer.parseInt(cursor.getString(3)),cursor.getString(2), Integer.parseInt(cursor.getString(4)));
	    return w;
	}
	
	 public List<Workout> getAllWorkouts() {
		    List<Workout> l = new ArrayList<Workout>();
		    // Select All Query
		    String selectQuery = "SELECT  * FROM " + TABLE2;
		 
		    SQLiteDatabase db = this.getWritableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);
		 
		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
		        do {
		        	Workout w = new Workout();
		            w.setId(Integer.parseInt(cursor.getString(0)));
		            w.setRef(cursor.getString(1));
		            w.setTask(cursor.getString(2));
		            w.setRepeats(Integer.parseInt(cursor.getString(3)));
		            w.setSets(Integer.parseInt(cursor.getString(4)));
		            l.add(w);
		        } while (cursor.moveToNext());
		    }
		 
		    return l;
	 }
	 
	 public int updateWorkout(Workout w) {
		    SQLiteDatabase db = this.getWritableDatabase();
		 
		    ContentValues values = new ContentValues();
		    values.put(KEY_REF, w.getRef());
		    values.put(KEY_TASK, w.getTask());
		    values.put(KEY_REPEATS, w.getRepeats()); 
		    values.put(KEY_SETS, w.getSets()); 
		 
		    // updating row
		    return db.update(TABLE2, values, KEY_ID + " = ?",
		            new String[] { String.valueOf(w.getId()) });
	 }
	
	
	public void deleteWorkout(Workout w){
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TABLE2, KEY_ID + " = ?",
	            new String[] { String.valueOf(w.getId()) });
	    db.close();
	}
	
	/*
	 * WorkoutPlans
	 */

	public void addWorkoutPlan(WorkoutPlan wp){
	    SQLiteDatabase db = this.getWritableDatabase();
	    
	    ContentValues values = new ContentValues();
	    values.put(KEY_NAME, wp.getName()); 
	    values.put(KEY_TIME, wp.getTime()); 
	 
	    // Inserting Row
	    db.insert(TABLE, null, values);
	    db.close(); // Closing database connection
	}
	
	public WorkoutPlan getWorkoutPlan(int id){
	    SQLiteDatabase db = this.getReadableDatabase();
	    
	    Cursor cursor = db.query(TABLE, new String[] { KEY_ID,
	    		KEY_NAME, KEY_TIME }, KEY_ID + "=?",
	            new String[] { String.valueOf(id) }, null, null, null, null);
	    if (cursor != null)
	        cursor.moveToFirst();
	 
	    WorkoutPlan w = new WorkoutPlan(
	            cursor.getString(1));
	    return w;
	}
	
	 public List<WorkoutPlan> getAllWorkoutPlans() {
		    List<WorkoutPlan> l = new ArrayList<WorkoutPlan>();
		    // Select All Query
		    String selectQuery = "SELECT  * FROM " + TABLE;
		 
		    SQLiteDatabase db = this.getWritableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);
		 
		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
		        do {
		        	WorkoutPlan w = new WorkoutPlan();
		            w.setId(Integer.parseInt(cursor.getString(0)));
		            w.setName(cursor.getString(1));
		            //w.setTime(cursor.getString(2));
		            l.add(w);
		        } while (cursor.moveToNext());
		    }
		 
		    return l;
	 }
	 
	 public int updateWorkoutPlan(WorkoutPlan wp) {
		    SQLiteDatabase db = this.getWritableDatabase();
		 
		    ContentValues values = new ContentValues();
		    values.put(KEY_NAME, wp.getName());
		    values.put(KEY_TIME, wp.getTime()); 
		    
		    // updating row
		    return db.update(TABLE, values, KEY_ID + " = ?",
		            new String[] { String.valueOf(wp.getId()) });
	 }
	
	
	public void deleteWorkoutPlan(WorkoutPlan wp){
	    SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(TABLE, KEY_ID + " = ?",
	            new String[] { String.valueOf(wp.getId()) });
	    db.close();
	}
}
