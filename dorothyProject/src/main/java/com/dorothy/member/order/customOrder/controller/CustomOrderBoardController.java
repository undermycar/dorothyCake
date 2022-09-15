package com.dorothy.member.order.customOrder.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dorothy.admin.goods.service.AdminGoodsService;
import com.dorothy.common.vo.PageDTO;
import com.dorothy.member.goods.vo.MemberGoodsVO;
import com.dorothy.member.login.vo.MemberVO;
import com.dorothy.member.order.customOrder.service.CustomOrderBoardService;
import com.dorothy.member.order.customOrder.vo.CustomOrderBoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/customOrder/*")
@AllArgsConstructor
public class CustomOrderBoardController {
	
	private CustomOrderBoardService customOrderBoardService;
	
	private AdminGoodsService adminGoodsService;
	
	/***************************************************************
	 * Ŀ���� ��ǰ �ֹ� �Խ��� �۸�� �����ϱ�(����¡ ó���κ� ���� ��� ��ȸ)
	 * ��û URL: http://localhost:8081/board/customOrderList
	 **************************************************************/
	@RequestMapping(value="/customOrderList", method = RequestMethod.GET)
	public String customOrderList(@ModelAttribute("data") CustomOrderBoardVO cobvo, Model model) {
		log.info("customOrderList ȣ�� ����");
		
		// ��ü ���ڵ� ��ȸ
		List<CustomOrderBoardVO> customOrderList = customOrderBoardService.customOrderList(cobvo);
		model.addAttribute("customOrderList", customOrderList);
		
		// ��ü ���ڵ� ��
		int total = customOrderBoardService.customOrderListCnt(cobvo);
		
		// ����¡ ó��
		model.addAttribute("pageMaker", new PageDTO(cobvo, total));
		

		return "member/board/customOrder/customOrderList";
	}
	
	/***********************************************************
	 * Ŀ���� ��ǰ �ֹ� �Խ��� �� �󼼺��� ����
	 * ��û URL : http://localhost:8081/board/customOrder/customOrderDetail
	 ***********************************************************/
	@RequestMapping(value="/customOrderDetail", method = RequestMethod.GET)
	public String customOrderDetail(@ModelAttribute("data") CustomOrderBoardVO cobvo, Model model) {
		log.info("customOrderDetail ȣ�� ����");

		CustomOrderBoardVO customOrderDetail = customOrderBoardService.customOrderDetail(cobvo);
		model.addAttribute("customOrderDetail", customOrderDetail);
		
		/* ��ǰ�ڵ� �ҷ����°� */
		int g_code = 0;
		String g_name ="";
		g_name = customOrderDetail.getG_name();
		if(!g_name.equals("")) {
		g_code = adminGoodsService.getCode(customOrderDetail);
		log.info(g_code);
		}
		
		model.addAttribute("g_code", g_code); 
		

		
		return "member/board/customOrder/customOrderDetail"; // /WEB-INF/views/member/board/customOrder/customOrderDetail.jsp
	}
	
	/***********************************************************
	 * Ŀ���� ��ǰ �ֹ� �Խ��� �۾��� �� ���
	 * ��û URL : http://localhost:8081/board/customOrder/customOrderWriteForm
	 ***********************************************************/
	@RequestMapping(value="/customOrderWriteForm")
	public String customOrderWriteForm(@ModelAttribute("data") CustomOrderBoardVO cobvo) {
		log.info("customOrderWriteForm ȣ�� ����");
		
		return "member/board/customOrder/customOrderWriteForm";
	}
	
	/***********************************************************
	 * Ŀ���� ��ǰ �ֹ� �Խ��� �۾���
	 * ��û URL : http://localhost:8081/board/customOrder/customOrderWriteForm
	 ***********************************************************/
	@RequestMapping(value="/customOrderInsert", method = RequestMethod.POST)
	public String customOrderInsert(CustomOrderBoardVO cobvo, HttpSession session, Model model) throws Exception {
		log.info("customOrderInsert ȣ�� ����");
		System.out.println("CustomOrderBoardVO" + cobvo);
		
		int result = 0;
		String url = "";
		
		/* ���� �������� ����
		cobvo.setM_id("abc123"); */
		MemberVO member = (MemberVO)session.getAttribute("member");
		cobvo.setM_id(member.getM_id());
		
		cobvo.setC_inquiry("����");
		
		result = customOrderBoardService.customOrderInsert(cobvo);
		if(result == 1) {
			url = "/board/customOrder/customOrderList";
		}else {
			url = "/board/customOrder/customOrderWriteForm";
		}
		
		return "redirect:" + url;
	}
	
	/***********************************************************
	 * Ŀ���� ��ǰ �ֹ� �Խ��� �� ����
	 * ��û URL : http://localhost:8081/board/customOrder/customOrderDelete
	 ***********************************************************/
	@RequestMapping(value="/customOrderDelete", method = RequestMethod.POST)
	public String customOrderDelete(@ModelAttribute CustomOrderBoardVO cobvo, RedirectAttributes ras) throws Exception {
		log.info("customOrderDelete ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = customOrderBoardService.customOrderDelete(cobvo);
		ras.addFlashAttribute("CustomOrderBoardVO", cobvo);
		
		if(result == 1) {
			url = "/board/customOrder/customOrderList";
		}else {
			url = "/board/customOrder/customOrderDetail";
		}
		
		return "redirect:" + url;
	}
	
	/***********************************************************
	 * Ŀ���� ��ǰ �ֹ� �Խ��� �� ���� �� ���
	 * ��û URL : http://localhost:8081/board/customOrder/customOrderUpdateForm
	 ***********************************************************/
	@RequestMapping(value="/customOrderUpdateForm")
	public String customOrderUpdateForm(@ModelAttribute("data") CustomOrderBoardVO cobvo, Model model) {
		log.info("customOrderUpdateForm ȣ�� ����");
		log.info("c_num" + cobvo.getC_num());
		
		CustomOrderBoardVO updateData = customOrderBoardService.customOrderUpdateForm(cobvo);
		
		model.addAttribute("updateData", updateData);
		
		return "member/board/customOrder/customOrderUpdateForm";
	}
	
	/***********************************************************
	 * Ŀ���� ��ǰ �ֹ� �Խ��� �� ����
	 * ��û URL : http://localhost:8081/board/customOrder/customOrderUpdate
	 ***********************************************************/
	@RequestMapping(value="/customOrderUpdate", method = RequestMethod.POST)
	public String customOrderUpdate(@ModelAttribute CustomOrderBoardVO cobvo, RedirectAttributes ras) throws Exception {
		log.info("customOrderUpdate ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = customOrderBoardService.customOrderUpdate(cobvo);
		ras.addFlashAttribute("data", cobvo);
		
		if(result == 1) {
			url = "/board/customOrder/customOrderDetail";
		}else {
			url = "/board/customOrder/customOrderUpdateForm";
		}
		
		return "redirect:" + url;
	}
	
	/***********************************************************
	 * Ŀ���� ��ǰ �ֹ� �Խ��� �ֹ� �ϱ�
	 * ��û URL : http://localhost:8081/board/customOrder/customOrderDetail
	 *********************************************************
	@RequestMapping(value="/customOrderDetail")
	public String customGoodsInsert(@ModelAttribute("data") MemberGoodsVO mgvo, Model model) {

		
		return "member/cart/goPayment";
	}**/
	
	
	
}


	
	
	
	
	

