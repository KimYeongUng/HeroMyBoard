package org.hero.mvcBoard.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.hero.mvcBoard.commons.paging.PageMaker;
import org.hero.mvcBoard.commons.paging.SearchCriteria;
import org.hero.mvcBoard.domain.PostVO;
import org.hero.mvcBoard.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Board Controller
 *
 * CRUD URL Description below:
 *
 * GET /board/register : view register
 * POST /board/register : process register
 * GET /board/list : view list of posts
 * GET /board/read?postNo=val : view post where postNo is val
 * GET /board/modify?postNo=val : view modify page where postNo is val
 * POST /board/modify : modify processing page
 * POST /board/remove : remove processing page
 */

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

    private PostService service;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String registerGET(){
        log.info("Register GET...");
        return "/board/write";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String registerPOST(PostVO vo, RedirectAttributes rttr) throws Exception{
        log.info("Register POST....");
        log.info("VO: "+vo);
        service.register(vo);
        rttr.addFlashAttribute("msg","regSuccess");
        return "redirect:/board/list";
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model, @ModelAttribute("cri") SearchCriteria cri) throws Exception{
        log.info("list with criteria and PageMaker.....");
        log.info(cri.getPage()+" "+cri.getPageAmount());
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCriteria(cri);
        pageMaker.setTotalCount(service.countSearchedList(cri));
        model.addAttribute("posts",service.searchedList(cri));
        model.addAttribute("pageMaker",pageMaker);
        return "/board/list";
    }

    @RequestMapping(value = "/read",method = RequestMethod.GET)
    public String read(@RequestParam("postNo")Long postNo, @ModelAttribute("cri")SearchCriteria cri,
                       Model model) throws Exception{
        log.info("read....");
        model.addAttribute("post",service.read(postNo));
        return "/board/read";
    }

    @RequestMapping(value = "/modify",method = RequestMethod.GET)
    public String modifyGET(@RequestParam("postNo")Long postNo,@ModelAttribute("cri")SearchCriteria cri
            ,Model model) throws Exception{
        log.info("modify GET....");
        model.addAttribute("post",service.read(postNo));
        return "/board/modify";
    }

    @RequestMapping(value = "/modify",method = RequestMethod.POST)
    public String modifyPOST(PostVO vo,RedirectAttributes rttr,SearchCriteria cri) throws Exception{
        log.info("modify....");
        service.update(vo); 
        rttr.addAttribute("page",cri.getPage());
        rttr.addAttribute("pageAmount",cri.getPageAmount());
        rttr.addAttribute("keyword",cri.getKeyword());
        rttr.addAttribute("searchType",cri.getSearchType());
        rttr.addFlashAttribute("msg","modSuccess");
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("postNo") Long postNo,RedirectAttributes rttr,SearchCriteria cri) throws Exception {
        log.info("remove...: "+postNo);
        if(service.delete(postNo)){
            rttr.addAttribute("page",cri.getPage());
            rttr.addAttribute("pageAmount",cri.getPageAmount());
            rttr.addAttribute("keyword",cri.getKeyword());
            rttr.addAttribute("searchType",cri.getSearchType());
            rttr.addFlashAttribute("msg","delSuccess");
        }
        return "redirect:/board/list";
    }

}
