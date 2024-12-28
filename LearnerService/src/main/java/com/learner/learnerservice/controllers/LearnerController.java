package com.learner.learnerservice.controller;

import com.learner.learnerservice.model.LearnerDTO;
import com.learner.learnerservice.model.Msg;
import com.learner.learnerservice.service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/learner")
public class LearnerController {

    @Autowired
    private LearnerService learnerService;

    @GetMapping("/courses/")
    public ResponseEntity<?> getEnrolledCourses(@RequestBody LearnerDTO learner){
        return learnerService.getEnrolledCourses(learner);
    }

    @PostMapping("/enroll/")
    public ResponseEntity<?> enrollCourse(@RequestBody LearnerDTO learner){
        return learnerService.enrollCourse(learner);
    }
	
	@PostMapping("/unenroll/")
    public ResponseEntity<?> unenrollCourse(@RequestBody LearnerDTO learner){
        return learnerService.unenrollCourse(learner);
    }

    @GetMapping("/progress/")
    public ResponseEntity<?> getCourseProgress(@RequestBody LearnerDTO learner){
        return learnerService.getCourseProgress(learner);
    }
	
	@PostMapping("/progress")
    public ResponseEntity<?> updateCourseProgress(@RequestBody LearnerDTO learner){
        return learnerService.updateCourseProgress(learner);
		
}
