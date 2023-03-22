package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Article;
import ru.job4j.accidents.service.AccidentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Accident controller test class
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 09.03.23
 */
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AccidentControllerTest {

    /**
     * Mock object
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Mock service
     */
    @MockBean
    private AccidentService accidentService;

    @Test
    @WithMockUser
    public void whenAllAccidentsPageThenReturnAllAccidentsMessage() throws Exception {
        this.mockMvc.perform(get("/allAccidents"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/allAccidents"));
    }

    @Test
    @WithMockUser
    public void whenAddAccidentPageThenReturnAddAccidentMessage() throws Exception {
        this.mockMvc.perform(get("/formAddAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/addAccident"));
    }

    /**
     * Disabled. Need to fix
     */
    @Disabled
    @Test
    @WithMockUser
    public void whenUpdateAccidentPageThenReturnUpdateAccidentMessage() throws Exception {
        /*Accident accident = Accident.of()
                .id(25)
                .name("crash")
                .description("desc")
                .address("address")
                .type(new AccidentType(1, "type"))
                .articles(Set.of(new Article(1, "article")))
                .build();
        accidentService.saveAccident(accident);*/
        this.mockMvc.perform(get("/formUpdateAccident")
                        .param("accidentId", "25"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/updateAccident"));
    }

    @Test
    @WithMockUser
    public void whenSaveAccident() throws Exception {
        this.mockMvc.perform(post("/addAccident")
                        .param("id", "1")
                        .param("name", "crash")
                        .param("description", "desc")
                        .param("address", "address")
                        .param("type.id", "1")
                        .param("aIds", "2", "3"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).saveAccident(argument.capture());
        assertEquals(argument.getValue().getId(), 1);
        assertEquals(argument.getValue().getName(), "crash");
        assertEquals(argument.getValue().getDescription(), "desc");
        assertEquals(argument.getValue().getAddress(), "address");
        assertEquals(argument.getValue().getType().getId(), 1);
        assertEquals(argument.getValue().getArticles().stream().map(Article::getId).toList(), List.of(2, 3));
    }

    @Test
    @WithMockUser
    public void whenUpdateAccident() throws Exception {
        this.mockMvc.perform(post("/addAccident")
                        .param("id", "1")
                        .param("name", "crash")
                        .param("description", "desc")
                        .param("address", "address")
                        .param("type.id", "1")
                        .param("aIds", "2", "3"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argument1 = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).saveAccident(argument1.capture());
        this.mockMvc.perform(post("/updateAccident")
                        .param("id", "1")
                        .param("name", "crash2")
                        .param("description", "desc2")
                        .param("address", "address2")
                        .param("type.id", "2")
                        .param("aIds", "4", "5"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argument2 = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).updateAccident(argument2.capture());
        assertEquals(argument2.getValue().getId(), 1);
        assertEquals(argument2.getValue().getName(), "crash2");
        assertEquals(argument2.getValue().getDescription(), "desc2");
        assertEquals(argument2.getValue().getAddress(), "address2");
        assertEquals(argument2.getValue().getType().getId(), 2);
        assertEquals(argument2.getValue().getArticles().stream().map(Article::getId).sorted().toList(), List.of(4, 5));
    }

}