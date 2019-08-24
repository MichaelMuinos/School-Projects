
public class Student {
	
	private int grade = 0;
	private String name = "";
	private int id = 0;
	
	public Student(int grade, String name, int id) {
		this.grade = grade;
		this.name = name;
		this.id = id;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
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
