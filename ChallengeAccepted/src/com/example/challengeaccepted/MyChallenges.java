/**
 * 
 */
package com.example.challengeaccepted;

import java.util.List;

import database.DatabaseHandler;
import database.WorkoutPlan;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Martin
 *
 */
public class MyChallenges extends ListActivity {
	private DatabaseHandler datasource;
	private List<WorkoutPlan> values;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_challenges);

		datasource = new DatabaseHandler(this);

		values = datasource.getAllWorkoutPlans();
		System.out.println("PLANNS " + values.get(0).toString());
		// use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter<WorkoutPlan> adapter = new ArrayAdapter<WorkoutPlan>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView list, View view, int position,
			long id) {

		super.onListItemClick(list, view, position, id);
		String selectedItem = (String) getListView()
				.getItemAtPosition(position);
		// String selectedItem = (String) getListAdapter().getItem(position);
		text.setText("You clicked " + selectedItem + " at position " + position);
		

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
