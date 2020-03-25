package org.hero.mvcBoard.service;

import org.hero.mvcBoard.commons.paging.Criteria;
import org.hero.mvcBoard.commons.paging.SearchCriteria;
import org.hero.mvcBoard.domain.PostVO;

import java.util.List;

public interface PostService {
    void register(PostVO vo) throws Exception;
    PostVO read(Long postNo) throws Exception;
    boolean update(PostVO vo) throws Exception;
    boolean delete(Long postNo) throws Exception;
    List<PostVO> listAll() throws Exception;
    List<PostVO> listPaging(Criteria cri) throws Exception;
    int countPost(Criteria cri) throws Exception;
    List<PostVO> searchedList(SearchCriteria cri) throws Exception;
    int countSearchedList(SearchCriteria cri) throws Exception;
}
