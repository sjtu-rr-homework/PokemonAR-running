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
// IMPORTANT: You need to manually clear the data in MongoDB (db=moment, col=mount)
// before test!
public class ForumServiceTests {
    @Autowired
    private MockMvc mockMvc;

    private static boolean initialized = false;

    private String getTestMoment(int i, boolean isRequest) {
        return "{" +
                "\"text\":\"test" + i + "\"," +
                (isRequest ?
                        "\"timestamp\":\"" + (111111111 + i * 2000) + "\"," :
                        "\"timestamp\":" + (111111111 + i * 2000) + ","
                ) +
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
    }

    private String getTestMoment(int i) {
        return getTestMoment(i, false);
    }

    private String getTestMoments(int imax, int imin) {
        if (imin > imax) {
            int tmp = imin;
            imin = imax;
            imax = tmp;
        }
        String res = "[";
        for (int i = imax; i >= imin; i--) {
            res += getTestMoment(i);
            if (i > imin) {
                res += ",";
            }
        }
        res += "]";
        return res;
    }

    private synchronized void tryInitialize() throws Exception {
        if (!initialized) {
            for (int i = 0; i < 25; i++) {
                mockMvc.perform(post("/add/moment").contentType("application/json;charset=UTF-8")
                        .content(getTestMoment(i, true)))
                        .andExpect(status().isOk())
                        .andExpect(content().string("true"));
            }
        }
        initialized = true;
    }

    @Test
    public void testAddMoment() throws Exception {
        tryInitialize();
    }

    @Test
    public void testGetAllMoment() throws Exception {
        tryInitialize();
        mockMvc.perform(get("/get/all/moment/timestamp/111124111"))
                .andExpect(status().isOk())
                .andExpect(content().string(getTestMoments(6, 0)));
        mockMvc.perform(get("/get/all/moment/timestamp/111171111"))
                .andExpect(status().isOk())
                .andExpect(content().string(getTestMoments(24, 15)));
    }

    @Test
    public void testRefreshMoment() throws Exception {
        tryInitialize();
        mockMvc.perform(get("/get/refresh/moment/timestamp/111110111"))
                .andExpect(status().isOk())
                .andExpect(content().string(getTestMoments(9, 0)));
        mockMvc.perform(get("/get/refresh/moment/timestamp/111140111"))
                .andExpect(status().isOk())
                .andExpect(content().string(getTestMoments(24, 15)));
        mockMvc.perform(get("/get/refresh/moment/timestamp/111150111"))
                .andExpect(status().isOk())
                .andExpect(content().string(getTestMoments(24, 20)));
    }
}
