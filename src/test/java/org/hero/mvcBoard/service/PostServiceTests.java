package org.hero.mvcBoard.service;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.hero.mvcBoard.commons.paging.Criteria;
import org.hero.mvcBoard.commons.paging.SearchCriteria;
import org.hero.mvcBoard.domain.PostVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml",
        "file:src/main/webapp/WEB-INF/spring-config/dispatcher-servlet.xml"})
@WebAppConfiguration
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
    public void testGetPage() throws Exception {
        Criteria cri = new Criteria();
        cri.setPage(3);
        cri.setPageAmount(20);
        List<PostVO> list = service.listPaging(cri);

        for(PostVO vo : list){
            log.info(vo);
        }

    }

    @Test
    public void testRead() throws Exception {
        log.info(service.read(2L).toString());
    }

    @Test
    public void testDelete() throws Exception{
        log.info(service.delete(1L));
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
    public void testCount() throws Exception {
        Criteria cri = new Criteria();
        int count = service.countPost(cri);
        log.info(count);
    }

    @Test
    public void getList() throws Exception {
        service.listAll().forEach(log::info);
    }

    @Test
    public void searchTest() throws Exception {
        SearchCriteria cri = new SearchCriteria();
        cri.setPage(1);
        cri.setKeyword("999");
        cri.setSearchType("t");
        log.info("============ Result ===========");

        List<PostVO> posts = service.searchedList(cri);

        for(PostVO vo : posts)
            log.info(vo.getPostNo()+" "+vo.getTitle());

        log.info("counted search List: "+service.countSearchedList(cri));

    }

}
