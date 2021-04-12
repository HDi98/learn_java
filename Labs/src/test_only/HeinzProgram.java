package test_only;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HeinzProgram {

	Set<HeinzCourse> courseSet = new HashSet<>();
	Map<String, HeinzCourse> courseMap = new HashMap<>();
	
	public static void main(String[] args) {
		HeinzProgram hp = new HeinzProgram();
		
		hp.courseSet.add(new HeinzCourse("95712", "OPO in JAVA"));
		hp.courseSet.add(new HeinzCourse("95712", "OPO in JAVA"));
		hp.courseMap.put("95712", new HeinzCourse("95712", "OPO in JAVA"));
		hp.courseMap.put("95712", new HeinzCourse("95712", "OPO in JAVA"));
		
		System.out.println(hp.courseSet.size());
		System.out.println(hp.courseMap.size());
	}
}
