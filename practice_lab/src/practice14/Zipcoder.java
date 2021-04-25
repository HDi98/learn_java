package practice14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Zipcoder {

	Map<String, Zipcode> zipMap = new HashMap<>();
	int duplicate_count = 0;
	int over_count = 0;
	
	
	public static void main(String[] args) {
		Zipcoder z = new Zipcoder();
		z.loadData();
		
		System.out.println(z.zipMap.size() + " recorded loaded, " + z.duplicate_count + " duplicates ignored, " + z.over_count + " dirty zipcodes overwritten");
		System.out.println("Enter zipcode to search");
		Zipcode newz = z.findZipcode(new Scanner(System.in).nextLine());
		if (newz != null) {
			System.out.println("Found county: " + newz.country + ". city: " + newz.city);
		}
	}
	
	
	
	
	public void loadData() {
		
		try {
			Scanner fileScanner = new Scanner(new File("DirtyZipcodes.txt"));
			while (fileScanner.hasNextLine()) {
				String[] tmp = fileScanner.nextLine().split(",");
				Zipcode z = new Zipcode(tmp[0].trim(), tmp[1].trim(), tmp[2].trim());
				// before put it into the map, we need to see if duplicate
				if (!Objects.isNull(zipMap.get(tmp[0]))) {
					Zipcode tmpZipcode = zipMap.get(tmp[0]);
					//duplicate
					if (z.equals(tmpZipcode)) {
						System.out.println("Duplicate found: " + tmp[0] + ", " + tmp[1] + ", " + tmp[2]);
						duplicate_count += 1;
					}
					else {
						System.out.println("Overwritten: " + tmp[0] + ", " + tmp[1] + ", " + tmp[2]);
						over_count += 1;
					}
				}
				
				zipMap.put(tmp[0], z);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Zipcode findZipcode(String zipcode) {
		return zipMap.get(zipcode);
	}
	
}
