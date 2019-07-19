package org.pokemonrun.ruleadmin;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RuleadminApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSetFlags() throws Exception {
        mockMvc.perform(put("/admin/rule/flags")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"2\",\"lat\":\"2\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(put("/admin/rule/flags")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"2\",\"lat\":\"-2\"}," +
                        "{\"lng\":\"-100\",\"lat\":\"3\"}," +
                        "{\"lng\":\"4\",\"lat\":\"2.55\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(put("/admin/rule/flags")
                .contentType("application/json;charset=UTF-8")
                .content("[]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // -90 <= lat <= 90
        mockMvc.perform(put("/admin/rule/flags")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"-100\",\"lat\":\"90.1\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        // -180 <= lng <= 180
        mockMvc.perform(put("/admin/rule/flags")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"180.0\",\"lat\":\"-89.9\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(put("/admin/rule/flags")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"-180.0\",\"lat\":\"89.9\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testGetFlags() throws Exception {
        // test data 1
        mockMvc.perform(put("/admin/rule/flags")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"-180.0\",\"lat\":\"89.9\"}," +
                        "{\"lng\":\"-135.8\",\"lat\":\"39.2\"}," +
                        "{\"lng\":\"102\",\"lat\":\"-23.1\"}," +
                        "{\"lng\":\"121.331\",\"lat\":\"31.15\"}," +
                        "{\"lng\":\"0\",\"lat\":\"45.131\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/admin/rule/flags"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"lng\":\"-180.0\",\"lat\":\"89.9\"}," +
                        "{\"lng\":\"-135.8\",\"lat\":\"39.2\"}," +
                        "{\"lng\":\"102.0\",\"lat\":\"-23.1\"}," +
                        "{\"lng\":\"121.331\",\"lat\":\"31.15\"}," +
                        "{\"lng\":\"0.0\",\"lat\":\"45.131\"}]"));
        // test data 2
        mockMvc.perform(put("/admin/rule/flags")
                .contentType("application/json;charset=UTF-8")
                .content("[]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/admin/rule/flags"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

}
