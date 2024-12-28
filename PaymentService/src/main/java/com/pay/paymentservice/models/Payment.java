package com.ds.courseservice.controller;

import com.ds.courseservice.model.Course;
import com.ds.courseservice.model.Msg;
import com.ds.courseservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable String courseId){
        return courseService.getCourseById(courseId);
    }

    @GetMapping("/")
    public ResponseEntity<?> getCourses(){
        return courseService.getCourses();
    }

    @PostMapping("/")
    public ResponseEntity<?> addCourse(@RequestBody Course course){
        return courseService.insertCourse(course);
    }
	
	@PostMapping("/content")
    public ResponseEntity<?> addCourseContent(@RequestBody Course course){
        return courseService.insertCourse(courseContent);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<?> updateCourseById(@PathVariable String courseId, @RequestBody Course course){
        return courseService.updateCourseById(courseId, course);
    }
	
	@PostMapping("/content")
    public ResponseEntity<?> updateCourseContentById(@RequestBody Course course){
        return courseService.updateCourseContentById(courseContent);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteById(@PathVariable String courseId){
        return courseService.deleteById(courseId);
    }
	
	@PostMapping("/approve/{courseId}")
    public ResponseEntity<?> approveCourse(@PathVariable Msg msg,){
        return courseService.approveCourse(courseId);
    }
	
	@GetMapping("/students/{courseId}")
    public ResponseEntity<?> getAllStudentsByCourseId(@PathVariable String courseId){
        return courseService.getAllStudentsByCourseId(courseId);
    }

}
