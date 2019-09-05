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
public class RuleadminApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSetFlags() throws Exception {
        mockMvc.perform(post("/admin/post/rule/flags")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"2\",\"lat\":\"2\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/admin/post/rule/flags")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"2\",\"lat\":\"-2\"}," +
                        "{\"lng\":\"-100\",\"lat\":\"3\"}," +
                        "{\"lng\":\"4\",\"lat\":\"2.55\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/admin/post/rule/flags")
                .contentType("application/json;charset=UTF-8")
                .content("[]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // -90 <= lat <= 90
        mockMvc.perform(post("/admin/post/rule/flags")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"-100\",\"lat\":\"90.1\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        // -180 <= lng <= 180
        mockMvc.perform(post("/admin/post/rule/flags")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"180.0\",\"lat\":\"-89.9\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/admin/post/rule/flags")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"-180.0\",\"lat\":\"89.9\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // flags out of border will be ignored when generating routes
    }

    @Test
    public void testGetFlags() throws Exception {
        // test data 1
        mockMvc.perform(post("/admin/post/rule/flags")
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
        mockMvc.perform(post("/admin/post/rule/flags")
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
        mockMvc.perform(post("/admin/post/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"minSpeed\":\"6\"," +
                        "\"maxSpeed\":\"1.8\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/admin/post/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"minSpeed\":\"100\"," +
                        "\"maxSpeed\":\"10\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // maxSpeed > minSpeed >= 0
        mockMvc.perform(post("/admin/post/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"minSpeed\":\"3\"," +
                        "\"maxSpeed\":\"9\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(post("/admin/post/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"minSpeed\":\"1.8\"," +
                        "\"maxSpeed\":\"-0.1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(post("/admin/post/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"minSpeed\":\"6\"," +
                        "\"maxSpeed\":\"0\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(post("/admin/post/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"minSpeed\":\"1.5\"," +
                        "\"maxSpeed\":\"1.5\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        // empty
        mockMvc.perform(post("/admin/post/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"minSpeed\":\"\"," +
                        "\"maxSpeed\":\"\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/admin/post/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"minSpeed\":\"\"," +
                        "\"maxSpeed\":\"1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/admin/post/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"minSpeed\":\"9\"," +
                        "\"maxSpeed\":\"\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testGetBasicRule() throws Exception {
        // empty data
        mockMvc.perform(post("/admin/post/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"minSpeed\":\"\"," +
                        "\"maxSpeed\":\"\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/admin/rule/basic"))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"minSpeed\":\"\"," +
                        "\"maxSpeed\":\"\"}"));
        // test data 1
        mockMvc.perform(post("/admin/post/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"minSpeed\":\"6\"," +
                        "\"maxSpeed\":\"1.8\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/admin/rule/basic"))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"minSpeed\":\"6.0\"," +
                        "\"maxSpeed\":\"1.8\"}"));
        // test data 2
        mockMvc.perform(post("/admin/post/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"minSpeed\":\"\"," +
                        "\"maxSpeed\":\"1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/admin/rule/basic"))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"minSpeed\":\"\"," +
                        "\"maxSpeed\":\"1.0\"}"));
        // test data 3
        mockMvc.perform(post("/admin/post/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"minSpeed\":\"10.5\"," +
                        "\"maxSpeed\":\"\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/admin/rule/basic"))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"minSpeed\":\"10.5\"," +
                        "\"maxSpeed\":\"\"}"));
    }

    @Test
    public void testSetBorder() throws Exception {
        mockMvc.perform(post("/admin/post/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"2\",\"lat\":\"2\"}," +
                        "{\"lng\":\"0\",\"lat\":\"2\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/admin/post/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"2\",\"lat\":\"-2\"}," +
                        "{\"lng\":\"-100\",\"lat\":\"3\"}," +
                        "{\"lng\":\"4\",\"lat\":\"2.55\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/admin/post/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // -90 <= lat <= 90
        mockMvc.perform(post("/admin/post/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"-100\",\"lat\":\"90.1\"}," +
                        "{\"lng\":\"-110\",\"lat\":\"89\"}," +
                        "{\"lng\":\"-105\",\"lat\":\"80\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        // -180 <= lng <= 180
        mockMvc.perform(post("/admin/post/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"180.0\",\"lat\":\"-89.9\"}," +
                        "{\"lng\":\"175.0\",\"lat\":\"-79.9\"}," +
                        "{\"lng\":\"170.0\",\"lat\":\"-89.9\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/admin/post/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"170.0\",\"lat\":\"89.9\"}," +
                        "{\"lng\":\"160.0\",\"lat\":\"89.9\"}," +
                        "{\"lng\":\"180.1\",\"lat\":\"79.9\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        // no intersection between non-adjacent edges
        mockMvc.perform(post("/admin/post/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"-1\",\"lat\":\"-1\"}," +
                        "{\"lng\":\"1\",\"lat\":\"-1\"}," +
                        "{\"lng\":\"-1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"1\",\"lat\":\"1\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(post("/admin/post/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"-1\",\"lat\":\"-1\"}," +
                        "{\"lng\":\"1\",\"lat\":\"-1\"}," +
                        "{\"lng\":\"1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"-1\",\"lat\":\"1\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // no duplicate between adjacent edges
        mockMvc.perform(post("/admin/post/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"-1\",\"lat\":\"-1\"}," +
                        "{\"lng\":\"1\",\"lat\":\"-1\"}," +
                        "{\"lng\":\"-1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"0\",\"lat\":\"0\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        // empty
        mockMvc.perform(post("/admin/post/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // 1 or 2 edges
        mockMvc.perform(post("/admin/post/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"1\",\"lat\":\"-1\"}," +
                        "{\"lng\":\"1\",\"lat\":\"1\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(post("/admin/post/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"1\",\"lat\":\"-1\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void testGetBorder() throws Exception {
        // test data 1
        mockMvc.perform(post("/admin/post/rule/border")
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
        mockMvc.perform(post("/admin/post/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/admin/rule/border"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

    /*@Test
    // use POSTMAN to test
    public void testGenerateRoute() throws Exception {
        // test data 1
        mockMvc.perform(post("/admin/post/rule/border")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"0\",\"lat\":\"0\"}," +
                        "{\"lng\":\"10\",\"lat\":\"0\"}," +
                        "{\"lng\":\"10\",\"lat\":\"10\"}," +
                        "{\"lng\":\"0\",\"lat\":\"10\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/admin/post/rule/flags")
                .contentType("application/json;charset=UTF-8")
                .content("[{\"lng\":\"1\",\"lat\":\"1\"}," +
                        "{\"lng\":\"5\",\"lat\":\"5\"}," +
                        "{\"lng\":\"5\",\"lat\":\"5\"}," +
                        "{\"lng\":\"9\",\"lat\":\"9\"}," +
                        "{\"lng\":\"-1\",\"lat\":\"5\"}," +
                        "{\"lng\":\"1\",\"lat\":\"11\"}]"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/rule/route/start_lng/1/start_lat/2"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/rule/route/start_lng/1/start_lat/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"lng\":\"1.0\",\"lat\":\"1.0\"}," +
                        "{\"lng\":\"2.0\",\"lat\":\"2.0\"}," +
                        "{\"lng\":\"0.0\",\"lat\":\"2.0\"}]"));
        mockMvc.perform(get("/rule/route/start_lng/-1/start_lat/-2"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
        mockMvc.perform(get("/rule/route/start_lng/-1/start_lat/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }*/

    @Test
    public void testRecordCampusRunning() throws Exception {
        mockMvc.perform(post("/rule/campus/semester")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"mileageGoal\":\"8888\"," +
                        "\"endTime\":\"2019-09-31T23:59\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // new user
        mockMvc.perform(post("/rule/campus/user/1/length/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // existing user
        mockMvc.perform(post("/rule/campus/user/1/length/2.1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // length < 0
        mockMvc.perform(post("/rule/campus/user/2/length/-1"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(post("/rule/campus/user/1/length/-0.1"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        // empty username
        mockMvc.perform(post("/rule/campus/user//length/2"))
                .andExpect(status().is4xxClientError());
        // empty length
        mockMvc.perform(post("/rule/campus/user/1/length/"))
                .andExpect(status().is4xxClientError());
        // empty username & length
        mockMvc.perform(post("/rule/campus/user//length/"))
                .andExpect(status().is4xxClientError());
        // not in the time window
        /*mockMvc.perform(put("/rule/campus/semester")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"mileageGoal\":\"8888\"," +
                        "\"endTime\":\"2019-07-02T23:59\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/rule/campus/user/1/length/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));*/
    }

    @Test
    public void testGetCampusRunningInfo() throws Exception {
        mockMvc.perform(post("/rule/campus/semester")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"mileageGoal\":\"8888\"," +
                        "\"endTime\":\"2019-09-31T23:59\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // test data
        mockMvc.perform(post("/admin/post/rule/basic")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"minSpeed\":\"6\"," +
                        "\"maxSpeed\":\"1.8\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/rule/campus/user/_1/length/10"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/rule/campus/user/_1/length/3"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/rule/campus/user/_1/length/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/rule/campus/user/_2/length/10"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/rule/campus/user/_2/length/-5"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(post("/rule/campus/user/_3/length/-1"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(post("/rule/campus/user/_4/length/-10"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(post("/rule/campus/user/_4/length/5"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // test
        mockMvc.perform(get("/rule/campus/user/_1"))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"mileage\":\"15.0\"," +
                        "\"mileageGoal\":\"8888.0\"}"));
        mockMvc.perform(get("/rule/campus/user/_2"))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"mileage\":\"10.0\"," +
                        "\"mileageGoal\":\"8888.0\"}"));
        mockMvc.perform(get("/rule/campus/user/_3"))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"mileage\":\"0.0\"," +
                        "\"mileageGoal\":\"8888.0\"}"));
        mockMvc.perform(get("/rule/campus/user/_4"))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"mileage\":\"5.0\"," +
                        "\"mileageGoal\":\"8888.0\"}"));
        mockMvc.perform(get("/rule/campus/user/_100"))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"mileage\":\"0.0\"," +
                        "\"mileageGoal\":\"8888.0\"}"));
    }

    @Transactional
    public void createSemester1() throws Exception {
        mockMvc.perform(post("/rule/campus/semester")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"mileageGoal\":\"8888\"," +
                        "\"endTime\":\"2019-09-31T23:59\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
    @Transactional
    public void createSemester2() throws Exception {
        // negative mileage goal
        mockMvc.perform(post("/rule/campus/semester")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"mileageGoal\":\"-1\"," +
                        "\"endTime\":\"2019-09-31T23:59\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
    @Transactional
    public void createSemester3() throws Exception {
        // past time as end time
        mockMvc.perform(post("/rule/campus/semester")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"mileageGoal\":\"1\"," +
                        "\"endTime\":\"2000-01-01T23:59\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void testBeginNewSemester() throws Exception {
        createSemester1();
        createSemester2();
        createSemester3();
    }

    @Test
    public void testModifySemesterInfo() throws Exception {
        mockMvc.perform(post("/rule/campus/semester")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"mileageGoal\":\"8888\"," +
                        "\"endTime\":\"2019-09-31T23:59\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(put("/rule/campus/semester")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"mileageGoal\":\"1\"," +
                        "\"endTime\":\"2019-09-31T23:59\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // negative mileage goal
        mockMvc.perform(put("/rule/campus/semester")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"mileageGoal\":\"-1\"," +
                        "\"endTime\":\"2019-09-31T23:59\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        // past time as end time
        mockMvc.perform(put("/rule/campus/semester")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"mileageGoal\":\"1\"," +
                        "\"endTime\":\"2000-09-31T23:59\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(put("/rule/campus/semester")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"mileageGoal\":\"1\"," +
                        "\"endTime\":\"2019-07-21T23:59\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    /*@Test
    // use Postman to test this part
    public void testGetSemesterDetails() throws Exception {
        // specify semester info
        mockMvc.perform(post("/rule/campus/semester")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"mileageGoal\":\"80000\"," +
                        "\"endTime\":\"2019-09-30T23:59\"}"))
                .andExpect(status().isOk());
        mockMvc.perform(put("/rule/campus/semester")
                .contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"mileageGoal\":\"80000\"," +
                        "\"endTime\":\"2019-09-30T23:59\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // test GET
        mockMvc.perform(get("/rule/campus/semester"))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"mileageGoal\":\"\"," +
                        "\"endTime\":\"\"}"));
    }*/


}
