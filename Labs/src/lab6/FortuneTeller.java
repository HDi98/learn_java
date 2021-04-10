//name: Haonan Di
//andrew id: hdi

package lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FortuneTeller {
	List<Student> students = new ArrayList<>();
	List<Company> companies = new ArrayList<>();
	
	// DO NOT CHANGE THIS METHOD
	public static void main(String[] args) {
		FortuneTeller fortuneTeller = new FortuneTeller();
		fortuneTeller.loadStudentsList("Fortunes.csv");
		fortuneTeller.loadCompaniesList();
		fortuneTeller.printReport();
	}
	
	void printReport() {

/** Write the appropriate Collections.sort() statement here to sort companies on Rank*/
		Collections.sort(companies);
		System.out.println("*** No. of students hired by Fortune10 Best Employers ***"); 
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Rank. Company\t\t\t\tHired Count");
		System.out.println("--------------------------------------------------------------------");
		for (Company c : companies) {
			System.out.printf("%3d. %-30s: %10d%n", c.rank, c.name, c.hiredCount);
		}
		System.out.println("--------------------------------------------------------------------");
		System.out.printf("Total%43d%n", Company.overallHiredCount);
		System.out.println("====================================================================");

/** Write the appropriate Collections.sort() statement here to sort companies on hiredCount*/
		Collections.sort(companies, new CompanyHiredCountComparator());
		System.out.println("*** Fortune100 Best Employers by Hired Count ***"); 
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Rank. Company\t\t\t\tHired Count");
		System.out.println("--------------------------------------------------------------------");
		for (Company c : companies) {
			System.out.printf("%3d. %-30s: %10d%n", c.rank, c.name, c.hiredCount);
		}
		System.out.println("--------------------------------------------------------------------");
		System.out.printf("Total%43d%n", Company.overallHiredCount);
		System.out.println("====================================================================");
		System.out.println("*** Students and Employers ***");
		System.out.println("--------------------------------------------------------------------");
		
/** Write the appropriate Collections.sort() statement here to sort students on AndrewID*/

		Collections.sort(students);
		int i = 1;
		System.out.printf("#.   %-10s %-20s %-25s %-20s%n", "AndrewID", "First name", "Last name", "Employer");
		System.out.println("--------------------------------------------------------------------");
		for (Student s: students) {
			System.out.printf("%3d. %-10s %-20s %-25s %-20s%n", i++, s.andrewID, s.firstName, s.lastName, s.companyName);
		}
		System.out.println("====================================================================");

	}
	
	/** loadStudentsList() reads the data from filename 
	 * and loads students arrayList with it. 
	 */
	void loadStudentsList(String filename) {
		//write your code here
		try {
			Scanner fileScanner = new Scanner(new File(filename));
			while (fileScanner.hasNextLine()) {
				String[] tmp = fileScanner.nextLine().toString().split(",");
				students.add(new Student(tmp[1], tmp[0], tmp[2], tmp[3], tmp[4]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/** loadCompaniesList() uses the data stored in students array list
	 * to create companies array list. In this list, each Company object
	 * must have information about the company that has hired some students
	 * from CMU. 
	 */
	void loadCompaniesList() {
		//write your code here
		for (Student s: students) {
			Company.overallHiredCount ++;
			Company tmpC = new Company(s.companyRank, s.companyName);
			
			int sign = 0;
			for (Company c: companies) {				
				if(c.equals(tmpC)) {
					c.hiredCount++;
					sign=1;
					break;
				}
			}
						
			if (sign == 0) {
				tmpC.hiredCount = 1;
				companies.add(tmpC);
			}
			
		}
	}
	
	
	/*********** write your Comparator inner classes below this line ***************/

	
	public class StudentCompanyComparator implements Comparator <Student>{

		@Override
		public int compare(Student o1, Student o2) {
			// TODO Auto-generated method stub
			return o1.companyName.compareTo(o2.companyName);
		}
		
	}
	
	public class CompanyHiredCountComparator implements Comparator <Company>{

		@Override
		public int compare(Company o1, Company o2) {
			// TODO Auto-generated method stub
			if (o1.hiredCount == o2.hiredCount) {
				return o1.name.compareTo(o2.name);
			}
			else {
				return o2.hiredCount - o1.hiredCount;
			}
		}
		
	}
	
}
