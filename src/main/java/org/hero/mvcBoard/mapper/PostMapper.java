package org.hero.mvcBoard.mapper;

import org.hero.mvcBoard.domain.PostVO;

import java.util.List;

public interface PostMapper {
    public void register(PostVO vo);
    public PostVO read(Integer postNo);
    public Integer update(PostVO vo);
    public List<PostVO> listAll();
    public Integer delete(Integer postNo);
}
