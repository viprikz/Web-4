import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.util.JSON;
import org.example.sweater.entitys.Users;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

public class LentaStoreControllerTest extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    //GET API test
    @Test
    public void top10Test() throws Exception {
        String uri = "/top10";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
    @Test
    public void Id() throws Exception {
        String uri = "/%2dwAD@!@#F";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String status = mvcResult.getResponse().getContentAsString();
        assertEquals("Not Found", status);
    }
    @Test
    public void search() throws Exception {
        String uri = "/search/!#@#gf312";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String status = mvcResult.getResponse().getContentAsString();
        assertEquals("Empty", status);
    }
    @Test
    public void loginTest() throws Exception {
        Users user = new Users("test1", "test2");
        JSON json = new JSON();
        String uri = "/post/login";
        /*MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();*/
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content("{\"username\": \"test1\", \"password\": \"test2\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8"))
                .andReturn();
        String status = result.getResponse().getContentAsString();
        assertEquals("Error no user", status);
    }
    @Test
    public void registerTest1() throws Exception {
        Users user = new Users("test1", "test2");
        JSON json = new JSON();
        String uri = "/post/register";
        /*MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();*/
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content("{\"username\": \"\", \"password\": \"\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8"))
                .andReturn();
        String status = result.getResponse().getContentAsString();
        assertEquals("Bad Format", status);
    }
    @Test
    public void registerTest2() throws Exception {
        Users user = new Users("test1", "test2");
        JSON json = new JSON();
        String uri = "/post/register";
        /*MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();*/
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content("{\"username\": \"admin\", \"password\": \"admin\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8"))
                .andReturn();
        String status = result.getResponse().getContentAsString();
        assertEquals("Already exist", status);
    }
    @Test
    public void createTest() throws Exception {
        Users user = new Users("test1", "test2");
        JSON json = new JSON();
        String uri = "/post/create";
        /*MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();*/
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content("{\"shortcontent\": \"\", \"content\": \"\"}")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .characterEncoding("utf-8"))
                .andReturn();
        String status = result.getResponse().getContentAsString();
        assertEquals("Bad Format", status);
    }
}