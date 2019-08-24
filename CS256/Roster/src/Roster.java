import java.util.Scanner;


public class Roster {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the size: ");
		int size = input.nextInt();
		input.nextLine();
		
		Student[] roster = new Student[size];
		
		for(int i = 0; i < size; i++) {
			System.out.println("Enter the name of student " + (i+1) + ":");
			String name = input.nextLine();
			
			System.out.println("Enter the grade of student " + (i+1) + ":");
			int grade = input.nextInt();
			input.nextLine();
			
			Student student = new Student(grade,name,i);
			roster[i] = student;
		}
		
		for(Student student: roster) {
			System.out.println("Name: " + student.getName());
			System.out.println("Grade: " + student.getGrade());
			System.out.println("Id: " + student.getId());
		}
		
	}

}
