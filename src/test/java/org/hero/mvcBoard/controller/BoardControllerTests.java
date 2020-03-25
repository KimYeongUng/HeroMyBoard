package org.hero.mvcBoard.controller;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring-config/dispatcher-servlet.xml"})
@Log4j
@WebAppConfiguration
public class BoardControllerTests {

    @Setter(onMethod_ ={@Autowired})
    private WebApplicationContext ctx;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    public void testList() throws Exception{
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView()
                .getModelMap());
    }

    @Test
    public void testRegister() throws Exception{
        String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
                .param("title","Test New Title1")
                .param("content","Test New Content1")
                .param("writer","Test New Writer1")).andReturn().getModelAndView().getViewName();

        log.info(resultPage);
    }

    @Test
    public void testGet() throws Exception{
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/read").param("postNo","4")
        ).andReturn().getModelAndView().getModelMap());
    }

    @Test
    public void testModify() throws Exception{
        String resultpage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
                .param("postNo","4")
                .param("title","changed Title")
                .param("content","aaa")
                .param("writer","user0")).andReturn().getModelAndView().getViewName();

        log.info(resultpage);
    }

    @Test
    public void testRemove()throws Exception{
        // Check before method execute if DB post_no exists..
        String resultpage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
                .param("PostNo","4")).andReturn().getModelAndView().getViewName();

        log.info(resultpage);
    }

    @Test
    public void testURI()throws Exception{
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/board/read")
                .queryParam("postNo",12)
                .queryParam("pageAmount",20)
                .build();
        log.info("/board/read?postNo=12&pageAmount=20");
        log.info(uriComponents);
    }
}
