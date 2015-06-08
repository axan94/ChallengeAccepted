package com.example.challengeaccepted;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import database.DatabaseHandler;
import database.Workout;
import database.WorkoutPlan;
import services.ScheduleClient;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewChallenge extends Activity {
	private EditText txt1;
	private EditText txt2;
	private EditText txt3;
	private DatabaseHandler datasource;
	private List<Workout> list;
	private WorkoutPlan wp;
	private int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_challenge);

		txt1 = (EditText) findViewById(R.id.editText1);
		txt2 = (EditText) findViewById(R.id.editText2);
		txt3 = (EditText) findViewById(R.id.editText3);

		datasource = new DatabaseHandler(this);
		list = new ArrayList<Workout>();
	}

	public void addToList() {
		Workout w = new Workout(Integer.parseInt(txt3.getText().toString()),
				txt1.getText().toString(), Integer.parseInt(txt3.getText()
						.toString()));
		list.add(w);

	}

	public void nextExercise(View v) {
		addToList();
		txt1.setText("");
		txt2.setText("");
		txt3.setText("");
	}

	public void save(View v) {
		addToList();
		// go trough all of them
		wp = new WorkoutPlan();
		datasource.addWorkoutPlan(wp);
		int i = datasource.getAllWorkoutPlans().size();
		id = i-1;
		System.out.println(" asdasd " + i + " " + id);
		wp.setId(id);
		for (Workout w : list) {
			w.setRef("" + id);
			datasource.addWorkout(w);
		}

		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Title");
		alert.setMessage("Message");

		// Set an EditText view to get user input
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

				wp.setName(input.getText().toString());
				int test = datasource.updateWorkoutPlan(wp);
				System.out.println(" TEST " + test);
				Intent intent = new Intent(NewChallenge.this,
						SaveChallenge.class);
				intent.putExtra("workoutplan", id);
				NewChallenge.this.startActivity(intent);
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});

		alert.show();

	}
}
