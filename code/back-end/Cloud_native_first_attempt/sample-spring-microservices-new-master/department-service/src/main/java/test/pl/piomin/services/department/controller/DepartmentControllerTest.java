package test.pl.piomin.services.department.controller;

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
import pl.piomin.services.department.DepartmentApplication;
import pl.piomin.services.department.client.EmployeeClient;
import pl.piomin.services.department.repository.DepartmentRepository;

/**
 * DepartmentController Tester.
 *
 * @author <Authors name>
 * @since <pre>ÆßÔÂ 8, 2019</pre>
 * @version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {DepartmentApplication.class})
@AutoConfigureMockMvc
public class DepartmentControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    DepartmentRepository repository;
    @Autowired
    EmployeeClient employeeClient;

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
     * Method: add(@RequestBody Department department)
     *
     */
    @Test
    public void testAdd() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/");
        mockHttpServletRequestBuilder.contentType("application/json;charset=UTF-8")
                .content("{\"organizationId\":\"1\",\"name\":\"Human Resource\",\"employees\":[]}");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":5,\"organizationId\":1,\"name\":\"Human Resource\",\"employees\":[]}"));
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
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":5,\"organizationId\":1,\"name\":\"Human Resource\",\"employees\":[]}"));

        mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/10");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));
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
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"organizationId\":1,\"name\":\"Development\",\"employees\":[]},{\"id\":2,\"organizationId\":1,\"name\":\"Operations\",\"employees\":[]},{\"id\":3,\"organizationId\":2,\"name\":\"Development\",\"employees\":[]},{\"id\":4,\"organizationId\":2,\"name\":\"Operations\",\"employees\":[]}]"));
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
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"organizationId\":1,\"name\":\"Development\",\"employees\":[]},{\"id\":2,\"organizationId\":1,\"name\":\"Operations\",\"employees\":[]}]"));

        mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/organization/5");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     *
     * Method: findByOrganizationWithEmployees(@PathVariable("organizationId") Long organizationId)
     *
     */
    @Test
    public void testFindByOrganizationWithEmployees() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/organization/1/with-employees");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"organizationId\":1,\"name\":\"Development\",\"employees\":[]},{\"id\":2,\"organizationId\":1,\"name\":\"Operations\",\"employees\":[]}]"));

        mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/organization/5/with-employees");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


} 
