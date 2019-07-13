package org.pokemonrun.runningrecordservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RunningRecordServiceApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRunningRecordController() throws Exception {
        mockMvc.perform(post("/running/record").contentType("application/json;charset=UTF-8")
                .content("{\"username\":\"wzr\",\"startTime\":\"12345678\",\"course\":\"08ep--w32\"," +
                        "\"duration\":\"1000\",\"courseLength\":\"4000\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

}
