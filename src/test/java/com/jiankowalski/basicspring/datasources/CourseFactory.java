package com.jiankowalski.basicspring.datasources;

import com.jiankowalski.basicspring.domain.CategoryEnum;
import com.jiankowalski.basicspring.domain.Course;
import com.jiankowalski.openapi.model.CourseInput;

public class CourseFactory {

    public static Course criarCourseValido() {
        return new Course("Angular", CategoryEnum.FRONT_END, null);
    }

    public static CourseInput criarCourseInputValido() {
        CourseInput courseInput = new CourseInput();
        courseInput.setName("Angular");
        courseInput.setCategory(CourseInput.CategoryEnum.FRONT_END);
        return courseInput;
    }

    public static CourseInput criarCourseInputSemNome() {
        CourseInput courseInput = new CourseInput();
        courseInput.setCategory(CourseInput.CategoryEnum.FRONT_END);
        return courseInput;
    }
}
