package com.jiankowalski.basicspring.datasources;

import com.jiankowalski.basicspring.datasources.config.DatasourceConfiguration;
import com.jiankowalski.basicspring.datasources.repository.CourseRepository;
import com.jiankowalski.basicspring.domain.CategoryEnum;
import com.jiankowalski.basicspring.domain.Course;
import com.jiankowalski.basicspring.domain.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@EnableJpaRepositories(basePackages = "com.jiankowalski.basicspring.datasources")
@Import({CourseDatasource.class, DatasourceConfiguration.class})
class CourseDatasourceTest {

    private static Long ID_INEXISTENTE = -999L;
    @Autowired
    private CourseDatasource courseDatasource;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void deveSalvarCourseComSucesso() {
        Course courseSalvo = courseDatasource.saveCourse(CourseFactory.criarCourseValido());
        assertThat(courseSalvo).satisfies((course -> {
            assertThat(course.getName()).isEqualTo("Angular");
            assertThat(course.getCategory()).isEqualTo(CategoryEnum.FRONT_END);
        }));
    }

    @Test
    void findCourse() {
        Course courseSalvo = courseDatasource.saveCourse(CourseFactory.criarCourseValido());
        assertThat(courseDatasource.findCourse(courseSalvo.getId())).satisfies((course -> {
            assertThat(course.getName()).isEqualTo("Angular");
            assertThat(course.getCategory()).isEqualTo(CategoryEnum.FRONT_END);
        }));
    }

    @Test
    void findAllCourses() {
        courseDatasource.saveCourse(CourseFactory.criarCourseValido());
        courseDatasource.saveCourse(CourseFactory.criarCourseValido());
        assertThat(courseDatasource.findAllCourses()).satisfies((courses -> {
            assertThat(courses.size()).isEqualTo(2);
            assertThat(courses).isNotEmpty();
        }));
    }

    @Test
    void deleteCourse() {
        Course courseSalvo = courseDatasource.saveCourse(CourseFactory.criarCourseValido());
        assertThat(courseDatasource.findCourse(courseSalvo.getId())).satisfies((course -> {
            assertThat(course.getName()).isEqualTo("Angular");
            assertThat(course.getCategory()).isEqualTo(CategoryEnum.FRONT_END);
        }));
        assertDoesNotThrow(() -> courseDatasource.deleteCourse(courseSalvo.getId()));
    }

    @Test
    void deveLancarExceptionAoConsultarCourseInexistente() {
        assertThat(assertThrows(NotFoundException.class, () -> {
            courseDatasource.findCourse(ID_INEXISTENTE);
        })).satisfies((e) -> {
            assertThat(e.getMessage()).isEqualTo("Record not found for curso with id -999");
        });
    }
}