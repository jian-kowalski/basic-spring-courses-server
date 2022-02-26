package com.jiankowalski.basicspring.transportlayers;

import com.jiankowalski.basicspring.interactors.CourseService;
import com.jiankowalski.basicspring.transportlayers.mappers.CourseMapper;
import com.jiankowalski.openapi.api.CoursesApi;
import com.jiankowalski.openapi.model.CourseInput;
import com.jiankowalski.openapi.model.CourseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController implements CoursesApi {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
        this.courseMapper = CourseMapper.INSTANCE;
    }

    @Override
    public ResponseEntity<CourseModel> addCourse(CourseInput courseInput) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                courseMapper.courseToCourseModel(courseService.createCourse(courseMapper.courseInputToCourse(courseInput)))
        );
    }

    @Override
    public ResponseEntity<Void> deleteCourse(Long id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<CourseModel> findCourseById(Long id) {
        return ResponseEntity.ok(courseMapper.courseToCourseModel(courseService.getCourse(id)));
    }

    @Override
    public ResponseEntity<List<CourseModel>> findcourses() {
        return ResponseEntity.ok(
                courseMapper.coursesToCourseModels(courseService.getAllCourses())
        );
    }


}
