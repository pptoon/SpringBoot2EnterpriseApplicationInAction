package org.fkit.springboottest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class StudentControllerTest {
	
	// 注入Spring容器
    @Autowired
    private WebApplicationContext wac;
    // MockMvc实现了对Http请求的模拟
    private MockMvc mvc;
    @Before
    public void setupMockMvc(){
    	// 初始化MockMvc对象
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); 
    }
    
    /**
     * 新增学生测试用例
     * @throws Exception
     */
    @Test
    public void addStudent() throws Exception{
        String json="{\"name\":\"孙悟空\",\"address\":\"花果山\",\"age\":\"700\",\"sex\":\"男\"}";
        mvc.perform(MockMvcRequestBuilders.post("/student/save")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    .content(json.getBytes()) //传json参数
            )
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andDo(MockMvcResultHandlers.print());
    }

    /**
     * 获取学生信息测试用例
     * @throws Exception
     */
    @Test
    public void qryStudent() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/student/get/1")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8)
            )
           .andExpect(MockMvcResultMatchers.status().isOk())
           .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("孙悟空"))
           .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("花果山"))
           .andDo(MockMvcResultHandlers.print());
    }
}