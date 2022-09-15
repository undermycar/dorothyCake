package com.dorothy.admin.reply.customOrder.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dorothy.admin.reply.customOrder.service.CustomOrderReplyService;
import com.dorothy.admin.reply.customOrder.vo.CustomOrderReplyVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping(value="/admin/replies/customOrder")
@Log4j
@AllArgsConstructor
public class CustomOrderReplyController {
	
	private CustomOrderReplyService customOrderReplyService;
	
	/**************************************************************
	 * ��� ��� ����
	 * @return List<CustomOrderReplyVO>
	 **************************************************************/
	@GetMapping(value="/all/{c_num}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<CustomOrderReplyVO> replyCustomOrderList(@PathVariable("c_num") Integer c_num){
		log.info("admin replyCustomOrderList ȣ�� ����");
		
		List<CustomOrderReplyVO> replyList = null;
		replyList = customOrderReplyService.replyCustomOrderList(c_num);
		return replyList;
	}
	
	/**************************************************************
	 * ��� ���� ����
	 * @return String
	 **************************************************************/
	@PostMapping(value = "/replyCustomOrderInsert", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public String replyCustomOrderInsert(@RequestBody CustomOrderReplyVO corvo) {
		log.info("replyCustomOrderInsert ȣ�� ����");
		log.info("CustomOrderReplyVO : " + corvo);
		
		int result = 0;
		result = customOrderReplyService.replyCustomOrderInsert(corvo);
		return (result == 1) ? "SUCCESS" : "FAILURE";
	}
	
	/**************************************************************
	 * ��� ���� ����
	 * @return String
	 **************************************************************/
	@DeleteMapping(value = "/{rc_num}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String replyCustomOrderDelete(@PathVariable("rc_num") int rc_num) {
		log.info("replyCustomOrderDelete ȣ�� ����");
		log.info("rc_num : " + rc_num);
		
		int result = customOrderReplyService.replyCustomOrderDelete(rc_num);
		return (result == 1) ? "SUCCESS" : "FAILURE";
	}
	
	/**************************************************************
	 * ��� ���� ����
	 * @return String
	 **************************************************************/
	@PutMapping(value = "/{rc_num}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public String replyCustomOrderUpdate(@PathVariable("rc_num") Integer rc_num, @RequestBody CustomOrderReplyVO corvo) { 
		log.info("replyCustomOrderUpdate ȣ�� ����");
	  
		corvo.setRc_num(rc_num);
		int result = customOrderReplyService.replyCustomOrderUpdate(corvo);
		return (result == 1) ? "SUCCESS" : "FAILURE";
	}
	
	

}
