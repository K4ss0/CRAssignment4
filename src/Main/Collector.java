package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Collector {

	String filePath = "StudentData";
	Student[] students = readStudentDataFromFile(filePath);
	Student[] compSciStudents = new Student[100];
	Student[] statStudents = new Student[100];
	Student[] apmthStudents = new Student[100];
	
	public static Student[] readStudentDataFromFile(String filePath) {
		Student[] students = new Student[100];
		
		try(FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			
			String line;
			int studentCount =0;
			while((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 4) {
					String studentID = parts[0];
					String studentName = parts[1];
					String course = parts[2];
					String grade = parts [3];
					
					students[studentCount] = new Student(studentID, studentName, course, grade);
					
					studentCount++;
					}
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
			return students;
		}

	public Student[] studentSort() {
		Student[] sortedStudents = Arrays.copyOf(students, students.length);
		
		Arrays.sort(sortedStudents, new Comparator<Student>() {
			@Override
			public int compare(Student student1, Student student2) {
				return student1.getGrade().compareTo(student2.getGrade());
			}
		});
		
		return sortedStudents;
	}
	
	public void courseArrays(Student[] sortedStudents) {
		
		int compSciCount = 0;
		int statCount = 0;
		int apmthCount = 0;
		
		
		for (Student student : sortedStudents) {
			if(student != null) {
			String currentCourse = student.getCourse();
			
			if ("COMPSCI".equals(currentCourse)) {
				compSciStudents[compSciCount++] = student;
			} else if ("STAT".equals(currentCourse)) {
				statStudents[statCount++] =student;
			} else if ("APMTH".equals(currentCourse)) {
				apmthStudents[apmthCount++] = student;
			}
		
		}
	
	}
	
	compSciStudents = Arrays.copyOf(compSciStudents, compSciCount);
	statStudents = Arrays.copyOf(statStudents, statCount);
	apmthStudents = Arrays.copyOf(apmthStudents, apmthCount);
}
}

