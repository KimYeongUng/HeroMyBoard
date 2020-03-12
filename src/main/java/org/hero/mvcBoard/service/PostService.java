package org.hero.mvcBoard.service;

import org.hero.mvcBoard.domain.PostVO;

import java.util.List;

public interface PostService {
    void register(PostVO vo) throws Exception;
    PostVO read(Integer postNo) throws Exception;
    boolean update(PostVO vo) throws Exception;
    boolean delete(Integer postNo) throws Exception;
    List<PostVO> listAll() throws Exception;
}
