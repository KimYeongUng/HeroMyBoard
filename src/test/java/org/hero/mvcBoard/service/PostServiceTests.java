package org.hero.mvcBoard.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.hero.mvcBoard.domain.PostVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
public class PostServiceTests {
    @Setter(onMethod_ = {@Autowired})
    private PostService service;

    @Test
    public void testExists(){
        log.info(service);
        assertNotNull(service);
    }

    @Test
    public void testCreate() throws Exception {
        log.info("Create Register Test.....");
        PostVO vo = new PostVO();
        vo.setTitle("Test Title");
        vo.setContent("test Contents");
        vo.setWriter("Tester");
        service.register(vo);
        log.info("postNo: "+vo.getPostNo());
    }

    @Test
    public void testRead() throws Exception {
        log.info(service.read(2).toString());
    }

    @Test
    public void testDelete() throws Exception{
        log.info(service.delete(1));
    }

    @Test
    public void testUpdate() throws Exception {
        PostVO vo = new PostVO();
        vo.setPostNo(3L);
        vo.setTitle("updated title");
        vo.setContent("updated Content");
        vo.setWriter("modified");
        boolean count = service.update(vo);
        log.info("updated: "+count);
    }

    @Test
    public void getList() throws Exception {
        service.listAll().forEach(post->log.info(post));
    }
}
