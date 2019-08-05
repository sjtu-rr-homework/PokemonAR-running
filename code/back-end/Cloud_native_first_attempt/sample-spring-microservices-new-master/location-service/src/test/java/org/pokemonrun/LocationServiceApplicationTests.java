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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class LocationServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Before
    @Test
    public void testRefreshLocation() throws Exception {
        mockMvc.perform(post("/refresh/location")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"username\":\"wzra1\"," +
                        "\"longitude\":\"121.437861\"," +
                        "\"latitude\":\"31.031472\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/refresh/location")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"username\":\"wzra2\"," +
                        "\"longitude\":\"121.437862\"," +
                        "\"latitude\":\"31.031473\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/refresh/location")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"username\":\"wzra3\"," +
                        "\"longitude\":\"121.437863\"," +
                        "\"latitude\":\"31.031474\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/refresh/location")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"username\":\"wzrb1\"," +
                        "\"longitude\":\"121.443676\"," +
                        "\"latitude\":\"31.025423\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/refresh/location")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"username\":\"wzrb2\"," +
                        "\"longitude\":\"121.443677\"," +
                        "\"latitude\":\"31.025424\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/refresh/location")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"username\":\"wzrc1\"," +
                        "\"longitude\":\"121.433505\"," +
                        "\"latitude\":\"31.025\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }


    @Test
    public void testGetNearBy() throws Exception {
        mockMvc.perform(post("/get/nearby")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"username\":\"wzrd\"," +
                        "\"longitude\":\"121.437861\"," +
                        "\"latitude\":\"31.031472\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("[" +
                        "{" +
                        "\"username\":\"wzra1\"," +
                        "\"longitude\":\"121.437861\"," +
                        "\"latitude\":\"31.031472\"" +
                        "}," +
                        "{" +
                        "\"username\":\"wzra2\"," +
                        "\"longitude\":\"121.437862\"," +
                        "\"latitude\":\"31.031473\"" +
                        "}," +
                        "{" +
                        "\"username\":\"wzra3\"," +
                        "\"longitude\":\"121.437863\"," +
                        "\"latitude\":\"31.031474\"" +
                        "}" +
                        "]"));
        mockMvc.perform(post("/get/nearby")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"username\":\"wzrc1\"," +
                        "\"longitude\":\"121.43786\"," +
                        "\"latitude\":\"31.031471\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("[" +
                        "{" +
                        "\"username\":\"wzra1\"," +
                        "\"longitude\":\"121.437861\"," +
                        "\"latitude\":\"31.031472\"" +
                        "}," +
                        "{" +
                        "\"username\":\"wzra2\"," +
                        "\"longitude\":\"121.437862\"," +
                        "\"latitude\":\"31.031473\"" +
                        "}," +
                        "{" +
                        "\"username\":\"wzra3\"," +
                        "\"longitude\":\"121.437863\"," +
                        "\"latitude\":\"31.031474\"" +
                        "}," +
                        "{" +
                        "\"username\":\"wzrd\"," +
                        "\"longitude\":\"121.437861\"," +
                        "\"latitude\":\"31.031472\"" +
                        "}" +
                        "]"));
        mockMvc.perform(post("/get/nearby")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"username\":\"anonymous\"," +
                        "\"longitude\":\"0\"," +
                        "\"latitude\":\"0\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
}
