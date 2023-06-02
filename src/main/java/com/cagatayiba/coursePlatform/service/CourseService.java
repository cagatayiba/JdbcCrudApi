package com.cagatayiba.coursePlatform.service;


import com.cagatayiba.coursePlatform.dao.CourseDao;
import com.cagatayiba.coursePlatform.exception.NoObjectWithGivenIdException;
import com.cagatayiba.coursePlatform.model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseDao courseDao;

    public List<Course> getAllCourses(){
        return courseDao.getAll();
    }

    // throws NoObjectWithGivenIdException
    public Course getCourseWithId(int id) {
        //return courseDao.get(id).orElseThrow(() -> new NoObjectWithGivenIdException(id));
        return courseDao.get(id).orElse(null);
    }

    public void addCourse(Course course){
        courseDao.create(course);
    }

    public void updateCourse(Course course, int id){
        courseDao.update(course, id);
    }

    public void deleteCourse(int id){
        courseDao.delete(id);
    }

    /*
        List<T> getAll();
    Optional<T> get(K id);
    boolean delete(K id);
    boolean update(T object, K id);
    boolean create(T object);
    */
}
