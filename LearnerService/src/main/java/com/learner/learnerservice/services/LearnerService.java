package com.learner.learnerservice.service;

import com.learner.learnerservice.model.LearnerDTO;

public interface LearnerService {

	public ResponseEntity<?> getEnrolledCourses(LearnerDTO learner);
	
	public ResponseEntity<?> enrollCourse(LearnerDTO learner);
	
	public ResponseEntity<?> unenrollCourse(LearnerDTO learner);
	
	public ResponseEntity<?> getCourseProgress(LearnerDTO learner);
	
	public ResponseEntity<?> updateCourseProgress(LearnerDTO learner);
	
}