package com.ds.courseservice.service;

import com.ds.courseservice.model.Course;
import com.ds.courseservice.model.CourseContent;
import com.ds.courseservice.model.Msg;

public interface CourseService {

	public ResponseEntity<?> getCourseById(String courseId);
	
	public ResponseEntity<?> getCourses();
	
	public ResponseEntity<?> addCourse(Course course,);
	
	public ResponseEntity<?> addCourseContent(Course course);
	
	public ResponseEntity<?> updateCourseById(String courseId, Course course);
	
	public ResponseEntity<?> updateCourseContentById(Course course);
	
	public ResponseEntity<?> deleteById(String courseId);
	
	public ResponseEntity<?> approveCourse(String courseId);
	
	public ResponseEntity<?> getAllStudentsByCourseId(String courseId);	
}