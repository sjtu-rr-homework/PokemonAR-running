package org.pokemonrun.ruleadmin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleadminApplicationTests {

    @Test
    public void contextLoads() {
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
}
