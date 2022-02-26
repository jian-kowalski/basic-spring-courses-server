package com.jiankowalski.basicspring.datasources;

import com.jiankowalski.basicspring.datasources.mapper.CourseMapperDatasource;
import com.jiankowalski.basicspring.datasources.repository.CourseRepository;
import com.jiankowalski.basicspring.domain.Course;
import com.jiankowalski.basicspring.domain.exception.DomainException;
import com.jiankowalski.basicspring.domain.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseDatasource {

    private final CourseRepository courseRepository;
    private final CourseMapperDatasource courseMapperDatasource;

    public CourseDatasource(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
        this.courseMapperDatasource = CourseMapperDatasource.INSTANCE;
    }

    public Course saveCourse(Course course) {
        try {
            return courseMapperDatasource.courseEntityToCourse(courseRepository.save(courseMapperDatasource.courseToCourseEntity(course)));
        } catch (Exception e) {
            throw new DomainException("Erro ao salvar o corso", e);
        }
    }

    public Course findCourse(Long id) {
        return courseMapperDatasource.courseEntityToCourse(
                courseRepository.findById(id).orElseThrow(() -> {
                    throw new NotFoundException("curso", id);
                })
        );
    }

    public List<Course> findAllCourses() {
        return courseMapperDatasource.coursesToCourseModels(courseRepository.findAll());
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
