package com.jiankowalski.basicspring.transportlayers.mappers;

import com.jiankowalski.basicspring.domain.Course;
import com.jiankowalski.openapi.model.CourseInput;
import com.jiankowalski.openapi.model.CourseModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course courseInputToCourse(CourseInput newCouse);

    CourseModel courseToCourseModel(Course course);

    List<CourseModel> coursesToCourseModels(List<Course> pets);

}
