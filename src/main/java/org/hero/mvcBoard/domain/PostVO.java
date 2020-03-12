package org.hero.mvcBoard.domain;

import lombok.Data;

import java.util.Date;

@Data
public class PostVO {
    private Long postNo;
    private String title;
    private String content;
    private String writer;
    private Date regdate;
    private Long viewCnt;
}
