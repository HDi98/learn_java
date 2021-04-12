package test_only;

import java.util.Objects;

public class HeinzCourse {

	String courseCode, courseName;
	HeinzCourse(String courseCode, String courseName){
		this.courseCode = courseCode;
		this.courseName = courseName;
	}
//	@Override
//	public int hashCode() {
//		return Objects.hash(courseCode);
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		HeinzCourse other = (HeinzCourse) obj;
//		return courseCode.equals(other.courseCode);
//	}
	
}
