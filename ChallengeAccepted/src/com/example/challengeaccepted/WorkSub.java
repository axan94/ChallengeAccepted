package com.example.challengeaccepted;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import database.DatabaseHandler;
import database.Workout;

public class WorkSub extends ListActivity {
	private DatabaseHandler datasource;
	private List<Workout> values;
	private int id;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workout_sub);

		Bundle extras = getIntent().getExtras();
		id = (Integer) extras.get("workoutplan");
		
		datasource = new DatabaseHandler(this);

		values = datasource.getAllWorkouts(id);
		// use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<Workout> adapter = new ArrayAdapter<Workout>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
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
}
