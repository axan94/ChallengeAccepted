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
	
	public WorkoutPlan(){
		
	}
	
	public WorkoutPlan(int id, String name){
		this.id = id;
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
	
}
