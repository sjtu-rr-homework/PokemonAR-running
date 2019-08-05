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
public class RecordServiceApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
    }

    @Test
    @Before
    public void testSaveRecord() throws Exception {
        mockMvc.perform(post("/running/record").contentType("application/json;charset=UTF-8")
                .content("{\"username\":\"wzr\",\"startTime\":\"12345678\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633844375,\\\"lng\\\":121.44293187314733}," +
                        "{\\\"lat\\\":31.022460874978126,\\\"lng\\\":121.44301228726991}]\"," +
                        "\"duration\":\"1000\",\"courseLength\":\"4000\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/running/record").contentType("application/json;charset=UTF-8")
                .content("{\"username\":\"wzr\",\"startTime\":\"22225678\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633845375,\\\"lng\\\":121.44293187315733}," +
                        "{\\\"lat\\\":31.022460874979126,\\\"lng\\\":121.44301228727991}]\"," +
                        "\"duration\":\"1000\",\"courseLength\":\"4000\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/running/record").contentType("application/json;charset=UTF-8")
                .content("{\"username\":\"wzr\",\"startTime\":\"33325678\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633846375,\\\"lng\\\":121.44293187316733}," +
                        "{\\\"lat\\\":31.022460874970126,\\\"lng\\\":121.44301228726991}]\"," +
                        "\"duration\":\"666\",\"courseLength\":\"3333\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/running/record").contentType("application/json;charset=UTF-8")
                .content("{\"username\":\"wzr\",\"startTime\":\"44325644\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633844375,\\\"lng\\\":121.44293187314733}," +
                        "{\\\"lat\\\":31.022460874974126,\\\"lng\\\":121.44301228724991}]\"," +
                        "\"duration\":\"668\",\"courseLength\":\"3344\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/running/record").contentType("application/json;charset=UTF-8")
                .content("{\"username\":\"wzr\",\"startTime\":\"55325644\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633845375,\\\"lng\\\":121.44293187355733}," +
                        "{\\\"lat\\\":31.022460874975126,\\\"lng\\\":121.44301228725991}]\"," +
                        "\"duration\":\"555\",\"courseLength\":\"3000\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/running/record").contentType("application/json;charset=UTF-8")
                .content("{\"username\":\"wzr\",\"startTime\":\"65325644\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633846375,\\\"lng\\\":121.44293187356733}," +
                        "{\\\"lat\\\":31.022460874976626,\\\"lng\\\":121.44301228725691}]\"," +
                        "\"duration\":\"666\",\"courseLength\":\"3600\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testGetUserRecordList() throws Exception {
        mockMvc.perform(get("/running/record/user/wzr"))
                .andExpect(status().isOk())
                .andExpect(content().string("[" +
                        "{\"username\":\"wzr\",\"startTime\":\"65325644\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633846375,\\\"lng\\\":121.44293187356733}," +
                        "{\\\"lat\\\":31.022460874976626,\\\"lng\\\":121.44301228725691}]\"," +
                        "\"duration\":\"666\",\"courseLength\":\"3600.0\"}," +
                        "{\"username\":\"wzr\",\"startTime\":\"55325644\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633845375,\\\"lng\\\":121.44293187355733}," +
                        "{\\\"lat\\\":31.022460874975126,\\\"lng\\\":121.44301228725991}]\"," +
                        "\"duration\":\"555\",\"courseLength\":\"3000.0\"}," +
                        "{\"username\":\"wzr\",\"startTime\":\"44325644\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633844375,\\\"lng\\\":121.44293187314733}," +
                        "{\\\"lat\\\":31.022460874974126,\\\"lng\\\":121.44301228724991}]\"," +
                        "\"duration\":\"668\",\"courseLength\":\"3344.0\"}," +
                        "{\"username\":\"wzr\",\"startTime\":\"33325678\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633846375,\\\"lng\\\":121.44293187316733}," +
                        "{\\\"lat\\\":31.022460874970126,\\\"lng\\\":121.44301228726991}]\"," +
                        "\"duration\":\"666\",\"courseLength\":\"3333.0\"}," +
                        "{\"username\":\"wzr\",\"startTime\":\"22225678\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633845375,\\\"lng\\\":121.44293187315733}," +
                        "{\\\"lat\\\":31.022460874979126,\\\"lng\\\":121.44301228727991}]\"," +
                        "\"duration\":\"1000\",\"courseLength\":\"4000.0\"}," +
                        "{\"username\":\"wzr\",\"startTime\":\"12345678\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633844375,\\\"lng\\\":121.44293187314733}," +
                        "{\\\"lat\\\":31.022460874978126,\\\"lng\\\":121.44301228726991}]\"," +
                        "\"duration\":\"1000\",\"courseLength\":\"4000.0\"}" +
                        "]"));
    }

    @Test
    public void testGetUserRecordListPage() throws Exception {
        mockMvc.perform(get("/running/record/user/wzr/page/0"))
                .andExpect(status().isOk())
                .andExpect(content().string("[" +
                        "{\"username\":\"wzr\",\"startTime\":\"65325644\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633846375,\\\"lng\\\":121.44293187356733}," +
                        "{\\\"lat\\\":31.022460874976626,\\\"lng\\\":121.44301228725691}]\"," +
                        "\"duration\":\"666\",\"courseLength\":\"3600.0\"},"+
                        "{\"username\":\"wzr\",\"startTime\":\"55325644\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633845375,\\\"lng\\\":121.44293187355733}," +
                        "{\\\"lat\\\":31.022460874975126,\\\"lng\\\":121.44301228725991}]\"," +
                        "\"duration\":\"555\",\"courseLength\":\"3000.0\"},"+
                        "{\"username\":\"wzr\",\"startTime\":\"44325644\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633844375,\\\"lng\\\":121.44293187314733}," +
                        "{\\\"lat\\\":31.022460874974126,\\\"lng\\\":121.44301228724991}]\"," +
                        "\"duration\":\"668\",\"courseLength\":\"3344.0\"},"+
                        "{\"username\":\"wzr\",\"startTime\":\"33325678\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633846375,\\\"lng\\\":121.44293187316733}," +
                        "{\\\"lat\\\":31.022460874970126,\\\"lng\\\":121.44301228726991}]\"," +
                        "\"duration\":\"666\",\"courseLength\":\"3333.0\"},"+
                        "{\"username\":\"wzr\",\"startTime\":\"22225678\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633845375,\\\"lng\\\":121.44293187315733}," +
                        "{\\\"lat\\\":31.022460874979126,\\\"lng\\\":121.44301228727991}]\"," +
                        "\"duration\":\"1000\",\"courseLength\":\"4000.0\"}]"));
        mockMvc.perform(get("/running/record/user/wzr/page/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"username\":\"wzr\",\"startTime\":\"12345678\"," +
                        "\"course\":\"[{\\\"lat\\\":31.022394633844375,\\\"lng\\\":121.44293187314733}," +
                        "{\\\"lat\\\":31.022460874978126,\\\"lng\\\":121.44301228726991}]\"," +
                        "\"duration\":\"1000\",\"courseLength\":\"4000.0\"}]"));
        mockMvc.perform(get("/running/record/user/wzr/page/2"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }

}
