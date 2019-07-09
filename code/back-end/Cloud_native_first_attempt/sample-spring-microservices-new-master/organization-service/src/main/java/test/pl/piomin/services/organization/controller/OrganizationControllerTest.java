package test.pl.piomin.services.organization.controller;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.piomin.services.organization.OrganizationApplication;
import pl.piomin.services.organization.client.DepartmentClient;
import pl.piomin.services.organization.client.EmployeeClient;
import pl.piomin.services.organization.repository.OrganizationRepository;
/**
 * OrganizationController Tester.
 *
 * @author <Authors name>
 * @since <pre>ÆßÔÂ 8, 2019</pre>
 * @version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = {OrganizationApplication.class})
@AutoConfigureMockMvc
public class OrganizationControllerTest {
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    OrganizationRepository repository;
    @Autowired
    DepartmentClient departmentClient;
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
     * Method: add(@RequestBody Organization organization)
     *
     */
    @Test
    public void testAdd() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/");
        mockHttpServletRequestBuilder.contentType("application/json;charset=UTF-8")
                .content("{\"name\":\"SJTU\",\"address\":\"800 Dongchuan Rd., Shanghai, China\"}");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":3,\"name\":\"SJTU\",\"address\":\"800 Dongchuan Rd., Shanghai, China\",\"departments\":[],\"employees\":[]}"));
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
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"name\":\"Microsoft\",\"address\":\"Redmond, Washington, USA\",\"departments\":[],\"employees\":[]},{\"id\":2,\"name\":\"Oracle\",\"address\":\"Redwood City, California, USA\",\"departments\":[],\"employees\":[]}]"));
    }

    /**
     *
     * Method: findById(@PathVariable("id") Long id)
     *
     */
    @Test
    public void testFindById() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/2");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":2,\"name\":\"Oracle\",\"address\":\"Redwood City, California, USA\",\"departments\":[],\"employees\":[]}"));

        mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/4");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    /**
     *
     * Method: findByIdWithDepartments(@PathVariable("id") Long id)
     *
     */
    @Test
    public void testFindByIdWithDepartments() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/2/with-departments");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":2,\"name\":\"Oracle\",\"address\":\"Redwood City, California, USA\",\"departments\":null,\"employees\":[]}"));

        mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/4/with-departments");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    /**
     *
     * Method: findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id)
     *
     */
    @Test
    public void testFindByIdWithDepartmentsAndEmployees() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/1/with-departments-and-employees");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Microsoft\",\"address\":\"Redmond, Washington, USA\",\"departments\":null,\"employees\":[]}"));

        mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/6/with-departments-and-employees");
        mockMvc.perform(mockHttpServletRequestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(""));

    }

    /**
     *
     * Method: findByIdWithEmployees(@PathVariable("id") Long id)
     *
     */
    @Test
    public void testFindByIdWithEmployees() throws Exception {
//TODO: Test goes here... 
    }


} 
