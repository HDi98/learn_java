import java.util.Scanner;

/**
 * 
 */

/**
 * 
 * @author Haonan Di
 *
 */
public class weeklyTemp {

	/**
	 * @param args
	 */
	private int weeklyTemp[] = new int[7];
	private int max, min;
	private float average;
	
	//accessor and mutator method
	public int[] getWeeklyTemp() {
		return weeklyTemp;
	}

	public void setWeeklyTemp(int[] weeklyTemp) {
		this.weeklyTemp = weeklyTemp;
	}

	public int getMax() {
		generateMax();
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		generateMin();
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public float getAverage() {
		generateAverage();
		return average;
	}

	public void setAverage(float avg) {
		this.average = avg;
	}

	//8.1 a) getTemperature method
	void getTemperatures(){
		
		int i;
		Scanner readInput = new Scanner(System.in);
		
		for(i = 0; i<7; i++) {
			char temp = (char)(i + '0');
			System.out.println("Please enter temperature for the " + temp + "'s day");
			weeklyTemp[i] = readInput.nextInt();
		}
		
	}
	
	//8.1 b) printTemperatures, it's the accessor method for status weeklyTemp
	
	void printTemperatures() {
		System.out.print(weeklyTemp);
	}
	
	//8.1 c) getMax, slightly modify the accessor method for max
	
	void generateMax() {
		max = weeklyTemp[0];
		for(int i = 1; i < 7; i++) {
			if (weeklyTemp[i] > max) {
				max = weeklyTemp[i];
			}
		}
		
	}
	
	//8.1 d) same way of getMax function
	
	void generateMin() {
		min = weeklyTemp[0];
		for (int i = 1; i < 7; i ++) {
			if (weeklyTemp[i] < min) {
				min = weeklyTemp[i];
			}
		}
		
		
	}
	
	//8.1 e) generateAverage method, sum and divide
	void generateAverage() {
		float sum1 = 0;
		
		for (int i = 0; i < 7; i ++) {
			sum1 += weeklyTemp[i];
		}
		
		average = sum1 / 7;
	}
	
	//8.1 f) call the status is fine
	
	void printStatistics() {
		getMax();
		getMin();
		getAverage();
		System.out.printf("The max temperature is: %d\nThe min temperature is: %d\nThe average temperature is: %5.2f",max, min, average );
	}
	
	
	
	
	public void main(String[] args) {
		// TODO Auto-generated method stub

		getTemperatures();
		printTemperatures();
		getMax();
		getMin();
		getAverage();
		printStatistics();
		
	}

}
