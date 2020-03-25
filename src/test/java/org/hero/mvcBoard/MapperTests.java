package org.hero.mvcBoard;


import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.hero.mvcBoard.commons.paging.SearchCriteria;
import org.hero.mvcBoard.domain.PostVO;
import org.hero.mvcBoard.mapper.PostMapper;
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
public class MapperTests {

    @Setter(onMethod_ ={@Autowired} )
    private PostMapper mapper;

    @Test
    public void mapperExists(){
        log.info("PostMapper");
        assertNotNull(mapper);
    }

    @Test
    public void SearchedList(){
        log.info("searched List");
        SearchCriteria cri = new SearchCriteria();
        cri.setPage(1);
        cri.setKeyword("999");
        cri.setSearchType("tcw");
        log.info("===== Searched List =====");
        List<PostVO> list = mapper.searchedList(cri);
        list.forEach(log::info);
        log.info("===== Searched List Counted =====");
        log.info(mapper.countSearchedPost(cri));
    }

}
