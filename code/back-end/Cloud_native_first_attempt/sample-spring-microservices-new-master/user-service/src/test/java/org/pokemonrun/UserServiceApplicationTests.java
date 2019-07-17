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
public class UserServiceApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
    }

    @Before
    public void testRegister() throws Exception {
        mockMvc.perform(get("/register/username/wzr/password/rzw/email/w@w.w"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/register/username//password/rzw/email/w@w.w"))
                .andExpect(status().isNotFound());
        mockMvc.perform(get("/register/username/wzr2/password//email/w@w.w"))
                .andExpect(status().isNotFound());
        mockMvc.perform(get("/register/username/wzr/password/rzw/email/w@w.w"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(get("/login/username/wzr/password/123"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(get("/login/username//password/123"))
                .andExpect(status().isNotFound());
        mockMvc.perform(get("/login/username/wzr/password/rzw"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/login/username/wzr2/password/123"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }


}
