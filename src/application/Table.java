package application;



public class Table {
	private String exercise;
	private int sets;
	private int id;
	private String unitLabel;
	private double weight;
	private double rep;
	private String date;
	public Table(int id,String exercise,int sets,double weight,double rep,String date) {
		this.id=id;
		this.exercise=exercise;
		this.sets=sets;
		this.weight=weight;
		this.rep=rep;
		this.date=date;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getExercise() {
		return exercise;
	}
	public void setExercise(String exercise) {
		this.exercise = exercise;
	}
	
	public int getSets() {
		return sets;
	}
	public void setSets(int sets) {
		this.sets = sets;
	}
	
	
	
	public String getUnitLabel() {
		return unitLabel;
	}
	public void setUnitLabel(String unitLabel) {
		this.unitLabel = unitLabel;
	}
	
	
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	
	public double getRep() {
		return rep;
	}
	public void setRep(double rep) {
		this.rep = rep;
	}
	
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	

}
