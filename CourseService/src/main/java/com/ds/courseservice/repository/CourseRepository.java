package com.ds.courseservice.repository;

import com.ds.courseservice.model.Course;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<Course, String> {

}
