package com.dorothy.admin.board.review.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dorothy.admin.board.review.service.AdminBoardReviewService;
import com.dorothy.common.vo.PageDTO;
import com.dorothy.member.board.review.vo.BoardReviewVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/admin/board/review/*")
public class AdminBoardReviewController {
	
	@Setter(onMethod_ = @Autowired)
	private AdminBoardReviewService adminBoardReviewService;
	
	/***********************************************************
	 * ������ ����Խ��� �� ��� ����
	 * ��û URL : http://localhost:8081/admin/board/review/boardReviewList
	 ***********************************************************/
	@RequestMapping(value="/boardReviewList", method = RequestMethod.GET)
	public String boardReviewList(@ModelAttribute("data") BoardReviewVO brvo, Model model) {
		log.info("boardReviewList ȣ�� ����");
		
		// ��ü ���ڵ� ��ȸ
		List<BoardReviewVO> boardReviewList = adminBoardReviewService.boardReviewList(brvo);
		model.addAttribute("boardReviewList", boardReviewList);
		
		// ��ü ���ڵ� ��
		int total = adminBoardReviewService.boardReviewListCnt(brvo);
		
		// ����¡ ó��
		model.addAttribute("pageMaker", new PageDTO(brvo, total));
		
		int count = total - (brvo.getPageNum() - 1) * brvo.getAmount();
		model.addAttribute("count", count);
		
		return "admin/board/review/boardReviewList"; // /WEB-INF/views/member/board/review/boardReviewList.jsp
	}
	
	/***********************************************************
	 * ������ ����Խ��� �� �󼼺��� ����
	 * ��û URL : http://localhost:8081/admin/board/review/boardReviewDetail
	 ***********************************************************/
	@RequestMapping(value="/boardReviewDetail", method = RequestMethod.GET)
	public String boardReviewDetail(@ModelAttribute("data") BoardReviewVO brvo, Model model) {
		log.info("admin boardReviewDetail ȣ�� ����");
		
		BoardReviewVO brDetail = adminBoardReviewService.boardReviewDetail(brvo);
		model.addAttribute("brDetail", brDetail);
		
		return "admin/board/review/boardReviewDetail";
	}
	
	/***********************************************************
	 * ������ ����Խ��� ������ �� ���� ����
	 * ��û URL : http://localhost:8081/admin/board/review/deleteAll
	 ***********************************************************/
	@RequestMapping(value="/deleteAll", method = RequestMethod.POST)
	public String deleteAll(HttpServletRequest request) throws Exception {
		log.info("deleteAll ȣ�� ����");
		
		String[] numArr = request.getParameterValues("numArr");
		
		adminBoardReviewService.deleteAll(numArr);
		
		return "/admin/board/review/boardReviewList";		
	}
	
	/***********************************************************
	 * ������ ����Խ��� �������� �� ���� ����
	 * ��û URL : http://localhost:8081/admin/board/review/boardReviewDelete
	 ***********************************************************/
	@RequestMapping(value="boardReviewDelete", method = RequestMethod.POST)
	public String boardReviewDelete(@ModelAttribute BoardReviewVO brvo, RedirectAttributes ras) throws Exception {
		log.info("admin boardReviewDelete ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = adminBoardReviewService.boardReviewDelete(brvo);
		ras.addFlashAttribute("BoardReviewVO", brvo);
		
		if(result == 1) {
			url = "/admin/board/review/boardReviewList";
		}else {
			url = "/admin/board/review/boardReviewDetail";
		}
		
		return "redirect:" + url;
	}
	
	@ResponseBody
	@RequestMapping(value="/replyCnt")
	public String replyCnt(@RequestParam("r_num") int r_num) {
		log.info("replyCnt ȣ�� ����");
		
		int result = 0;
		result = adminBoardReviewService.replyCnt(r_num);
		
		// return result + ""; > �������̾ ����
		return String.valueOf(result);
	}
}
