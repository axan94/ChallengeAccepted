/**
 * 
 */
package com.example.challengeaccepted;

import java.util.List;

import database.DatabaseHandler;
import database.WorkoutPlan;
import android.app.ListActivity;
import android.content.Intent;
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
	private ArrayAdapter<WorkoutPlan> adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_challenges);

		datasource = new DatabaseHandler(this);

		values = datasource.getAllWorkoutPlans();
		// use the SimpleCursorAdapter to show the
		// elements in a ListView
		adapter = new ArrayAdapter<WorkoutPlan>(this,
				android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
		
		
		ListView lv = getListView();
		lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
		    @Override
		    public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
		        return onLongListItemClick(v,pos,id);
		    }
		});
	}

	@Override
	protected void onListItemClick(ListView list, View view, int position,
			long id) {
		super.onListItemClick(list, view, position, id);
//		String selectedItem = (String) getListView()
//				.getItemAtPosition(position);		
		Intent intent = new Intent(MyChallenges.this,
				WorkSub.class);
		intent.putExtra("workoutplan", values.get(position).getId());
		MyChallenges.this.startActivity(intent);		

	}
	
	
	protected boolean onLongListItemClick(View v, int pos, long id) {
		datasource.deleteWorkoutPlan((WorkoutPlan) getListView().getItemAtPosition(pos));
//		adapter.notifyDataSetChanged();
		finish();
		Intent refresh = new Intent(this, MyChallenges.class);
		startActivity(refresh);
	    System.out.println(" LONGCLICK ");
	    return true;
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
