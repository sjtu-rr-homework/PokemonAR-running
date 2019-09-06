package org.pokemonrun;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.netflix.ribbon.RibbonAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ImportAutoConfiguration({RibbonAutoConfiguration.class, FeignRibbonClientAutoConfiguration.class, FeignAutoConfiguration.class})
public class ForumServiceTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddMoment() throws Exception {
        mockMvc.perform(post("/add/moment").contentType("application/json;charset=UTF-8")
                .content("{" +
                        "\"text\":\"test\"," +
                        "\"timestamp\":\"222222222\"," +
                        "\"username\":\"wzr\"," +
                        "\"cover\":\"\"," +
                        "\"pic1\":\"1234\"," +
                        "\"pic2\":\"4321\"," +
                        "\"pic3\":\"\"," +
                        "\"pic4\":\"\"," +
                        "\"pic5\":\"\"," +
                        "\"pic6\":\"\"," +
                        "\"pic7\":\"\"," +
                        "\"pic8\":\"\"," +
                        "\"pic9\":\"\"" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    public void testGetAllMoment() throws Exception {
        for (int i = 0; i < 25; i++) {
            String tmp = "{" +
                    "\"text\":\"test" + i + "\"," +
                    "\"timestamp\":\"" + (111111111 + i * 2000) + "\"," +
                    "\"username\":\"wzr\"," +
                    "\"cover\":\"\"," +
                    "\"pic1\":\"" + (i % 9 == 0 ? "1234" : "") + "\"," +
                    "\"pic2\":\"" + (i % 9 == 1 ? "2234" : "") + "\"," +
                    "\"pic3\":\"" + (i % 9 == 2 ? "3234" : "") + "\"," +
                    "\"pic4\":\"" + (i % 9 == 3 ? "4234" : "") + "\"," +
                    "\"pic5\":\"" + (i % 9 == 4 ? "5234" : "") + "\"," +
                    "\"pic6\":\"" + (i % 9 == 5 ? "6234" : "") + "\"," +
                    "\"pic7\":\"" + (i % 9 == 6 ? "7234" : "") + "\"," +
                    "\"pic8\":\"" + (i % 9 == 7 ? "8234" : "") + "\"," +
                    "\"pic9\":\"" + (i % 9 == 8 ? "9234" : "") + "\"" +
                    "}";
            System.out.println(tmp);
            mockMvc.perform(post("/add/moment").contentType("application/json;charset=UTF-8")
                    .content(tmp))
                    .andExpect(status().isOk())
                    .andExpect(content().string("true"));
        }
        String str1 = "[", str2 = "[";
        for (int i = 6; i >= 0; i--) {
            str1 += "{" +
                    "\"text\":\"test" + i + "\"," +
                    "\"timestamp\":" + (111111111 + i * 2000) + "," +
                    "\"username\":\"wzr\"," +
                    "\"cover\":\"\"," +
                    "\"pic1\":\"" + (i % 9 == 0 ? "1234" : "") + "\"," +
                    "\"pic2\":\"" + (i % 9 == 1 ? "2234" : "") + "\"," +
                    "\"pic3\":\"" + (i % 9 == 2 ? "3234" : "") + "\"," +
                    "\"pic4\":\"" + (i % 9 == 3 ? "4234" : "") + "\"," +
                    "\"pic5\":\"" + (i % 9 == 4 ? "5234" : "") + "\"," +
                    "\"pic6\":\"" + (i % 9 == 5 ? "6234" : "") + "\"," +
                    "\"pic7\":\"" + (i % 9 == 6 ? "7234" : "") + "\"," +
                    "\"pic8\":\"" + (i % 9 == 7 ? "8234" : "") + "\"," +
                    "\"pic9\":\"" + (i % 9 == 8 ? "9234" : "") + "\"" +
                    "}" + (i == 0 ? "" : ",");
        }
        str1 += "]";
        mockMvc.perform(get("/get/all/moment/timestamp/111124111"))
                .andExpect(status().isOk())
                .andExpect(content().string(str1));
        for (int i = 24; i >= 15; i--) {
            str2 += "{" +
                    "\"text\":\"test" + i + "\"," +
                    "\"timestamp\":" + (111111111 + i * 2000) + "," +
                    "\"username\":\"wzr\"," +
                    "\"cover\":\"\"," +
                    "\"pic1\":\"" + (i % 9 == 0 ? "1234" : "") + "\"," +
                    "\"pic2\":\"" + (i % 9 == 1 ? "2234" : "") + "\"," +
                    "\"pic3\":\"" + (i % 9 == 2 ? "3234" : "") + "\"," +
                    "\"pic4\":\"" + (i % 9 == 3 ? "4234" : "") + "\"," +
                    "\"pic5\":\"" + (i % 9 == 4 ? "5234" : "") + "\"," +
                    "\"pic6\":\"" + (i % 9 == 5 ? "6234" : "") + "\"," +
                    "\"pic7\":\"" + (i % 9 == 6 ? "7234" : "") + "\"," +
                    "\"pic8\":\"" + (i % 9 == 7 ? "8234" : "") + "\"," +
                    "\"pic9\":\"" + (i % 9 == 8 ? "9234" : "") + "\"" +
                    "}" + (i == 15 ? "" : ",");
        }
        str2 += "]";
        mockMvc.perform(get("/get/all/moment/timestamp/111171111"))
                .andExpect(status().isOk())
                .andExpect(content().string(str2));
    }
}
