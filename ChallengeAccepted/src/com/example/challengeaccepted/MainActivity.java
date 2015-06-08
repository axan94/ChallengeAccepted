package com.example.challengeaccepted;

import java.util.Calendar;

import services.ScheduleClient;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	// This is a handle so that we can call methods on our service
    private ScheduleClient scheduleClient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
        // Create a new service client and bind our activity to this service
        scheduleClient = new ScheduleClient(this);
        scheduleClient.doBindService();
		
	}
	
    /**
     * This is the onClick called from the XML to set a new notification 
     */
    public void onDateSelectedButtonClick(View v){
    	// Get the date from our datepicker
//    	int day = picker.getDayOfMonth();
//    	int month = picker.getMonth();
//    	int year = picker.getYear();
    	// Create a new calendar set to the date chosen
    	// we set the time to midnight (i.e. the first minute of that day)
    	Calendar c = Calendar.getInstance();
    	//c.set(year, month, day);
    	c.set(2015, 5, 8);
    	c.set(Calendar.HOUR_OF_DAY, 11);
    	c.set(Calendar.MINUTE, 12);
    	c.set(Calendar.SECOND, 0);
    	// Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
    	scheduleClient.setAlarmForNotification(c);
    	// Notify the user what they just did
    	Toast.makeText(this, "Notifcation set for: " + c.getTime(), Toast.LENGTH_SHORT).show();
//    	Toast.makeText(this, "Notification set for: "+ day +"/"+ (month+1) +"/"+ year, Toast.LENGTH_SHORT).show();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
