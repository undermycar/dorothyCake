package com.dorothy.member.reply.customOrder.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dorothy.admin.reply.customOrder.vo.CustomOrderReplyVO;
import com.dorothy.member.reply.customOrder.service.ReplyCustomOrderService;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping(value="/replies/customOrder")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyCustomOrderController {
	
	private ReplyCustomOrderService replyCustomOrderService;
	
	/**************************************************************
	 * ��� ��� ����
	 * @return List<CustomOrderReplyVO>
	 **************************************************************/
	@GetMapping(value="/all/{c_num}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomOrderReplyVO> replyCustomOrderList(@PathVariable("c_num") Integer c_num){
		log.info("replyCustomOrderList ȣ�� ����");
		
		List<CustomOrderReplyVO> replyList = null;
		replyList = replyCustomOrderService.replyCustomOrderList(c_num);
		return replyList;
	}
	
	

}
