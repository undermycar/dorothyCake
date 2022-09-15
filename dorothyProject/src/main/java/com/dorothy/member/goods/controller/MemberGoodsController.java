package com.dorothy.member.goods.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dorothy.member.goods.service.MemberGoodsService;
import com.dorothy.member.goods.vo.MemberGoodsVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping(value = "/member/goods")
@AllArgsConstructor
@Log4j
public class MemberGoodsController {
	
	private MemberGoodsService memberGoodsService;
	
	// �����̾� ����ũ
	@GetMapping(value="/goodsList")
	public String goodsList(MemberGoodsVO gvo, Model model){
		log.info("goodsList ȣ�� ����");

		List<MemberGoodsVO> goodsList = memberGoodsService.goodsList(gvo);
		model.addAttribute("goodsList", goodsList);
		
		return "member/goods/goodsList"; // /WEB-INF/views/member/goods/goodsList.jsp
	}
	
	// ���͸� ����ũ g_type 2
	@GetMapping(value="/goodsListLetter")
	public String goodsListLetter(MemberGoodsVO gvo, Model model){
		log.info("goodsListLetter ȣ�� ����");

		List<MemberGoodsVO> goodsList = memberGoodsService.goodsListLetter(gvo);
		model.addAttribute("goodsList", goodsList);
		
		return "member/goods/goodsList"; // /WEB-INF/views/member/goods/goodsListLetter.jsp
	}
	
	// ���͸� ����ũ g_type 2
	@GetMapping(value="/goodsListAddition")
	public String goodsListAddition(MemberGoodsVO gvo, Model model){
		log.info("goodsListAddition ȣ�� ����");

		List<MemberGoodsVO> goodsList = memberGoodsService.goodsListAddition(gvo);
		model.addAttribute("goodsList", goodsList);
		
		return "member/goods/goodsList"; // /WEB-INF/views/member/goods/goodsListAddition.jsp
	}
	
	@GetMapping(value="/goodsDetail")
	public String goodsDetail(MemberGoodsVO gvo, Model model) {
		log.info("goodsDetail ȣ�� ����");
		
		LocalDate today = LocalDate.now();
		
		MemberGoodsVO detail = memberGoodsService.goodsDetail(gvo);
		model.addAttribute("detail", detail);
		model.addAttribute("today", today.toString());
		
		return "member/goods/goodsDetail"; // /WEB-INF/views/member/goods/goodsDetail.jsp
	}
}
