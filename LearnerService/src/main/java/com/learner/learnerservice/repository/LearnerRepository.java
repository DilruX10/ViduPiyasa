package com.learner.courseservice.repository;

import com.learner.learnerservice.model.LearnerDTO;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface LearnerRepository extends MongoRepository<LearnerDTO, String> {

}
