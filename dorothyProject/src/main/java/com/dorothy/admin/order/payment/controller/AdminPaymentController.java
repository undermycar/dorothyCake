package com.dorothy.admin.order.payment.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dorothy.admin.order.payment.service.AdminPaymentService;
import com.dorothy.common.excel.ListExcelView;
import com.dorothy.common.vo.PageDTO;
import com.dorothy.member.payment.vo.PaymentVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/admin/payment")
@AllArgsConstructor
public class AdminPaymentController {
	
	private AdminPaymentService adminPaymentService;
	
	/***********************************************************
	 * ���� ����Ʈ
	 * ��û URL : http://localhost:8080/admin/payment/paymentList
	 ***********************************************************/
	@RequestMapping(value="/paymentList", method = RequestMethod.GET)
	public String paymentList(@ModelAttribute("data") PaymentVO pvo, Model model) {
		log.info("paymentList ȣ�� ����");
		
		List<PaymentVO> paymentList = adminPaymentService.paymentList(pvo);
		model.addAttribute("paymentList", paymentList);
		
		int total = adminPaymentService.paymentListCnt(pvo);
		model.addAttribute("pageMaker", new PageDTO(pvo, total));
		
		int count = total - (pvo.getPageNum() - 1) * pvo.getAmount();
		model.addAttribute("count", count);
		
		return "admin/payment/paymentList";
	}
	
	/***********************************************************
	 * ���� ���
	 * ��û URL : http://localhost:8080/admin/payment/paymentCancel
	 ***********************************************************/
	@RequestMapping(value="/paymentCancel", method = RequestMethod.POST)
	@ResponseBody
	public String paymentCancel(HttpServletRequest request) {
		log.info("paymentCancel ȣ�� ����");
		String uid = request.getParameter("merchant_uid");
		PaymentVO pvo = new PaymentVO();
		pvo.setP_merchant_uid(uid);
		pvo.setP_state("�������");
		
		int result = adminPaymentService.paymentCancel(pvo);
		
		return "admin/payment/paymentList";
	}
	
	/***********************************************************
	 * ���� ���� �ٿ�ε�
	 * ��û URL : http://localhost:8080/admin/payment/paymentExcel
	 ***********************************************************/
	  @RequestMapping(value="/paymentExcel", method = RequestMethod.GET) public
	  ModelAndView paymentExcel(@ModelAttribute PaymentVO pvo) {
		  log.info("paymentExcel ȣ�� ����");
		  
		  List<PaymentVO> paymentList = adminPaymentService.paymentListExcel(pvo);
		  
		  ModelAndView mv = new ModelAndView(new ListExcelView());
		  mv.addObject("paymentList", paymentList);
		  mv.addObject("template", "payment.xlsx");
		  mv.addObject("file_name", "payment");
		  
		  return mv;
	  }
}
