//name: Haonan Di
//andrew id: hdi
package lab6;

public class Student implements Comparable <Student>{
	String lastName;
	String firstName;
	String andrewID;
	int companyRank;
	String companyName;
	
	Student(String firstName, String lastName, String andrewID, String companyRank, String companyName){
		this.firstName = firstName;
		this.lastName = lastName;
		this.andrewID = andrewID;
		this.companyRank = Integer.parseInt(companyRank);
		this.companyName = companyName;
	}
	
	@Override
	public int compareTo(Student student) {
		// TODO Auto-generated method stub
		
		return this.andrewID.compareTo(student.andrewID);
	}

}
