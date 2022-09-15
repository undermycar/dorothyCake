package com.dorothy.admin.board.qna.controller;

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
/*import org.springframework.web.servlet.ModelAndView;*/
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dorothy.admin.board.qna.service.AdminBoardService;
import com.dorothy.common.vo.PageDTO;
import com.dorothy.member.board.qna.vo.BoardVO;





import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value="/admin/board/qna/*")
@Log4j
public class AdminBoardController {
	
	@Setter(onMethod_ = @Autowired)
	private AdminBoardService adminBoardService;
	
	/***********************************************************
	 * ������ �����Խ��� �� ��� ����
	 * ��û URL : http://localhost:8080/admin/qna/free/boardList
	 ***********************************************************/
	
	@RequestMapping(value="/boardList", method = RequestMethod.GET)
	public String boardList(@ModelAttribute("data") BoardVO bvo, Model model) {
		log.info("admin boardList ȣ�� ����");
		
		List<BoardVO> boardList = adminBoardService.boardList(bvo);
		model.addAttribute("boardList" ,boardList);
		
		//��ü ���ڵ� �� ����
		int total = adminBoardService.boardListCnt(bvo);
		model.addAttribute("pageMaker", new PageDTO(bvo, total));
		
		int count = total - (bvo.getPageNum()-1) * bvo.getAmount();
		model.addAttribute("count", count);
		
		
		return "admin/board/qna/boardList"; // /WEB-INF/views/admin/board/qna/boardList.jsp
	}
	
	/***********************************************************
	 * ������ �����Խ��� �� �󼼺��� ����
	 * ��û URL : http://localhost:8080/admin/board/qna/boardList
	 ***********************************************************/
	@RequestMapping(value="/boardDetail", method=RequestMethod.GET)
//	@GetMapping("/boardDetail")
	public String boardDetail(@ModelAttribute("data") BoardVO bvo, Model model) {
		log.info("boardDetail ȣ�� ����");
		//log.info("bvo = " + bvo);
		
		BoardVO detail = adminBoardService.boardDetail(bvo);
		model.addAttribute("detail", detail);
		
		return "admin/board/qna/boardDetail";
	}
	
	/***********************************************************
	 * ������ �����Խ��� ������ �� ���� ����
	 * ��û URL : http://localhost:8080/admin/board/qna/deleteAll
	 ***********************************************************/
	@RequestMapping(value="/deleteAll", method = RequestMethod.POST)
	public String deleteAll(HttpServletRequest request) throws Exception {
		log.info("deleteAll ȣ�� ����");
		
		String[] numArr = request.getParameterValues("numArr");
		
		adminBoardService.deleteAll(numArr);
		
		return "/admin/board/qna/boardList";		
	}
	
	/***********************************************************
	 * ������ �����Խ��� �������� �� ���� ����
	 * ��û URL : http://localhost:8080/admin/board/qna/boardDelete
	 ***********************************************************/
	
	@RequestMapping(value = "/boardDelete")
	public String boardDelete(@ModelAttribute BoardVO bvo, RedirectAttributes ras) throws Exception{
		log.info("boardDelete ȣ�� ����");
		
		// �Ʒ� �������� �Է� ������ ���� ���°� ����ϴ�.(1 or 0)
		int result = 0;
		String url = "";
		
		// result = boardService.boardDelete(bvo.getB_num());
		result = adminBoardService.boardDelete(bvo);
		ras.addFlashAttribute("boardVO", bvo);
		
		if(result == 1) {
			url = "/admin/board/qna/boardList";
		}else {
			//url = "/board/boardDetail?b_num="+bvo.getB_num();
			url = "/admin/board/qna/boardDetail";
		}
		
		return "redirect:" + url;
	}
	
	@ResponseBody
	@RequestMapping(value="/replyCnt")
	public String replyCnt(@RequestParam("q_num") int q_num) {
		log.info("replyCnt ȣ�� ����");
		
		int result = 0;
		result = adminBoardService.replyCnt(q_num);
		
		// return result + ""; > �������̾ ����
		return String.valueOf(result);
	}
	
	/*
	 * ���� �ٿ�ε� �����ϱ� ���� : ListExcelView Ŭ�������� ��Ÿ������ Model�� �����ϰ� �ȴ�.
	 */
	
//	@RequestMapping(value = "/board/boardExcel", method = RequestMethod.GET)
//	public ModelAndView boardExcel(@ModelAttribute BoardVO bvo) {
//		log.info("boardExcel ȣ�� ����");	
//		
//		List<BoardVO> boardList = adminBoardService.boardListExcel(bvo);
//		log.info("��ȯ���� ���ڵ� �� : " +boardList.size());
//		
//		
//		ModelAndView mv = new ModelAndView(new ListExcelView());
//		mv.addObject("list", boardList); //������ ������
//		mv.addObject("template", "board.xlsx"); //������ ������
//		mv.addObject("file_name", "board"); //������ ������
//		
//		return mv;
//	}
//	
	
	
	

}
