package com.dorothy.member.reply.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dorothy.admin.reply.qna.dao.AdminReplyDao;
import com.dorothy.admin.reply.qna.vo.AdminReplyVO;

import lombok.Setter;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Setter(onMethod_ = @Autowired)
	private AdminReplyDao replyDao;
	
	// ��� ��� ����
	@Override
	public List<AdminReplyVO> replyList(Integer q_num) {
		List<AdminReplyVO> list = null;
		list = replyDao.replyList(q_num);
		return list;
	}
	
	// ��� �Է� ����
	@Override
	public int replyInsert(AdminReplyVO rvo) {
		int result = 0;
		result = replyDao.replyInsert(rvo);
		return result;
	}
	
	// ��� ���� ����
	@Override
	public int replyDelete(Integer rq_num) {
		int result = 0;
		result = replyDao.replyDelete(rq_num);
		return result;
	}
	
	// ��� ���� ����
	@Override
	public int replyUpdate(AdminReplyVO rvo) {
		int result = 0;
		result = replyDao.replyUpdate(rvo);
		return result;
	}

	

}
