package com.dorothy.admin.reply.customOrder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dorothy.admin.reply.customOrder.dao.CustomOrderReplyDao;
import com.dorothy.admin.reply.customOrder.vo.CustomOrderReplyVO;

import lombok.Setter;

@Service
public class CustomOrderReplyServiceImpl implements CustomOrderReplyService {
	
	@Setter(onMethod_ = @Autowired)
	private CustomOrderReplyDao customOrderReplyDao;
	
	// ��� ��� ����
	@Override
	public List<CustomOrderReplyVO> replyCustomOrderList(Integer c_num) {
		List<CustomOrderReplyVO> replyList = null;
		replyList = customOrderReplyDao.replyCustomOrderList(c_num);
		return replyList;
	}
	
	// ��� ���� ����
	@Override
	public int replyCustomOrderInsert(CustomOrderReplyVO corvo) {
		int result = 0;
		result = customOrderReplyDao.replyCustomOrderInsert(corvo);
		
		if (result == 1) {
			customOrderReplyDao.inquiryUpdate(corvo); 			
		}
		return result;
	}
	
	// ��� ���� ����
	@Override
	public int replyCustomOrderDelete(int rc_num) {
		int result = 0;
		result = customOrderReplyDao.replyCustomOrderDelete(rc_num);
		return result;
	}

	// ��� ���� ����
	@Override
	public int replyCustomOrderUpdate(CustomOrderReplyVO corvo) {
		int result = 0;
		result = customOrderReplyDao.replyCustomOrderUpdate(corvo);
		return result;
	}

}
