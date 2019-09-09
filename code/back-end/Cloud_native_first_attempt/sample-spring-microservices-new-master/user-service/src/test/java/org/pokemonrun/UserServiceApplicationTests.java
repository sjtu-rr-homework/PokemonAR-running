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
// IMPORTANT: Data in MongoDB must be cleared before testing Add/Get Cover!
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
        mockMvc.perform(get("/login/username/no/password/123"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void testAddExp() throws Exception {
        mockMvc.perform(get("/addexp/username/wzr/exp/10"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/addexp/username/wzr/exp/-999999"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(get("/addexp/username/no/exp/10"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(get("/getinfo/username/wzr"))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"username\":\"wzr\"," +
                        "\"star\":0," +
                        "\"email\":\"w@w.w\"," +
                        "\"exp\":10," +
                        "\"pet\":-1," +
                        "\"distance\":0.0," +
                        "\"friends\":[]" +
                        "}"));
    }

    @Test
    public void testGetUserInfo() throws Exception {
        mockMvc.perform(get("/getinfo/username/wzr"))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"username\":\"wzr\"," +
                        "\"star\":0," +
                        "\"email\":\"w@w.w\"," +
                        "\"exp\":0," +
                        "\"pet\":-1," +
                        "\"distance\":0.0," +
                        "\"friends\":[]" +
                        "}"));
        mockMvc.perform(get("/getinfo/username/no"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void testUserSetPet() throws Exception {
        mockMvc.perform(get("/setpet/username/wzr/setpet/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/getpet/username/wzr"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
        mockMvc.perform(get("/setpet/username/no/setpet/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(get("/getpet/username/no"))
                .andExpect(status().isOk())
                .andExpect(content().string("-1"));
    }

    @Test
    public void testBlockUser() throws Exception {
        mockMvc.perform(get("/blockuser/username/wzr"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/blockuser/username/no"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void testAddDistance() throws Exception {
        mockMvc.perform(get("/addDistance/username/wzr/distance/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/addDistance/username/wzr/distance/-0.1"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(get("/addDistance/username/wzr/distance/0"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(get("/addDistance/username/no/distance/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(get("/addDistance/username/no/distance/-1"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void testAddFriend() throws Exception {
        mockMvc.perform(get("/register/username/wzr2/password/rzw2/email/w2@w2.w2"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/addfriend/username/wzr/friendname/wzr2"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/getinfo/username/wzr"))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"username\":\"wzr\"," +
                        "\"star\":0," +
                        "\"email\":\"w@w.w\"," +
                        "\"exp\":0," +
                        "\"pet\":-1," +
                        "\"distance\":0.0," +
                        "\"friends\":[" +
                        "{\"friendname\":\"wzr2\"}" +
                        "]}"));
        mockMvc.perform(get("/getinfo/username/wzr2"))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"username\":\"wzr2\"," +
                        "\"star\":0," +
                        "\"email\":\"w2@w2.w2\"," +
                        "\"exp\":0," +
                        "\"pet\":-1," +
                        "\"distance\":0.0," +
                        "\"friends\":[" +
                        "{\"friendname\":\"wzr\"}" +
                        "]}"));
        mockMvc.perform(get("/addfriend/username/wzr/friendname/wzr2"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
        mockMvc.perform(get("/addfriend/username/wzr2/friendname/wzr"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void testGetAllUsername() throws Exception {
        mockMvc.perform(get("/getallusername"))
                .andExpect(status().isOk())
                .andExpect(content().string("[" +
                        "\"wzr\"]"));
        mockMvc.perform(get("/register/username/wzr2/password/rzw2/email/w2@w2.w2"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/getallusername"))
                .andExpect(status().isOk())
                .andExpect(content().string("[" +
                        "\"wzr\",\"wzr2\"]"));
    }

    @Test
    public void testAddCover() throws Exception {
        mockMvc.perform(post("/add/cover").contentType("application/json;charset=UTF-8")
                .content("{\"username\":\"wzr\"," +
                        "\"pic\":\"1234\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/add/cover").contentType("application/json;charset=UTF-8")
                .content("{\"username\":\"wzr\"," +
                        "\"pic\":\"1234\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/add/cover").contentType("application/json;charset=UTF-8")
                .content("{\"username\":\"wzr\"," +
                        "\"pic\":\"4321\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(post("/add/cover").contentType("application/json;charset=UTF-8")
                .content("{\"username\":\"nonexist_user\"," +
                        "\"pic\":\"1234\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    public void testGetCover() throws Exception {
        // data
        mockMvc.perform(post("/add/cover").contentType("application/json;charset=UTF-8")
                .content("{\"username\":\"wzr\"," +
                        "\"pic\":\"4321\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        mockMvc.perform(get("/register/username/wzr2/password/rzw/email/w2@w.w"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
        // test
        mockMvc.perform(get("/get/cover/username/wzr"))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"username\":\"wzr\"," +
                        "\"pic\":\"4321\"" +
                        "}"));
        mockMvc.perform(get("/get/cover/username/wzr2"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
        mockMvc.perform(get("/get/cover/username/nonexist_user"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
