package com.cagatayiba.coursePlatform;

import com.cagatayiba.coursePlatform.dao.CourseDao;
import com.cagatayiba.coursePlatform.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class CoursePlatformApplication {

	private static CourseDao courseDao;

	public  CoursePlatformApplication(CourseDao courseDao){
		this.courseDao = courseDao;
	}


	public static void main(String[] args) {

		SpringApplication.run(CoursePlatformApplication.class, args);
		System.out.println("\nFirst Course .................\n");
		Optional<Course> firstCourse = courseDao.get(1);
		firstCourse.ifPresent(System.out::println);
		System.out.println("\nCreate Course .................\n");
		Course springVue = new Course(0, "Spring Boot + Vue","New Course","http://www.danvega.dev/courses");
		courseDao.create(springVue);
		var vueOptional = courseDao.findByTitle(springVue.getTitle());
		vueOptional.ifPresent(vueCourse -> {
			vueCourse.setDescription("Learn to build Vue apps that talk to Spring");
			courseDao.update(vueCourse, vueCourse.getId());
		});


		courseDao.delete(4);

		System.out.println("\nAll Courses .................\n");
		courseDao.getAll().forEach(System.out::println);
	}

}
