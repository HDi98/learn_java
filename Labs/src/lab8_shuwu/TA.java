package lab8_shuwu;

public class TA implements Runnable, Comparable<TA>{

	static int totalHelpTime;  //total time spent so far across all TA's
	static int taCount; //number of TAs
	static final int MAX_HELP_TIME = 120; //max total time a TA can spend across all students

	int studentsHelped; //number of students helped by a TA so far
	int helpTime;  //total time spent by a TA so far

	int taID; //TA's unique id

	TA() {
		taID = ++taCount;
	}

	@Override
	public void run() {
		Student s;
		//ta如果帮助时间超过 MAX_HELP_TIME就停止了
		while(helpTime < MAX_HELP_TIME && !JavaCourse.allDone){
			synchronized (JavaCourse.studentQ){
				s = JavaCourse.studentQ.poll();
			}

			if(s != null){
				studentsHelped++;
				Student.totalStudentsHelped++;
				int questionTime = s.askQuestions();
				totalHelpTime += questionTime;
				helpTime += questionTime;

				try{
					Thread.sleep(questionTime);
					System.out.println(spacer(taID) + "TA" + taID + ":Student" + s.studentID + ":" + questionTime + "min");
				}catch (InterruptedException e){
					e.printStackTrace();
				}
			}

			if(helpTime >= MAX_HELP_TIME){
				System.out.println(spacer(taID) + "---- " + "TA" + taID + " done ----");
				if(!JavaCourse.allDone){
					JavaCourse.allDone = true;
					System.out.println("****************All done flag set by" + "TA" + taID);
				}

			}
			if(totalHelpTime >= taCount * MAX_HELP_TIME){
				if(!JavaCourse.allDone){
					JavaCourse.allDone = true;
					System.out.println("****************All done flag set by" + "TA" + taID);
				}
			}
		}


	}

	@Override
	public int compareTo(TA o) {
		//write your code here
		return o.helpTime - this.helpTime;
	}


	//do not change this method
	String spacer(int taID) {
		StringBuilder spaces = new StringBuilder();
		for (int i = 1; i < taID; i++) {
			spaces.append("\t\t");
		}
		return spaces.toString();
	}
}
