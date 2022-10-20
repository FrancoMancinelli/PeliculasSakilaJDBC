package models;

public class Pelicula {
	
	//ATRIBUTOS
	
	private int id;
	private int duration;
	private int year;
	private String name;
	private String description;
	private String actores;
	
	//CONSTRUCTOR
	public Pelicula(int id, int duration, int year, String name, String description) {
		super();
		this.id = id;
		this.duration = duration;
		this.year = year;
		this.name = name;
		this.description = description;
	}
	
	//CONSTRUCTOR
	public Pelicula(int id, int duration, int year, String name, String description, String actores) {
		super();
		this.id = id;
		this.duration = duration;
		this.year = year;
		this.name = name;
		this.description = description;
		this.actores = actores;
	}

	//GETTERS & SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActores() {
		return actores;
	}

	public void setActores(String actores) {
		this.actores = actores;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	//ToString
	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", duration=" + duration + ", year=" + year + ", name=" + name + ", description="
				+ description + "]";
	}
	
	
	
	

}
