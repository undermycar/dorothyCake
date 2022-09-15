package com.dorothy.admin.reply.qna.controller;

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

import com.dorothy.admin.reply.qna.service.AdminReplyService;
import com.dorothy.admin.reply.qna.vo.AdminReplyVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping(value="/admin/replies/qna")
@RestController
@Log4j
@AllArgsConstructor
public class AdminReplyController {

	private AdminReplyService replyService;
	
	/**************************************************************
	 * ��� ��� ����
	 * @return List<ReplyVO>
	 **************************************************************/
	@GetMapping(value="/all/{q_num}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AdminReplyVO> replyList(@PathVariable("q_num") Integer q_num){
		log.info("replyList ȣ�� ����");
		
		List<AdminReplyVO> replyList = null;
		replyList = replyService.replyList(q_num);
		return replyList;
	}
	
	/**************************************************************
	 * ��� ���� ����
	 * @return String
	 **************************************************************/
	@PostMapping(value = "/replyInsert", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public String replyInsert(@RequestBody AdminReplyVO rvo) {
		log.info("replyInsert ȣ�� ����");
		log.info("AdminReplyVO : " + rvo);
		
		int result = 0;
		result = replyService.replyInsert(rvo);
		log.info("result : " + result);
		return (result == 1) ? "SUCCESS" : "FAILURE";
	}
	
	/**************************************************************
	 * ��� ���� ����
	 * @return String
	 **************************************************************/
	@DeleteMapping(value = "/{rq_num}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String replyDelete(@PathVariable("rq_num") int rq_num) {
		log.info("replyDelete ȣ�� ����");
		log.info("rq_num : " + rq_num);
		
		int result = replyService.replyDelete(rq_num);
		return (result == 1) ? "SUCCESS" : "FAILURE";
	}
	
	/**************************************************************
	 * ��� ���� ����
	 * @return String
	 **************************************************************/
	@PutMapping(value = "/{rq_num}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public String replyUpdate(@PathVariable("rq_num") Integer rq_num, @RequestBody AdminReplyVO rvo) { 
		log.info("replyUpdate ȣ�� ����");
	  
		rvo.setRq_num(rq_num);
		int result = replyService.replyUpdate(rvo);
		return (result == 1) ? "SUCCESS" : "FAILURE";
	}
	 
}
