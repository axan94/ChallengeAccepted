package com.example.challengeaccepted;

import java.util.Calendar;

import services.NotifyService;
import services.ScheduleClient;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	
	public void myChallengeButton(View v){
		Intent intent = new Intent(MainActivity.this, MyChallenges.class);
		MainActivity.this.startActivity(intent);
		
	}
	
	public void newChallenge(View v){
		Intent intent = new Intent(MainActivity.this, NewChallenge.class);
		MainActivity.this.startActivity(intent);
		
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
}
