package com.cagatayiba.coursePlatform.dao;

import com.cagatayiba.coursePlatform.model.Course;

import java.util.Optional;

public interface CourseDao extends CrudDao<Course, Integer>{
    Optional<Course> findByTitle(String title);
}
