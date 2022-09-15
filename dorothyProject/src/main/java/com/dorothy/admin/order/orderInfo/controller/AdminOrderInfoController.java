package com.dorothy.admin.order.orderInfo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dorothy.admin.order.orderInfo.service.AdminOrderInfoService;
import com.dorothy.common.excel.ListExcelView;
import com.dorothy.common.vo.PageDTO;
import com.dorothy.member.login.vo.MemberVO;
import com.dorothy.member.order.orderInfo.vo.OrderInfoVO;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Controller
@Log4j
@RequestMapping(value="/admin/order/*")
@AllArgsConstructor
public class AdminOrderInfoController {
private AdminOrderInfoService adminOrderInfoService;
	
	/***********************************************************
	 * �ֹ� ����Ʈ
	 * ��û URL : http://localhost:8080/admin/order/oderInfoList
	 ***********************************************************/
	@RequestMapping(value="/orderInfoList", method = RequestMethod.GET)
	public String orderInfoList(@ModelAttribute("data") OrderInfoVO ovo, HttpServletRequest req, Model model) {
		log.info("OrderInfoList ȣ�� ����");
		

		
		List<OrderInfoVO> orderInfoList = adminOrderInfoService.adminOrderInfoList(ovo);
		model.addAttribute("orderInfoList", orderInfoList);
		
		//��ü ���ڵ� �� ����
		int total = adminOrderInfoService.orderInfoListCnt(ovo);
		model.addAttribute("pageMaker", new PageDTO(ovo, total));
		
		int count = total - (ovo.getPageNum()-1) * ovo.getAmount();
		model.addAttribute("count", count);
		
		return "admin/order/orderInfoList";
	}
	
	/***********************************************************
	 * ������ �ֹ������Խ��� �� �󼼺��� ����
	 * ��û URL : http://localhost:8080/admin/order/orderInfo/orderInfoList
	 ***********************************************************/
	@RequestMapping(value="/orderInfoDetail", method=RequestMethod.GET)
//	@GetMapping("/orderInfoDetail")
	public String orderInfoDetail(@ModelAttribute("data") OrderInfoVO ovo, Model model) {
		log.info("orderInfoDetail ȣ�� ����");
		
		
		OrderInfoVO detail = adminOrderInfoService.orderInfoDetail(ovo);
		model.addAttribute("detail", detail);
		
		return "admin/order/orderInfoDetail";
	}
	
	/***********************************************************
	 * �ֹ� ���� �ٿ�ε�
	 * ��û URL : http://localhost:8080/admin/order/orderExcel
	 ***********************************************************/
	  @RequestMapping(value="/orderExcel", method=RequestMethod.GET)
	   public ModelAndView orderExcel(@ModelAttribute OrderInfoVO ovo) {
	      log.info("orderExcel ȣ��");
	      
	      List<OrderInfoVO> orderList = adminOrderInfoService.orderListExcel(ovo);
	      
	      ModelAndView mv = new ModelAndView(new ListExcelView());
	      mv.addObject("orderInfoList", orderList);
	      mv.addObject("template", "order.xlsx");
	      mv.addObject("file_name", "order");
	      
	      return mv;
	   }
}
