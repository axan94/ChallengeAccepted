package database;

import java.util.Date;

public class Workout {
	private int id;
	private String ref;
	private String task;
	private int repeats;
	private int sets;
	
	
	public Workout(){
		
	}
	
	public Workout( int repeats, String task,
			int sets) {
		this.task = task;
		this.repeats = repeats;
		this.sets = sets;
	}


	public String getRef() {
		return ref;
	}


	public void setRef(String ref) {
		this.ref = ref;
	}


	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public int getRepeats() {
		return repeats;
	}


	public void setRepeats(int repeats) {
		this.repeats = repeats;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	
	

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}



	@Override
	public String toString() {
		return "Workout [task=" + task
				+ ", repeats=" + repeats + ", sets=" + sets
				+ "]";
	}
}
