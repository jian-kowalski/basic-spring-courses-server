package com.jiankowalski.basicspring.interactors;

import com.jiankowalski.basicspring.datasources.CourseDatasource;
import com.jiankowalski.basicspring.domain.Course;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseDatasource courseDatasource;

    public CourseService(CourseDatasource petDatasource) {
        this.courseDatasource = petDatasource;
    }

    public Course createCourse(Course pet) {
        return courseDatasource.saveCourse(pet);
    }

    public Course getCourse(Long id) {
        return courseDatasource.findCourse(id);
    }

    public List<Course> getAllCourses() {
        return courseDatasource.findAllCourses();
    }

    public void deleteCourse(Long id) {
        courseDatasource.deleteCourse(id);
    }
}
