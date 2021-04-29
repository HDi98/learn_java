package lab8_shuwu;

import java.util.Random;

public class Student extends Thread{

    static final int MIN_QUESTION_TIME = 5;
    static final int MAX_QUESTION_TIME = 30;
    static int totalStudentsCreated;
    static int totalStudentsHelped;
    int studentID;

    Student() {
        studentID = ++totalStudentsCreated;
    }

    int askQuestions(){
        Random random = new Random();
        int askedTime = random.nextInt(MAX_QUESTION_TIME - MIN_QUESTION_TIME) + MIN_QUESTION_TIME;
        return askedTime;
    }
}
