/**
 * 
 */
package database;

/**
 * @author Martin
 *
 */
public class WorkoutPlan {
	private int id;
	private String name;
	private String time;
	
	public WorkoutPlan(){
		
	}
	
	public WorkoutPlan(String name){
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return ""+ name;
	}
}
