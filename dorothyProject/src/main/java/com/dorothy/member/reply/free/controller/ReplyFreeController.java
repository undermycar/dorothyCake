package com.dorothy.member.reply.free.controller;

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

import com.dorothy.member.reply.free.service.ReplyFreeService;
import com.dorothy.member.reply.free.vo.ReplyFreeVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping(value="/replies/free")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyFreeController {
	
	private ReplyFreeService replyFreeService;
	
	/**************************************************************
	 * ��� ��� ����
	 * @return List<ReplyFreeVO>
	 **************************************************************/
	@GetMapping(value="/all/{f_num}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ReplyFreeVO> replyFreeList(@PathVariable("f_num") Integer f_num){
		log.info("replyFreeList ȣ�� ����");
		
		List<ReplyFreeVO> replyList = null;
		replyList = replyFreeService.replyFreeList(f_num);
		return replyList;
	}
	
	/**************************************************************
	 * ��� ���� ����
	 * @return String
	 **************************************************************/
	@PostMapping(value = "/replyFreeInsert", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public String replyFreeInsert(@RequestBody ReplyFreeVO rfvo) {
		log.info("replyFreeInsert ȣ�� ����");
		log.info("ReplyFreeVO : " + rfvo);
		
		int result = 0;
		result = replyFreeService.replyFreeInsert(rfvo);
		return (result == 1) ? "SUCCESS" : "FAILURE";
	}
	
	/**************************************************************
	 * ��� ���� ����
	 * @return String
	 **************************************************************/
	@DeleteMapping(value = "/{rf_num}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String replyFreeDelete(@PathVariable("rf_num") int rf_num) {
		log.info("replyFreeDelete ȣ�� ����");
		log.info("rf_num : " + rf_num);
		
		int result = replyFreeService.replyFreeDelete(rf_num);
		return (result == 1) ? "SUCCESS" : "FAILURE";
	}
	
	/**************************************************************
	 * ��� ���� ����
	 * @return String
	 **************************************************************/
	@PutMapping(value = "/{rf_num}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public String replyFreeUpdate(@PathVariable("rf_num") Integer rf_num, @RequestBody ReplyFreeVO rfvo) { 
		log.info("replyFreeUpdate ȣ�� ����");
	  
		rfvo.setRf_num(rf_num);
		int result = replyFreeService.replyFreeUpdate(rfvo);
		return (result == 1) ? "SUCCESS" : "FAILURE";
	}
	 
}
