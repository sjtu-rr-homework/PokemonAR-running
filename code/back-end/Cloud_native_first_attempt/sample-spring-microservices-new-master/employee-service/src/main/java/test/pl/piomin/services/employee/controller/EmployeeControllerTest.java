package test.pl.piomin.services.employee.controller;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.piomin.services.employee.EmployeeApplication;
import pl.piomin.services.employee.repository.EmployeeRepository;

/**
 * EmployeeController Tester.
 *
 * @author <Authors name>
 * @since <pre>ÆßÔÂ 8, 2019</pre>
 * @version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {EmployeeApplication.class})
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    EmployeeRepository repository;

    private MockMvc mockMvc;

    @Before
    public void before() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: add(@RequestBody Employee employee)
     *
     */
    @Test
    public void testAdd() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/");
        mockHttpServletRequestBuilder.contentType("application/json;charset=UTF-8")
                .content("{\"organizationId\":\"1\",\"departmentId\":\"1\",\"name\":\"wzr\",\"age\":\"21\",\"position\":\"tech leader\"}");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":11,\"organizationId\":1,\"departmentId\":1,\"name\":\"wzr\",\"age\":21,\"position\":\"tech leader\"}"));
    }

    /**
     *
     * Method: findById(@PathVariable("id") Long id)
     *
     */
    @Test
    public void testFindById() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/1");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"organizationId\":1,\"departmentId\":1,\"name\":\"John Smith\",\"age\":34,\"position\":\"Analyst\"}"));
    }

    /**
     *
     * Method: findAll()
     *
     */
    @Test
    public void testFindAll() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"organizationId\":1,\"departmentId\":1,\"name\":\"John Smith\",\"age\":34,\"position\":\"Analyst\"},{\"id\":2,\"organizationId\":1,\"departmentId\":1,\"name\":\"Darren Hamilton\",\"age\":37,\"position\":\"Manager\"},{\"id\":3,\"organizationId\":1,\"departmentId\":1,\"name\":\"Tom Scott\",\"age\":26,\"position\":\"Developer\"},{\"id\":4,\"organizationId\":1,\"departmentId\":2,\"name\":\"Anna London\",\"age\":39,\"position\":\"Analyst\"},{\"id\":5,\"organizationId\":1,\"departmentId\":2,\"name\":\"Patrick Dempsey\",\"age\":27,\"position\":\"Developer\"},{\"id\":6,\"organizationId\":2,\"departmentId\":3,\"name\":\"Kevin Price\",\"age\":38,\"position\":\"Developer\"},{\"id\":7,\"organizationId\":2,\"departmentId\":3,\"name\":\"Ian Scott\",\"age\":34,\"position\":\"Developer\"},{\"id\":8,\"organizationId\":2,\"departmentId\":3,\"name\":\"Andrew Campton\",\"age\":30,\"position\":\"Manager\"},{\"id\":9,\"organizationId\":2,\"departmentId\":4,\"name\":\"Steve Franklin\",\"age\":25,\"position\":\"Developer\"},{\"id\":10,\"organizationId\":2,\"departmentId\":4,\"name\":\"Elisabeth Smith\",\"age\":30,\"position\":\"Developer\"}]"));
    }

    /**
     *
     * Method: findByDepartment(@PathVariable("departmentId") Long departmentId)
     *
     */
    @Test
    public void testFindByDepartment() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/department/1");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"organizationId\":1,\"departmentId\":1,\"name\":\"John Smith\",\"age\":34,\"position\":\"Analyst\"},{\"id\":2,\"organizationId\":1,\"departmentId\":1,\"name\":\"Darren Hamilton\",\"age\":37,\"position\":\"Manager\"},{\"id\":3,\"organizationId\":1,\"departmentId\":1,\"name\":\"Tom Scott\",\"age\":26,\"position\":\"Developer\"}]"));
        mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/department/10");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     *
     * Method: findByOrganization(@PathVariable("organizationId") Long organizationId)
     *
     */
    @Test
    public void testFindByOrganization() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/organization/1");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"organizationId\":1,\"departmentId\":1,\"name\":\"John Smith\",\"age\":34,\"position\":\"Analyst\"},{\"id\":2,\"organizationId\":1,\"departmentId\":1,\"name\":\"Darren Hamilton\",\"age\":37,\"position\":\"Manager\"},{\"id\":3,\"organizationId\":1,\"departmentId\":1,\"name\":\"Tom Scott\",\"age\":26,\"position\":\"Developer\"},{\"id\":4,\"organizationId\":1,\"departmentId\":2,\"name\":\"Anna London\",\"age\":39,\"position\":\"Analyst\"},{\"id\":5,\"organizationId\":1,\"departmentId\":2,\"name\":\"Patrick Dempsey\",\"age\":27,\"position\":\"Developer\"}]"));
        mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/organization/10");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


} 
