package org.hero.mvcBoard.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.hero.mvcBoard.commons.paging.Criteria;
import org.hero.mvcBoard.commons.paging.SearchCriteria;
import org.hero.mvcBoard.domain.PostVO;
import org.hero.mvcBoard.mapper.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {
    private PostMapper mapper;

    @Override
    public void register(PostVO vo) throws Exception {
        log.info("Register: "+vo);
        mapper.register(vo);
    }

    @Override
    public PostVO read(Long postNo) throws Exception {
        log.info("read: "+postNo);
        return mapper.read(postNo);
    }

    @Override
    public boolean update(PostVO vo) throws Exception {
        log.info("update: "+vo);
        return mapper.update(vo) == 1;
    }

    @Override
    public boolean delete(Long postNo) throws Exception {
        log.info("delete: "+postNo);
        return mapper.delete(postNo) == 1;
    }

    @Override
    public List<PostVO> listAll() throws Exception {
        log.info("return list of Posts...");
        return mapper.listAll();
    }

    @Override
    public List<PostVO> listPaging(Criteria cri) throws Exception {

        return mapper.getListWithPaging(cri);
    }

    @Override
    public int countPost(Criteria cri) throws Exception {
        log.info("Count Post");
        return mapper.countPost(cri);
    }

    @Override
    public List<PostVO> searchedList(SearchCriteria cri) throws Exception {
        log.info("======== Searched List ========");
        return mapper.searchedList(cri);
    }

    @Override
    public int countSearchedList(SearchCriteria cri) throws Exception {
        log.info("======= Count Searched Posts =======");
        return mapper.countSearchedPost(cri);
    }
}
