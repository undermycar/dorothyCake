package com.dorothy.member.board.qna.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dorothy.common.vo.PageDTO;
import com.dorothy.member.board.qna.service.BoardService;
import com.dorothy.member.board.qna.vo.BoardVO;
import com.dorothy.member.login.vo.MemberVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/qna/*")
@AllArgsConstructor
public class BoardController {
	private BoardService boardService;
	
	/*************************************
	 * �۸�� �����ϱ�(����¡ ó�� ���� ��� ��ȸ)
	 * ��û URL : http://localhost:8080/board/boardList
	 *************************************
	@RequestMapping(value="/boardList", method = RequestMethod.GET)
//	@GetMapping("/boardList")
	public String boardList(@ModelAttribute("data") BoardVO bvo, Model model) {
		log.info("boardList ȣ�� ����");
		// ��ü ���ڵ� ��ȸ
		List<BoardVO> boardList = boardService.boardList(bvo);
		model.addAttribute("boardList", boardList);
		
		return "board/boardList"; // /WEB-INF/views/board/boardList.jsp
	} */

	/*************************************
	 * �۸�� �����ϱ�(����¡ ó�� ��� ��ȸ)
	 * ��û URL : http://localhost:8080/board/qna/boardList
	 *************************************/
	@RequestMapping(value="/boardList", method = RequestMethod.GET)
//	@GetMapping("/boardList")
	public String boardList(@ModelAttribute("data") BoardVO bvo, Model model) {
		log.info("boardList ȣ�� ����");
		// ��ü ���ڵ� ��ȸ
		List<BoardVO> boardList = boardService.boardList(bvo);
		model.addAttribute("boardList", boardList);
		
		// ��ü ���ڵ�� ����
		int total = boardService.boardListCnt(bvo);
		// ����¡ ó��
		model.addAttribute("pageMaker", new PageDTO(bvo, total)); // new PageDTO(CommonVO �Ǵ� CommonVO ���� Ŭ������ �ν��Ͻ�(BoardVO), �� ���ڵ��)
		
		return "member/board/qna/boardList"; // member/board/qna/boardList.jsp
	}
	
	@RequestMapping(value="/myList", method = RequestMethod.GET)
//	@GetMapping("/boardList")
	public String myList(@ModelAttribute("data") BoardVO bvo, HttpServletRequest req, Model model) {
		log.info("boardList ȣ�� ����");
		
		HttpSession session = req.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("member");
		
		// ��ü ���ڵ� ��ȸ
		List<BoardVO> boardList = boardService.myList(mvo);
		model.addAttribute("boardList", boardList);
		
		// ��ü ���ڵ�� ����
		int total = boardService.boardListCnt(bvo);
		// ����¡ ó��
		model.addAttribute("pageMaker", new PageDTO(bvo, total)); // new PageDTO(CommonVO �Ǵ� CommonVO ���� Ŭ������ �ν��Ͻ�(BoardVO), �� ���ڵ��)
		
		return "member/board/qna/boardList"; // member/board/qna/boardList.jsp
	}
	
	/*************************************
	 * �۾��� �� ����ϱ�
	 *************************************/
	@RequestMapping(value="/writeForm")
	public String writeForm(@ModelAttribute("data") BoardVO bvo) {
		log.info("writeForm ȣ�� ����");
		
		return "member/board/qna/writeForm"; // /WEB-INF/views/board/writeForm.jsp
	}
	
	/*************************************
	 * �۾��� �����ϱ�
	 *************************************/
	@RequestMapping(value="/boardInsert", method=RequestMethod.POST)
//	@PostMapping("/boardInsert")
	public String boardInsert(BoardVO bvo, Model model) throws Exception{
		log.info("boardInsert ȣ�� ����");
		
		int result = 0;
		String url = "";
//		bvo.setM_id("abc123");
		bvo.setQ_status("�亯���");
		
		
		result = boardService.boardInsert(bvo);
		if(result == 1) {
			url = "/board/qna/boardList";
		}else {
			url = "/board/qna/writeForm";
		}
		
		return "redirect:"+url;
	}
	
	/*************************************
	 * �� �󼼺��� ����
	 *************************************/
	@RequestMapping(value="/boardDetail", method=RequestMethod.GET)
//	@GetMapping("/boardDetail")
	public String boardDetail(@ModelAttribute("data") BoardVO bvo, Model model) {
		log.info("boardDetail ȣ�� ����");
		//log.info("bvo = " + bvo);
		
		BoardVO detail = boardService.boardDetail(bvo);
		model.addAttribute("detail", detail);
		
		return "member/board/qna/boardDetail";
	}
	
	/*************************************
	 * ��й�ȣ Ȯ��
	 * @param b_num
	 * @param b_pwd
	 * @return int�� result�� 0 �Ǵ� 1�� ������ ���� �ְ�,
	 * 		   String���� result ���� "���� or ����, ��ġ or ����ġ"�� ������ ���� �ִ�.(���� ���ڿ� ����)
	 * ���� : @Responsebody�� ���޵� �並 ���ؼ� ����ϴ� ���� �ƴ϶� HTTP Response Body�� ���� ����ϴ� ���.
	 * 		 produces �Ӽ��� ������ �̵�� Ÿ�԰� ���õ� ������ �����ϴµ� ����� ���� ����Ʈ Ÿ���� ����.
	 *************************************/
	@ResponseBody
	@RequestMapping(value = "/pwdConfirm", method = RequestMethod.POST, produces = "text/plain; charset=UTF-8")
	public String pwdConfirm(BoardVO bvo) {
		log.info("pwdConfirm ȣ�� ����");
		String value = "";
		
		// �Ʒ� �������� �Է� ������ ���� ���°� ����(1 or 0)
		int result = boardService.pwdConfirm(bvo);
		if(result == 1) {
			value = "����";
		}else {
			value = "����";
		}
		log.info("result = " + result);
		
		return value; // value�� ��ü�� �������� ���
	}
	
	/*************************************
	 * �ۻ��� �����ϱ�
	 * ���� : RedirectAttributes ��ü�� �����̷�Ʈ ����(return "redirect:/���")��
	 * �ѹ��� ���Ǵ� �����͸� ������ �� �ִ� addFlashAttribute()��� ����� �����Ѵ�.
	 * addFlashAttribute() �޼���� ���������� ���۵Ǳ�� ������, URI�󿡴� ������ �ʴ� ������ �������� ���·� ���޵ȴ�.
	 *************************************/
	@RequestMapping(value = "/boardDelete")
	public String boardDelete(@ModelAttribute BoardVO bvo, RedirectAttributes ras) throws Exception{
		log.info("boardDelete ȣ�� ����");
		
		// �Ʒ� �������� �Է� ������ ���� ���°� ����ϴ�.(1 or 0)
		int result = 0;
		String url = "";
		
		// result = boardService.boardDelete(bvo.getB_num());
		result = boardService.boardDelete(bvo);
		ras.addFlashAttribute("boardVO", bvo);
		
		if(result == 1) {
			url = "/board/qna/boardList";
		}else {
			//url = "/board/boardDetail?b_num="+bvo.getB_num();
			url = "/board/qna/boardDetail";
		}
		
		return "redirect:" + url;
	}
	
	/*************************************
	 * �ۼ��� �� ����ϱ�
	 * @param : b_num
	 * @return : BoardVO
	 *************************************/
	@RequestMapping(value="/updateForm")
	public String updateForm(@ModelAttribute("data") BoardVO bvo, Model model) {
		log.info("updateForm ȣ�� ����");
		log.info("q_num = " + bvo.getQ_num());
		
		BoardVO updateData = boardService.updateForm(bvo);
		
		model.addAttribute("updateData", updateData);
		return "member/board/qna/updateForm"; // /WEB-INF/views/board/updateForm.jsp
	}
	
	/*************************************
	 * �ۼ��� �����ϱ�
	 * @param : BoardVO
	 * ���� : RedirectAttributes ��ü�� �����̷�Ʈ ����(return "redirect:/���")��
	 * �ѹ��� ���Ǵ� �����͸� ������ �� �ִ� addFlashAttribute()��� ����� �����Ѵ�.
	 * addFlashAttribute() �޼���� ���������� ���۵Ǳ�� ������, URI�󿡴� ������ �ʴ� ������ �������� ���·� ���޵ȴ�.
	 *************************************/
	@RequestMapping(value="/boardUpdate", method=RequestMethod.POST)
	public String boardUpdate(@ModelAttribute BoardVO bvo, RedirectAttributes ras) throws Exception {
		log.info("boardUpdate ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = boardService.boardUpdate(bvo);
		ras.addFlashAttribute("data", bvo);
		
		if(result == 1) {
			// �Ʒ� url�� ���� �� �� �������� �̵�
			// url = "/board/boardDetail?b_num="+bvo.getB_num();
			url = "/board/qna/boardDetail";
		}else {
			//url = "/board/updateForm?b_num="+bvo.getB_num();
			url = "/board/qna/updateForm";
		}
		
		return "redirect:"+url;
	}
	
	/*************************************
	 * �ۻ��� �� ��� ���� �����ϱ�
	 * @param : int
	 *************************************/
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value="/replyCnt") public String
	 * replyCnt(@RequestParam("q_num") int q_num) { log.info("replyCnt ȣ�� ����");
	 * 
	 * int result = 0; result = boardService.replyCnt(q_num);
	 * 
	 * // return result + ""; > �������̾ ���� return String.valueOf(result); }
	 */
}
