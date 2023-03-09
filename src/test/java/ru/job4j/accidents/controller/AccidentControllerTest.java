package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Test
    @WithMockUser
    public void whenUpdateAccidentPageThenReturnUpdateAccidentMessage() throws Exception {
        String accidentId = "1";
        this.mockMvc.perform(get("/formUpdateAccident")
                        .param("accidentId", accidentId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/updateAccident"));
    }

}