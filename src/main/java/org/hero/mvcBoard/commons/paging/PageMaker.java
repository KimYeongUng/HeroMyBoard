package org.hero.mvcBoard.commons.paging;

import lombok.Getter;
import lombok.extern.log4j.Log4j;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Getter
@Log4j
public class PageMaker {
    private int totalCount;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;

    private int displayPageNum = 10;

    private Criteria cri;

    public void setCriteria(Criteria cri){
        this.cri = cri;
        log.info("pageMaker set Criteria: "+cri.page+" "+cri.getPageAmount());
    }

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
        log.info("Total Cnt in Criteria: "+this.totalCount);
        calcData();
    }

    private void calcData(){
        log.info("calcData processing...");
        this.endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum)*displayPageNum);
        this.startPage = (this.endPage - displayPageNum)+1;

        int tmpEndPage = (int)(Math.ceil(this.totalCount/(double)cri.getPageAmount()));

        if(this.endPage>tmpEndPage)
            endPage = tmpEndPage;

        this.prev = startPage != 1;
        this.next = endPage * cri.getPageAmount() < totalCount;

    }

    public String queryMaker(int page){
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page",page)
                .queryParam("pageAmount",cri.getPageAmount())
                .build();
        return uriComponents.toUriString();
    }

    public String makeSearch(int page){
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .queryParam("page",page)
                .queryParam("pageAmount",cri.getPageAmount())
                .queryParam("searchType",((SearchCriteria)cri).getSearchType())
                .queryParam("keyword",encoding(((SearchCriteria)cri).getKeyword()))
                .build();

        return uriComponents.toUriString();
    }

    private String encoding(String keyword){
        if(keyword == null || keyword.trim().length() == 0)
            return "";
        return URLEncoder.encode(keyword, StandardCharsets.UTF_8);
    }
}
