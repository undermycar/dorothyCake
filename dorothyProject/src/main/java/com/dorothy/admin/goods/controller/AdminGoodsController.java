package com.dorothy.admin.goods.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dorothy.admin.goods.service.AdminGoodsService;
import com.dorothy.common.excel.ListExcelView;
import com.dorothy.common.vo.PageDTO;
import com.dorothy.member.goods.vo.MemberGoodsVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/admin/goods")
@AllArgsConstructor
@Log4j
public class AdminGoodsController {
	
	private AdminGoodsService adminGoodsService;
	
	@RequestMapping(value="/goodsList", method = RequestMethod.GET)
	public String goodsList(@ModelAttribute("data") MemberGoodsVO gvo, Model model){
		log.info("goodsList ȣ�� ����");

		List<MemberGoodsVO> goodsList = adminGoodsService.goodsList(gvo);
		model.addAttribute("goodsList", goodsList);
		
		// ��ü ���ڵ� ��
		int total = adminGoodsService.goodsListCnt(gvo);
		// ����¡ ó��
		model.addAttribute("pageMaker", new PageDTO(gvo, total));
		
		return "admin/goods/goodsList"; // /WEB-INF/views/member/goods/goodsList.jsp
	}
	
	@RequestMapping(value="/goodsDetail", method = RequestMethod.GET)
	public String goodsDetail(@ModelAttribute("data") MemberGoodsVO gvo, Model model) {
		log.info("goodsDetail ȣ�� ����");
		
		MemberGoodsVO detail = adminGoodsService.goodsDetail(gvo);
		model.addAttribute("detail", detail);
		
		return "admin/goods/goodsDetail"; // /WEB-INF/views/admin/goods/goodsDetail.jsp
	}
	
	@RequestMapping(value="/goodsDelete", method = RequestMethod.POST)
	public String goodsDelete(@ModelAttribute MemberGoodsVO gvo, RedirectAttributes ras) throws Exception {
		log.info("goodsDelete ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = adminGoodsService.goodsDelete(gvo);
		ras.addFlashAttribute("GoodsVO", gvo);
		
		if(result == 1) {
			url = "/admin/goods/goodsList";
		}else {
			url = "/admin/goods/goodsDetail";
		}
		
		return "redirect:" + url;
	}
	
	@RequestMapping(value="/deleteAll", method = RequestMethod.POST)
	public String deleteAll(HttpServletRequest request) throws Exception {
		log.info("deleteAll ȣ�� ����");
		
		String[] numArr = request.getParameterValues("numArr");
		
		adminGoodsService.deleteAll(numArr);
		
		return "/admin/goods/goodsList";		
	}
	
	@RequestMapping(value="/goodsUpdateForm")
	public String goodsUpdateForm(@ModelAttribute("data") MemberGoodsVO gvo, Model model) throws Exception {
		log.info("goodsUpdateForm ȣ�� ����");
		log.info("g_code : " +gvo.getG_code());
		
		MemberGoodsVO updateData = adminGoodsService.goodsUpateForm(gvo);
		
		model.addAttribute("updateData", updateData);
		
		return "admin/goods/goodsUpdateForm";
	}
	
	@RequestMapping(value="/goodsUpdate", method = RequestMethod.POST)
	public String goodsUpdate(@ModelAttribute MemberGoodsVO gvo, RedirectAttributes ras) throws Exception {
		log.info("goodsUpdate ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = adminGoodsService.goodsUpdate(gvo);
		ras.addFlashAttribute("data", gvo);
		
		if(result == 1) {
			url = "/admin/goods/goodsDetail";
		}else {
			url = "/admin/goods/goodsUpdateForm";
		}
		
		return "redirect:" + url;
	}
	
	@RequestMapping(value="/goodsInsert", method = RequestMethod.POST)
	public String goodsInsert(MemberGoodsVO gvo, Model model) throws Exception {
		log.info("goodsInsert ȣ�� ����");
		
		int result = 0;
		String url = "";
		
		result = adminGoodsService.goodsInsert(gvo);
		if(result == 1) {
			url = "/admin/goods/goodsList";
		}else {
			url = "/admin/goods/goodsInsertForm";
		}
		
		return "redirect:" + url;
	}
	
	@RequestMapping(value="/goodsInsertForm")
	public String goodsInsertForm(@ModelAttribute("data") MemberGoodsVO gvo) {
		log.info("goodsInsertForm ȣ�� ����");
		
		return "admin/goods/goodsInsertForm"; // /WEB-INF/views/admin/goods/goodsInsertForm.jsp
	}
	
	@RequestMapping(value="/goodsExcel", method=RequestMethod.GET)
	public ModelAndView goodsExcel(@ModelAttribute MemberGoodsVO gvo) {
		log.info("goodsExcel ȣ��");
		
		List<MemberGoodsVO> goodsList = adminGoodsService.goodsListExcel(gvo);
		
		ModelAndView mv = new ModelAndView(new ListExcelView());
		mv.addObject("goodsList", goodsList);
		mv.addObject("template", "goods.xlsx");
		mv.addObject("file_name", "goods");
		
		return mv;
	}
}
