package com.jiankowalski.basicspring.interactors;

import com.jiankowalski.basicspring.datasources.CourseDatasource;
import com.jiankowalski.basicspring.datasources.CourseFactory;
import com.jiankowalski.basicspring.domain.Course;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {CourseService.class})
class CourseServiceTest {

    @Autowired
    private CourseService courseService;

    @MockBean
    private CourseDatasource courseDatasource;

    @Test
    void createCourse() {
        Mockito.when(courseDatasource.saveCourse(Mockito.any(Course.class))).thenReturn(CourseFactory.criarCourseValido());
        Course course = courseService.createCourse(CourseFactory.criarCourseValido());
        assertThat(course.getName()).isEqualTo("Angular");
    }

    @Test
    void getCourse() {
        Mockito.when(courseDatasource.findCourse(Mockito.any(Long.class))).thenReturn(CourseFactory.criarCourseValido());
        Course course = courseService.getCourse(1L);
        assertThat(course.getName()).isEqualTo("Angular");
    }

    @Test
    void getAllCourses() {
        Mockito.when(courseDatasource.findAllCourses()).thenReturn(Arrays.asList(CourseFactory.criarCourseValido()));
        assertThat(courseService.getAllCourses()).satisfies(courses -> {
            assertThat(courses.size()).isEqualTo(1);
            assertThat(courses.get(0).getName()).isEqualTo("Angular");
        });
    }

    @Test
    void deleteCourse() {
        Mockito.when(courseDatasource.findCourse(Mockito.any(Long.class))).thenReturn(CourseFactory.criarCourseValido());
        Course courses = courseService.getCourse(1L);
        assertDoesNotThrow(() -> {
            courseService.deleteCourse(courses.getId());
        });

    }
}