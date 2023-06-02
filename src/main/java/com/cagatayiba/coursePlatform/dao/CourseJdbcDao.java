package com.cagatayiba.coursePlatform.dao;

import com.cagatayiba.coursePlatform.model.Course;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class CourseJdbcDao implements CourseDao{
    private  JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CourseJdbcDao.class);
    public CourseJdbcDao(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private RowMapper<Course> courseMapper = (rs, rowNum)->{
        Course newCourse = new Course();
        newCourse.setId(rs.getInt("course_id"));
        newCourse.setDescription(rs.getString("description"));
        newCourse.setTitle(rs.getString("title"));
        newCourse.setUrl(rs.getString("link"));
        return  newCourse;
    };

    @Override
    public List<Course> getAll() {
        String query = "SELECT * FROM course";
        return jdbcTemplate.query(query, courseMapper);
    }

    @Override
    public Optional<Course> get(Integer id) {
        String query = "SELECT * FROM course WHERE course_id=?";
        Course course = null;
        try {
            course = jdbcTemplate.queryForObject(query, courseMapper, id);
        }catch (EmptyResultDataAccessException e){
            logger.error("Course cannot found with id: " + id);
        }
        // since queryForObject Throws EmptyResultDataAccessException if there is no match for given id, ofNullable part is redundant
        return Optional.ofNullable(course);
    }

    @Override
    public boolean delete(Integer id) {
        String sql  = "DELETE FROM course WHERE course_id=?";
        int update = jdbcTemplate.update(sql, id);
        boolean isDeleted = update == 1;
        if(isDeleted){
            logger.info("Course Deleted : " + id);
        }
        return isDeleted;
    }

    @Override
    public boolean update(Course course, Integer id) {
        String sql = "UPDATE course SET title = ?, description = ?, link = ? WHERE course_id = ?";
        int update  = jdbcTemplate.update(sql, course.getTitle(), course.getDescription(), course.getUrl(), id);
        boolean isSuccess = update == 1;
        if(isSuccess){
            logger.info("The course is updated : "  + course.getTitle());
        }
        return isSuccess;
    }

    @Override
    public boolean create(Course course) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("course").
                usingGeneratedKeyColumns("course_id").
                usingColumns("description", "title", "link");
        Map<String, Object> params = new HashMap<>();
        params.put("description", course.getDescription());
        params.put("title", course.getTitle());
        params.put("link", course.getUrl());

        /*int id = 0;
        try {
            id = simpleJdbcInsert.executeAndReturnKey(params).intValue();
        }catch (DataIntegrityViolationException e){
            logger.error("cannot insert the course (title: " + course.getTitle() + " ).");
            return false;
        }*/

        try{
            simpleJdbcInsert.execute(params);
        }catch (DataAccessException e){
            logger.error("Cannot create the course : " + course.getTitle());
        }


        logger.info("New course is created: " + course.getTitle());
        return true;
    }



    @Override
    public Optional<Course> findByTitle(String title) {
        String query = "SELECT * FROM course WHERE title=?";
        Course course= null;
        try{
            course = jdbcTemplate.queryForObject(query, courseMapper, title);
        }catch (EmptyResultDataAccessException e){
            logger.info("Cannot find the course with title : " + title);
        }

        return Optional.ofNullable(course);
    }
}
