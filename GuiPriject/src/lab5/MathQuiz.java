//author: Haonan Di
// Andrew ID: hdi
package lab5;

public class MathQuiz extends Quiz {
	
	@Override
	void createQuestion() {
		// TODO Auto-generated method stub
		Quiz.answerChecked = false;
		int op1 = (int)Math.round(Math.random() * 100);
		int op2 = (int)Math.round(Math.random() * 100);
		questionString = new String[3];
		questionString[0] = Integer.toString(op1);
		questionString[2] = Integer.toString(op2);
		switch ((int)(Math.random()*3)) {
		case 0: questionString[1] = "+"; break;
		case 1: questionString[1] = "-"; break;
		default: questionString[1] = "*"; break;
		}
	}

	@Override
	boolean checkAnswer(String answer) {
		// TODO Auto-generated method stub
		if (Quiz.answerChecked) {
			return true;
		}
		Quiz.answerChecked = true;
		Quiz.totalCount++;
		int answer1 = Integer.parseInt(answer);
		switch (questionString[1]) {
		case "+": {
			if (Integer.parseInt(questionString[0])+ Integer.parseInt(questionString[2]) == answer1) {
				Quiz.score++;
				return true;
		}
			else {
				return false;
			}
		}
		case "-":{
			if (Integer.parseInt(questionString[0])- Integer.parseInt(questionString[2]) == answer1) {
				Quiz.score++;
				return true;
			}
				else {
					return false;
				}
		}
		case "*": {
			if (Integer.parseInt(questionString[0])* Integer.parseInt(questionString[2]) == answer1) {
				Quiz.score++;
				return true;
			}
				else {
					return false;
				}
		}
		
		}
		return false;
	}

}
