package lab8_pre;


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
		//write your code here
		
		while (!JavaCourse.allDone && helpTime < MAX_HELP_TIME) {
			Student s = null;
			synchronized (JavaCourse.studentQ){
				s = JavaCourse.studentQ.poll();
			}
			int questionTime = 0;
			if (s != null) {
				questionTime = s.askQuestions();
				TA.totalHelpTime += questionTime;
				helpTime += questionTime;
				try {
					Thread.sleep(questionTime);
					studentsHelped++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(spacer(taID) + "TA" + taID + ":Student" + s.studentID +":" + questionTime + "min");
				
			}
			
			if (helpTime >= MAX_HELP_TIME) {
				System.out.println(spacer(taID) + "---- TA" + taID + " done ----");
			}
			
			
			if (TA.totalHelpTime >= MAX_HELP_TIME * taCount && helpTime >= MAX_HELP_TIME) {
				
				if (!JavaCourse.allDone) {
					System.out.println("******************* All done flag set by TA" + taID);
				}					
				JavaCourse.allDone = true;
				
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
