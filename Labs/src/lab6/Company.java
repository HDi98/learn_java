//name: Haonan Di
//andrew id: hdi
package lab6;

import java.util.Objects;

public class Company implements Comparable <Company>{
	static int overallHiredCount;
	int rank;
	String name;
	int hiredCount;
	
	Company(int rank, String name){
		this.rank = rank;
		this.name = name;
	}
	
	@Override
	public int hashCode() {		
		return Objects.hash(rank, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return other.name.toLowerCase().equals(this.name.toLowerCase()) && other.rank == this.rank;
	}

	@Override
	public int compareTo(Company company) {
		// TODO Auto-generated method stub
		return this.rank - company.rank;
	}
	
}
