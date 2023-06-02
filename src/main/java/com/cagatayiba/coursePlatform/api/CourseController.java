package com.cagatayiba.coursePlatform.api;


import com.cagatayiba.coursePlatform.dto.DefaultErrorMessage;
import com.cagatayiba.coursePlatform.model.Course;
import com.cagatayiba.coursePlatform.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    @GetMapping()
    public ResponseEntity<List<Course>> getAllCourses(){
        return ResponseEntity
                .ok()
                .body(courseService.getAllCourses());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseWithId(@PathVariable int id){
        return ResponseEntity
                .ok()
                .body(courseService.getCourseWithId(id));
    }

    @PostMapping()
    public ResponseEntity addCourse(@RequestBody Course course){
        courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCourse(@RequestBody Course course, @PathVariable int id){
        courseService.updateCourse(course, id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable int id){
        courseService.deleteCourse(id);
        return ResponseEntity.ok().build();
    }
}
