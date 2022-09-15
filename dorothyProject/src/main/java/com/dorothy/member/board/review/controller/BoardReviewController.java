package com.dorothy.member.board.review.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dorothy.common.vo.PageDTO;

import com.dorothy.member.board.review.service.BoardReviewService;
import com.dorothy.member.board.review.vo.BoardReviewVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/review/*")
@AllArgsConstructor
public class BoardReviewController {
	private BoardReviewService boardReviewService;
	
	/***********************************************************
	 * ����Խ��� �� ��� �����ϱ�(����¡ ó�� ���� ��� ��ȸ)
	 * ��û URL : http://localhost:8081/board/review/boardReviewList
	 ***********************************************************/
	@RequestMapping(value="/boardReviewList", method = RequestMethod.GET)
	public String boardReviewList(@ModelAttribute("data") BoardReviewVO brvo, Model model) {
		log.info("boardReviewList ȣ�� ����");
		
		// ��ü ���ڵ� ��ȸ
		List<BoardReviewVO> boardReviewList = boardReviewService.boardReviewList(brvo);
		model.addAttribute("boardReviewList", boardReviewList);
		
		// ��ü ���ڵ� ��
		int total = boardReviewService.boardReviewListCnt(brvo);
		
		// ����¡ ó��
		model.addAttribute("pageMaker", new PageDTO(brvo, total));
		
		return "member/board/review/boardReviewList"; // /WEB-INF/views/member/board/review/boardReviewList.jsp
	}
	
	/***********************************************************
	 * ����Խ��� �� �󼼺��� ����
	 * ��û URL : http://localhost:8081/board/review/boardReviewDetail
	 ***********************************************************/
	@RequestMapping(value="/boardReviewDetail", method = RequestMethod.GET)
	public String boardReviewDetail(@ModelAttribute("data") BoardReviewVO brvo, Model model) {
		log.info("boardReviewDetail ȣ�� ����");
		
		BoardReviewVO brDetail = boardReviewService.boardReviewDetail(brvo);
		model.addAttribute("brDetail", brDetail);
		
		return "member/board/review/boardReviewDetail"; // /WEB-INF/views/member/board/review/boardReviewDetail.jsp
	}
	
	/***********************************************************
	 * ����Խ��� �۾��� �� ���
	 * ��û URL : http://localhost:8081/board/review/reviewWriteForm
	 ***********************************************************/
	@RequestMapping(value="/reviewWriteForm")
	public String reviewWriteForm(@ModelAttribute("data") BoardReviewVO brvo) {
		log.info("reviewWriteForm ȣ�� ����");
		
		return "member/board/review/reviewWriteForm"; // /WEB-INF/views/member/board/review/reviewWriteForm.jsp
	}
	
	/***********************************************************
	 * ����Խ��� �۾���
	 * ��û URL : http://localhost:8081/board/review/reviewWriteForm
	 ***********************************************************/
	@RequestMapping(value="/boardReviewInsert", method = RequestMethod.POST)
	public String boardReviewInsert(BoardReviewVO brvo, Model model) throws Exception {
		log.info("boardReviewInsert ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = boardReviewService.boardReviewInsert(brvo);
		if(result == 1) {
			url = "/board/review/boardReviewList";
		}else {
			url = "board/review/reviewWriteForm";
		}
		
		return "redirect:" + url;
	}
	
	/***********************************************************
	 * ����Խ��� �� ����
	 * ��û URL : http://localhost:8081/board/review/boardReviewDelete
	 ***********************************************************/
	@RequestMapping(value="/boardReviewDelete", method = RequestMethod.POST)
	public String boardReviewDelete(@ModelAttribute BoardReviewVO brvo, RedirectAttributes ras) throws Exception {
		log.info("boardReviewDelete ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = boardReviewService.boardReviewDelete(brvo);
		ras.addFlashAttribute("BoardReviewVO", brvo);
		
		if(result == 1) {
			url = "/board/review/boardReviewList";
		}else {
			url = "/board/review/boardReviewDetail";
		}
		
		return "redirect:" + url;
	}
	
	/***********************************************************
	 * ����Խ��� �� ���� �� ���
	 * ��û URL : http://localhost:8081/board/review/reviewUpdateForm
	 ***********************************************************/
	@RequestMapping(value="/reviewUpdateForm")
	public String reviewUpdateForm(@ModelAttribute("data") BoardReviewVO brvo, Model model) {
		log.info("reviewUpdateForm ȣ�� ����");
		log.info("r_num" + brvo.getR_num());
		
		BoardReviewVO updateData = boardReviewService.reviewUpdateForm(brvo);
		
		model.addAttribute("updateData", updateData);
		
		return "member/board/review/reviewUpdateForm";
	}
	
	/***********************************************************
	 * ����Խ��� �� ����
	 * ��û URL : http://localhost:8081/board/review/boardReviewUpdate
	 ***********************************************************/
	@RequestMapping(value="/boardReviewUpdate", method = RequestMethod.POST)
	public String boardReviewUpdate(@ModelAttribute BoardReviewVO brvo, RedirectAttributes ras) throws Exception {
		log.info("boardReviewUpdate ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = boardReviewService.boardReviewUpdate(brvo);
		ras.addFlashAttribute("data", brvo);
		
		if(result == 1) {
			url = "/board/review/boardReviewDetail";
		}else {
			url = "/board/review/reviewUpdateForm";
		}
		
		return "redirect:" + url;
	}
	

}
