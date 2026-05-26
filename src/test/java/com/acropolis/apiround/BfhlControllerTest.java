package com.acropolis.apiround;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BfhlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void processesExampleA() throws Exception {
        postData("""
                {"data":["a","1","334","4","R","$"]}
                """)
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "is_success": true,
                          "user_id": "mangesh_tiwari_21122005",
                          "email": "mangeshtiwari230049@acropolis.in",
                          "roll_number": "0827CS231148",
                          "odd_numbers": ["1"],
                          "even_numbers": ["334", "4"],
                          "alphabets": ["A", "R"],
                          "special_characters": ["$"],
                          "sum": "339",
                          "concat_string": "Ra"
                        }
                        """));
    }

    @Test
    void processesExampleB() throws Exception {
        postData("""
                {"data":["2","a","y","4","&","-","*","5","92","b"]}
                """)
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "odd_numbers": ["5"],
                          "even_numbers": ["2", "4", "92"],
                          "alphabets": ["A", "Y", "B"],
                          "special_characters": ["&", "-", "*"],
                          "sum": "103",
                          "concat_string": "ByA"
                        }
                        """));
    }

    @Test
    void processesExampleC() throws Exception {
        postData("""
                {"data":["A","ABCD","DOE"]}
                """)
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "odd_numbers": [],
                          "even_numbers": [],
                          "alphabets": ["A", "ABCD", "DOE"],
                          "special_characters": [],
                          "sum": "0",
                          "concat_string": "EoDdCbAa"
                        }
                        """));
    }

    @Test
    void returnsBadRequestForMissingData() throws Exception {
        postData("{}")
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.is_success").value(false));
    }

    private ResultActions postData(String json) throws Exception {
        return mockMvc.perform(post("/bfhl")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));
    }
}
