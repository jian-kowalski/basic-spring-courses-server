package com.jiankowalski.basicspring.transportlayers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jiankowalski.basicspring.datasources.CourseFactory;
import com.jiankowalski.basicspring.domain.exception.NotFoundException;
import com.jiankowalski.basicspring.interactors.CourseService;
import com.jiankowalski.openapi.model.CourseInput;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

    private final String END_POINT_COURSE = "/courses";
    private final String END_POINT_COURSE_ID = END_POINT_COURSE + "/{id}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CourseService courseService;

    @Test
    void deveRetornar201AoCriarComSucesso() throws Exception {
        CourseInput courseInput = CourseFactory.criarCourseInputValido();
        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_COURSE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseInput))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void deveRetornar400AoCriarComSemNome() throws Exception {
        CourseInput courseInput = CourseFactory.criarCourseInputSemNome();
        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_COURSE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(courseInput))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarExceptionAoConsultarCourseInexistente() throws Exception {
        Mockito.when(courseService.getCourse(Mockito.anyLong())).thenThrow(new NotFoundException("", 1L));
        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_COURSE_ID, -1)).andExpect(status().isNotFound());
    }

    @Test
    void deveRetornarExceptionBadRequestAoConsultarCourseInexistente() throws Exception {
        Mockito.when(courseService.getCourse(Mockito.anyLong())).thenThrow(new NotFoundException("", 1L));
        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_COURSE_ID, "COURSE")).andExpect(status().isBadRequest());
    }

    @Test
    void deveRetornarOKQuandoCourseExistente() throws Exception {
        Mockito.when(courseService.getCourse(Mockito.anyLong())).thenReturn(CourseFactory.criarCourseValido());
        mockMvc.perform(MockMvcRequestBuilders.get(END_POINT_COURSE_ID, 1)).andExpect(status().isOk());
    }

    @Test
    void deveExcluirCourseComSucesso() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(END_POINT_COURSE_ID, 1)).andExpect(status().isNoContent());
    }

}