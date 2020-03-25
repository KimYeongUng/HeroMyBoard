package org.hero.mvcBoard.mapper;

import org.hero.mvcBoard.commons.paging.Criteria;
import org.hero.mvcBoard.commons.paging.SearchCriteria;
import org.hero.mvcBoard.domain.PostVO;

import java.util.List;

public interface PostMapper {
    public void register(PostVO vo);
    public PostVO read(Long postNo);
    public Integer update(PostVO vo);
    public List<PostVO> listAll();
    public Integer delete(Long postNo);
    List<PostVO> getListWithPaging(Criteria cri);
    int countPost(Criteria cri);
    int countSearchedPost(SearchCriteria cri);
    List<PostVO> searchedList(SearchCriteria cri);
}
