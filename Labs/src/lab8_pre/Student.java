package lab8_pre;

public class Student {

	public static final int MIN_QUESTION_TIME = 5;
	public static final int MAX_QUESTION_TIME = 30;
	
	static int totalStudentsCreated;
	static int totalStudentsHelped;
	
	int studentID;
	
	Student(){
		totalStudentsCreated++;
		studentID = totalStudentsCreated;
	}
	
	int askQuestions() {
		totalStudentsHelped++;
		return (int) ((Math.random() * (MAX_QUESTION_TIME - MIN_QUESTION_TIME)) + MIN_QUESTION_TIME);
	}
}
