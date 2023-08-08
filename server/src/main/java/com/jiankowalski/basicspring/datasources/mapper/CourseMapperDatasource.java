package com.jiankowalski.basicspring.datasources.mapper;

import com.jiankowalski.basicspring.datasources.entity.CourseEntity;
import com.jiankowalski.basicspring.domain.Course;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseMapperDatasource {

  CourseMapperDatasource INSTANCE = Mappers.getMapper(CourseMapperDatasource.class);

  @ValueMapping(source = "FRONT-END", target = "front-end")
  @ValueMapping(source = "BACK-END", target = "back-end")
  CourseEntity courseToCourseEntity(Course course);

  @ValueMapping(source = "front-end", target = "FRONT-END")
  @ValueMapping(source = "back-end", target = "BACK-END")
  Course courseEntityToCourse(CourseEntity course);

  @ValueMapping(source = "front-end", target = "FRONT-END")
  @ValueMapping(source = "back-end", target = "BACK-END")
  List<Course> coursesToCourseModels(List<CourseEntity> courseEntities);
}
