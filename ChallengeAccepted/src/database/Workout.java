package database;

public class Workout {
	private int id;
	private String ref;
	private String task;
	private int repeats;
	
	public Workout(){
		
	}
	
	public Workout(int id, String ref, String task, int repeats){
		this.id = id;
		this.ref = ref;
		this.task = task;
		this.repeats = repeats;
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
	
	
}
