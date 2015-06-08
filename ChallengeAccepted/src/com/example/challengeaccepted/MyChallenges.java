/**
 * 
 */
package com.example.challengeaccepted;

import java.util.List;

import database.DatabaseHandler;
import database.WorkoutPlan;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 * @author Martin
 *
 */
public class MyChallenges extends ListActivity {
	  private DatabaseHandler datasource;

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.my_challenges);

	    datasource = new DatabaseHandler(this);

	    List<WorkoutPlan> values = datasource.getAllWorkoutPlans();

	    // use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    ArrayAdapter<WorkoutPlan> adapter = new ArrayAdapter<WorkoutPlan>(this,
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
