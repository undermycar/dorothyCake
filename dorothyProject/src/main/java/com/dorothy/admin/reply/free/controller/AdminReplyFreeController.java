package com.dorothy.admin.reply.free.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dorothy.admin.reply.free.service.AdminReplyFreeService;
import com.dorothy.member.reply.free.vo.ReplyFreeVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping(value="/admin/replies/free")
@RestController
@Log4j
@AllArgsConstructor
public class AdminReplyFreeController {
	
	private AdminReplyFreeService adminReplyFreeService;
	
	/**************************************************************
	 * ��� ��� ����
	 * @return List<ReplyFreeVO>
	 **************************************************************/
	@GetMapping(value="/all/{f_num}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ReplyFreeVO> replyFreeList(@PathVariable("f_num") Integer f_num){
		log.info("admin replyFreeList ȣ�� ����");
		
		List<ReplyFreeVO> replyList = null;
		replyList = adminReplyFreeService.replyFreeList(f_num);
		return replyList;
	}
}
