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

    @Test
    public void testSetBasicRule() throws Exception {
        mockMvc.perform(put("/admin/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{\"mileageRequirement\":\"80000\"," +
                        "\"minSpeed\":\"1.8\"," +
                        "\"maxSpeed\":\"6\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(put("/admin/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{\"mileageRequirement\":\"0\"," +
                        "\"minSpeed\":\"0\"," +
                        "\"maxSpeed\":\"100\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // mileage >= 0
        mockMvc.perform(put("/admin/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{\"mileageRequirement\":\"-1\"," +
                        "\"minSpeed\":\"1.8\"," +
                        "\"maxSpeed\":\"6\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        // maxSpeed > minSpeed >= 0
        mockMvc.perform(put("/admin/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{\"mileageRequirement\":\"80000\"," +
                        "\"minSpeed\":\"6\"," +
                        "\"maxSpeed\":\"1.8\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(put("/admin/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{\"mileageRequirement\":\"80000\"," +
                        "\"minSpeed\":\"-0.1\"," +
                        "\"maxSpeed\":\"1.8\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(put("/admin/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{\"mileageRequirement\":\"80000\"," +
                        "\"minSpeed\":\"-6\"," +
                        "\"maxSpeed\":\"-0.1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(put("/admin/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{\"mileageRequirement\":\"80000\"," +
                        "\"minSpeed\":\"1.5\"," +
                        "\"maxSpeed\":\"1.5\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        // empty
        mockMvc.perform(put("/admin/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{\"mileageRequirement\":\"80000\"," +
                        "\"minSpeed\":\"\"," +
                        "\"maxSpeed\":\"\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testGetBasicRule() throws Exception {
        // empty data
        mockMvc.perform(put("/admin/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{\"mileageRequirement\":\"\"," +
                        "\"minSpeed\":\"\"," +
                        "\"maxSpeed\":\"\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/admin/rule/basic"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"mileageRequirement\":\"\"," +
                        "\"minSpeed\":\"\"," +
                        "\"maxSpeed\":\"\"}"));
        // test data 1
        mockMvc.perform(put("/admin/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{\"mileageRequirement\":\"80000\"," +
                        "\"minSpeed\":\"1.8\"," +
                        "\"maxSpeed\":\"6\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/admin/rule/basic"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"mileageRequirement\":\"80000.0\"," +
                        "\"minSpeed\":\"1.8\"," +
                        "\"maxSpeed\":\"6.0\"}"));
        // test data 2
        mockMvc.perform(put("/admin/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{\"mileageRequirement\":\"80000\"," +
                        "\"minSpeed\":\"\"," +
                        "\"maxSpeed\":\"10\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/admin/rule/basic"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"mileageRequirement\":\"80000.0\"," +
                        "\"minSpeed\":\"\"," +
                        "\"maxSpeed\":\"10.0\"}"));
        // test data 3
        mockMvc.perform(put("/admin/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{\"mileageRequirement\":\"\"," +
                        "\"minSpeed\":\"0.5\"," +
                        "\"maxSpeed\":\"\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/admin/rule/basic"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"mileageRequirement\":\"\"," +
                        "\"minSpeed\":\"0.5\"," +
                        "\"maxSpeed\":\"\"}"));
    }

    @Test
    public void testSetBorder() throws Exception {
        mockMvc.perform(put("/admin/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"2\",\"lat\":\"2\"}," +
                        "{\"lng\":\"0\",\"lat\":\"2\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(put("/admin/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"2\",\"lat\":\"-2\"}," +
                        "{\"lng\":\"-100\",\"lat\":\"3\"}," +
                        "{\"lng\":\"4\",\"lat\":\"2.55\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(put("/admin/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // -90 <= lat <= 90
        mockMvc.perform(put("/admin/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"-100\",\"lat\":\"90.1\"}," +
                        "{\"lng\":\"-110\",\"lat\":\"89\"}," +
                        "{\"lng\":\"-105\",\"lat\":\"80\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        // -180 <= lng <= 180
        mockMvc.perform(put("/admin/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"180.0\",\"lat\":\"-89.9\"}," +
                        "{\"lng\":\"175.0\",\"lat\":\"-79.9\"}," +
                        "{\"lng\":\"170.0\",\"lat\":\"-89.9\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(put("/admin/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"170.0\",\"lat\":\"89.9\"}," +
                        "{\"lng\":\"160.0\",\"lat\":\"89.9\"}," +
                        "{\"lng\":\"180.1\",\"lat\":\"79.9\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        // no intersection between non-adjacent edges
        mockMvc.perform(put("/admin/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"-1\",\"lat\":\"-1\"}," +
                        "{\"lng\":\"1\",\"lat\":\"-1\"}," +
                        "{\"lng\":\"-1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"1\",\"lat\":\"1\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(put("/admin/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"-1\",\"lat\":\"-1\"}," +
                        "{\"lng\":\"1\",\"lat\":\"-1\"}," +
                        "{\"lng\":\"1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"-1\",\"lat\":\"1\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // no duplicate between adjacent edges
        mockMvc.perform(put("/admin/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"-1\",\"lat\":\"-1\"}," +
                        "{\"lng\":\"1\",\"lat\":\"-1\"}," +
                        "{\"lng\":\"-1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"0\",\"lat\":\"0\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        // empty
        mockMvc.perform(put("/admin/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // 1 or 2 edges
        mockMvc.perform(put("/admin/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"1\",\"lat\":\"-1\"}," +
                        "{\"lng\":\"1\",\"lat\":\"1\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(put("/admin/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"1\",\"lat\":\"-1\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void testGetBorder() throws Exception {
        // test data 1
        mockMvc.perform(put("/admin/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"2\",\"lat\":\"2\"}," +
                        "{\"lng\":\"0\",\"lat\":\"2\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/admin/rule/border"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"lng\":\"1.0\",\"lat\":\"1.0\"}," +
                        "{\"lng\":\"2.0\",\"lat\":\"2.0\"}," +
                        "{\"lng\":\"0.0\",\"lat\":\"2.0\"}]"));
        // test data 2
        mockMvc.perform(put("/admin/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/admin/rule/border"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

}
