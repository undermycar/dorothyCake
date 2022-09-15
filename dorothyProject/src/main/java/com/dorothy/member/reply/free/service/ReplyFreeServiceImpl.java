package com.dorothy.member.reply.free.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dorothy.member.reply.free.dao.ReplyFreeDao;
import com.dorothy.member.reply.free.vo.ReplyFreeVO;

import lombok.Setter;

@Service
public class ReplyFreeServiceImpl implements ReplyFreeService {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyFreeDao replyFreeDao;
	
	// ��� ��� ����
	@Override
	public List<ReplyFreeVO> replyFreeList(Integer f_num) {
		List<ReplyFreeVO> replyList = null;
		replyList = replyFreeDao.replyFreeList(f_num);
		return replyList;
	}
	
	// ��� ���� ����
	@Override
	public int replyFreeInsert(ReplyFreeVO rfvo) {
		int result = 0;
		result = replyFreeDao.replyFreeInsert(rfvo);
		return result;
	}
	
	// ��� ���� ����
	@Override
	public int replyFreeDelete(int rf_num) {
		int result = 0;
		result = replyFreeDao.replyFreeDelete(rf_num);
		return result;
	}
	
	// ��� ���� ����
	@Override
	public int replyFreeUpdate(ReplyFreeVO rfvo) {
		int result = 0;
		result = replyFreeDao.replyFreeUpdate(rfvo);
		return result;
	}
	
	// �ش��ϴ� ���� ��� ��ü ����
	@Override
	public int replyAllDelete(int f_num) {
		int result = 0;
		result = replyFreeDao.replyAllDelete(f_num);
		return result;
	}

}
