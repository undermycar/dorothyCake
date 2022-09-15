package com.dorothy.member.board.free.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dorothy.common.vo.PageDTO;
import com.dorothy.member.board.free.service.BoardFreeService;
import com.dorothy.member.board.free.vo.BoardFreeVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/free/*")
@AllArgsConstructor
public class BoardFreeController {
	private BoardFreeService boardFreeService;
	
	/***********************************************************
	 * �����Խ��� �� ��� ����
	 * ��û URL : http://localhost:8080/board/free/boardFreeList
	 ***********************************************************/
	@RequestMapping(value="/boardFreeList", method = RequestMethod.GET)
	public String boardFreeList(@ModelAttribute("data") BoardFreeVO bfvo, Model model) {
		log.info("boardFreeList ȣ�� ����");
		
		// ��ü ���ڵ� ��ȸ
		List<BoardFreeVO> boardFreeList = boardFreeService.boardFreeList(bfvo);
		model.addAttribute("boardFreeList", boardFreeList);
		
		// ��ü ���ڵ� ��
		int total = boardFreeService.boardFreeListCnt(bfvo);
		// ����¡ ó��
		model.addAttribute("pageMaker", new PageDTO(bfvo, total));
		
		return "member/board/free/boardFreeList"; // /WEB-INF/views/member/board/free/boardFreeList.jsp
	}
	
	/***********************************************************
	 * �����Խ��� �� �󼼺��� ����
	 * ��û URL : http://localhost:8080/board/free/boardFreeDetail
	 ***********************************************************/
	@RequestMapping(value="/boardFreeDetail", method = RequestMethod.GET)
	public String boardFreeDetail(@ModelAttribute("data") BoardFreeVO bfvo, Model model) {
		log.info("boardFreeDetail ȣ�� ����");
		
		BoardFreeVO bfDetail = boardFreeService.boardFreeDetail(bfvo);
		model.addAttribute("bfDetail", bfDetail);
		
		return "member/board/free/boardFreeDetail"; // /WEB-INF/views/member/board/free/boardFreeDetail.jsp
	}
	
	/***********************************************************
	 * �����Խ��� �۾��� �� ���
	 * ��û URL : http://localhost:8080/board/free/freeWriteForm
	 ***********************************************************/
	@RequestMapping(value="/freeWriteForm")
	public String freeWriteForm(@ModelAttribute("data") BoardFreeVO bfvo) {
		log.info("freeWriteForm ȣ�� ����");
		
		return "member/board/free/freeWriteForm"; // /WEB-INF/views/member/board/free/freeWriteForm.jsp
	}
	
	/***********************************************************
	 * �����Խ��� �۾���
	 * ��û URL : http://localhost:8080/board/free/freeWriteForm
	 ***********************************************************/
	@RequestMapping(value="/boardFreeInsert", method = RequestMethod.POST)
	public String boardFreeInsert(BoardFreeVO bfvo, Model model) throws Exception {
		log.info("boardFreeInsert ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = boardFreeService.boardFreeInsert(bfvo);
		if(result == 1) {
			url = "/board/free/boardFreeList";
		}else {
			url = "board/free/freeWriteForm";
		}
		
		return "redirect:" + url;
	}
	
	/***********************************************************
	 * �����Խ��� �� ����
	 * ��û URL : http://localhost:8080/board/free/boardFreeDelete
	 ***********************************************************/
	@RequestMapping(value="/boardFreeDelete", method = RequestMethod.POST)
	public String boardFreeDelete(@ModelAttribute BoardFreeVO bfvo, RedirectAttributes ras) throws Exception {
		log.info("boardFreeDelete ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = boardFreeService.boardFreeDelete(bfvo);
		ras.addFlashAttribute("boardFreeVO", bfvo);
		
		if(result == 1) {
			url = "/board/free/boardFreeList";
		}else {
			url = "/board/free/boardFreeDetail";
		}
		
		return "redirect:" + url;
	}
	
	/***********************************************************
	 * �����Խ��� �� ���� �� ���
	 * ��û URL : http://localhost:8080/board/free/freeUpdateForm
	 ***********************************************************/
	@RequestMapping(value="/freeUpdateForm")
	public String freeUpdateForm(@ModelAttribute("data") BoardFreeVO bfvo, Model model) {
		log.info("updateForm ȣ�� ����");
		log.info("f_num" + bfvo.getF_num());
		
		BoardFreeVO updateData = boardFreeService.freeUpateForm(bfvo);
		
		model.addAttribute("updateData", updateData);
		
		return "member/board/free/freeUpdateForm";
	}
	
	/***********************************************************
	 * �����Խ��� �� ����
	 * ��û URL : http://localhost:8080/board/free/boardFreeUpdate
	 ***********************************************************/
	@RequestMapping(value="/boardFreeUpdate", method = RequestMethod.POST)
	public String boardFreeUpdate(@ModelAttribute BoardFreeVO bfvo, RedirectAttributes ras) throws Exception {
		log.info("boardFreeUpdate ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = boardFreeService.boardFreeUpdate(bfvo);
		ras.addFlashAttribute("data", bfvo);
		
		if(result == 1) {
			url = "/board/free/boardFreeDetail";
		}else {
			url = "/board/free/freeUpdateForm";
		}
		
		return "redirect:" + url;
	}
	
}
