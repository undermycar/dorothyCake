package com.dorothy.admin.board.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dorothy.admin.board.notice.service.AdminBoardNoticeService;
import com.dorothy.common.vo.PageDTO;
import com.dorothy.member.board.notice.vo.BoardNoticeVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value="/admin/board/notice/*")
@Log4j
public class AdminBoardNoticeController {
	@Setter(onMethod_ = @Autowired)
	private AdminBoardNoticeService adminBoardNoticeService;
	
	@RequestMapping(value="/boardNoticeList", method = RequestMethod.GET)
	public String boardNoticeList(@ModelAttribute("data") BoardNoticeVO nvo, Model model) {
		log.info("admin boardNoticeList ȣ�� ����");
		
		List<BoardNoticeVO> boardNoticeList = adminBoardNoticeService.boardNoticeList(nvo);
		model.addAttribute("boardNoticeList", boardNoticeList);
		
		// ��ü ���ڵ�� ����
		int total = adminBoardNoticeService.boardNoticeListCnt(nvo);
		model.addAttribute("pageMaker", new PageDTO(nvo, total));
		
		int count = total - (nvo.getPageNum() - 1) * nvo.getAmount();
		model.addAttribute("count", count);
		
		return "admin/board/notice/boardNoticeList"; // /WEB-INF/views/admin/board/notice/boardNoticeList.jsp
	}
	
	@RequestMapping(value="/boardNoticeDetail", method = RequestMethod.GET)
	public String boardNoticeDetail(@ModelAttribute("data") BoardNoticeVO nvo, Model model) {
		log.info("admin boardNoticeDetail ȣ�� ����");
		
		BoardNoticeVO bnDetail = adminBoardNoticeService.boardNoticeDetail(nvo);
		model.addAttribute("bnDetail", bnDetail);
		
		return "admin/board/notice/boardNoticeDetail";
	}
	
	@RequestMapping(value="/deleteAll", method = RequestMethod.POST)
	public String deleteAll(HttpServletRequest request) throws Exception {
		log.info("deleteAll ȣ�� ����");
		
		String[] numArr = request.getParameterValues("numArr");
		
		adminBoardNoticeService.deleteAll(numArr);
		
		return "/admin/board/notice/boardNoticeList";		
	}

	@RequestMapping(value="boardNoticeDelete", method = RequestMethod.POST)
	public String boardNoticeDelete(@ModelAttribute BoardNoticeVO nvo, RedirectAttributes ras) throws Exception {
		log.info("admin boardNoticeDelete ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = adminBoardNoticeService.boardNoticeDelete(nvo);
		ras.addFlashAttribute("boardNoticeVO", nvo);
		
		if(result == 1) {
			url = "/admin/board/notice/boardNoticeList";
		}else {
			url = "/admin/board/notice/boardNoticeDetail";
		}
		
		return "redirect:" + url;
	}
	
	@RequestMapping(value="/noticeInsert", method = RequestMethod.POST)
	public String noticeInsert(BoardNoticeVO nvo, Model model) throws Exception {
		log.info("noticeInsert ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = adminBoardNoticeService.noticeInsert(nvo);
		if(result == 1) {
			url = "/admin/board/notice/boardNoticeList";
		}else {
			url = "/admin/board/notice/noticeInsertForm";
		}
		
		return "redirect:" + url;
	}
	
	@RequestMapping(value="/noticeInsertForm")
	public String noticeInsertForm(@ModelAttribute("data") BoardNoticeVO nvo) {
		log.info("noticeInsertForm ȣ�� ����");
		
		return "admin/board/notice/boardNoticeInsertForm"; // /WEB-INF/views/admin/board/notice/boardNoticeInsertForm.jsp
	}
	
	@RequestMapping(value="/noticeUpdateForm")
	public String noticeUpdateForm(@ModelAttribute("data") BoardNoticeVO nvo, Model model) throws Exception {
		log.info("noticeUpdateForm ȣ�� ����");
		log.info("n_num : " +nvo.getN_num());
		
		BoardNoticeVO updateData = adminBoardNoticeService.noticeUpateForm(nvo);
		
		model.addAttribute("updateData", updateData);
		
		return "admin/board/notice/boardNoticeUpdateForm";
	}
	
	@RequestMapping(value="/noticeUpdate", method = RequestMethod.POST)
	public String noticeUpdate(@ModelAttribute BoardNoticeVO nvo, RedirectAttributes ras) throws Exception {
		log.info("noticeUpdate ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = adminBoardNoticeService.noticeUpdate(nvo);
		ras.addFlashAttribute("data", nvo);
		
		if(result == 1) {
			url = "/admin/board/notice/boardNotieList";
		}else {
			url = "/admin/board/notice/noticeUpdateForm";
		}
		
		return "redirect:" + url;
	}
}
