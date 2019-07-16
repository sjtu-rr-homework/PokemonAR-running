package org.pokemonrun;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class PetServiceApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
    }

    @Test
    @Before
    public void testAddNum() throws Exception {
        mockMvc.perform(get("/user/wzr/addnum/1/num/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/user/wzr/addnum/1/num/3"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/user/wzr/addnum/2/num/2")) // vulnerable
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/user/wzr/addnum/2/num/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @Before
    public void testAddExp() throws Exception {
        mockMvc.perform(get("/user/wzr/addexp/1/exp/100"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/user/wzr/addexp/2/exp/-1")) // vulnerable
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void testQuery() throws Exception {
        mockMvc.perform(get("/user/wzr/own/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/user/wzr/own/3"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void testGetpets() throws Exception {
        mockMvc.perform(get("/user/wzr/getpets"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
        mockMvc.perform(get("/user/nonexistuser/getpets"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    @Test
    public void testGetinfo() throws Exception {
        mockMvc.perform(get("/user/wzr/getinfo/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
        mockMvc.perform(get("/user/wzr/getinfo/-1"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
        mockMvc.perform(get("/user/wzr/getinfo/3"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

}
