/**
 * 
 */
package com.example.challengeaccepted;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import services.ScheduleClient;
import database.DatabaseHandler;
import database.Workout;
import database.WorkoutPlan;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Martin
 *
 */
public class SaveChallenge extends ListActivity {
	private DatabaseHandler datasource;
	private boolean saving = false;
	private int id;
	
	// This is a handle so that we can call methods on our service
    private ScheduleClient scheduleClient;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.save_challenge);
		Bundle extras = getIntent().getExtras();
		id = (Integer) extras.get("workoutplan");

		datasource = new DatabaseHandler(this);

		List<Workout> values = datasource.getAllWorkouts(id);

		// use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<Workout> adapter = new ArrayAdapter<Workout>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
		
        // Create a new service client and bind our activity to this service
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
	
	public void drop(View v){
		WorkoutPlan wp = new WorkoutPlan();
		wp.setId(id);
		datasource.deleteWorkoutPlan(wp);
		
		for(Workout w :datasource.getAllWorkouts()){
			if(Integer.parseInt(w.getRef()) == id){
				datasource.deleteWorkout(w);
			}
		}
		Intent intent = new Intent(SaveChallenge.this, MainActivity.class);
		SaveChallenge.this.startActivity(intent);
		
	}

	public void getTime(View v) {
//		do {
			AlertDialog.Builder alert = new AlertDialog.Builder(this);

			alert.setTitle("When do you wanna train?");
			alert.setMessage("Timeformat (hh:mm)");

			// Set an EditText view to get user input
			final EditText input = new EditText(this);
			alert.setView(input);

			alert.setPositiveButton("Ok",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

							DateFormat f = new SimpleDateFormat("hh:mm");
							Date d1 = null;
							try {
								d1 = f.parse(input.getText().toString());
								startingService(d1.getHours(), d1.getMinutes());
								saving = true;
								
								WorkoutPlan plan = datasource.getWorkoutPlan(id);
								plan.setId(id);
								plan.setTime(input.getText().toString());
								int lol = datasource.updateWorkoutPlan(plan);
								System.out.println("PLAN " + plan.getId());
								System.out.println(" ID " + id + " " + lol);
								Intent intent = new Intent(SaveChallenge.this, MainActivity.class);
								SaveChallenge.this.startActivity(intent);
							} catch (ParseException e) {
								saving = false;
							}

						}
					});

			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							// Canceled.
						}
					});

			alert.show();
		//} while (!saving);


	}
	
    /**
     * This is the onClick called from the XML to set a new notification 
     */
    public void startingService(int hours, int minute){
    	Calendar c = Calendar.getInstance();
    	c.set(2015, 5, 8);
    	c.set(Calendar.HOUR_OF_DAY, hours);
    	c.set(Calendar.MINUTE, minute);
    	c.set(Calendar.SECOND, 0);
    	// Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
    	scheduleClient.setAlarmForNotification(c);
    	// Notify the user what they just did
//    	Toast.makeText(this, "Notifcation set for: " + c.getTime(), Toast.LENGTH_SHORT).show();
    }

	
    @Override
    protected void onStop() {
    	// When our activity is stopped ensure we also stop the connection to the service
    	// this stops us leaking our activity into the system *bad*
    	if(scheduleClient != null)
    		scheduleClient.doUnbindService();
    	super.onStop();
    }
}
