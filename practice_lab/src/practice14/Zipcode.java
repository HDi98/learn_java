package practice14;

import java.util.Objects;

public class Zipcode {

	String zip, city, country;
	
	Zipcode(String zip, String city, String country){
		this.zip = zip;
		this.city = city;
		this.country = country;
	}

	@Override
	public int hashCode() {		
		return Objects.hash(zip, city, country);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zipcode other = (Zipcode) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}
	
	
}
