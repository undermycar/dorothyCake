package com.dorothy.admin.order.customOrder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dorothy.admin.goods.dao.AdminGoodsDao;
import com.dorothy.admin.reply.customOrder.dao.CustomOrderReplyDao;
import com.dorothy.member.order.customOrder.dao.CustomOrderBoardDao;
import com.dorothy.member.order.customOrder.vo.CustomOrderBoardVO;

import lombok.Setter;

@Service
public class AdminCustomOrderServiceImpl implements AdminCustomOrderService {
	
	@Setter(onMethod_ = @Autowired)
	private CustomOrderBoardDao customOrderBoardDao;
	
	@Setter(onMethod_ = @Autowired)
	private CustomOrderReplyDao customOrderReplyDao;
	
	@Setter(onMethod_ = @Autowired)
	private AdminGoodsDao adminGoodsDao;
	
	// ������ Ŀ���� ��ǰ �ֹ� �Խ��� �� ��� ����
	@Override
	public List<CustomOrderBoardVO> customOrderList(CustomOrderBoardVO cobvo) {
		List<CustomOrderBoardVO> adminCustomOrderList = null;
		
		adminCustomOrderList = customOrderBoardDao.customOrderList(cobvo);
		return adminCustomOrderList;
	}

	// ��ü ���ڵ� �� ����
	@Override
	public int customOrderListCnt(CustomOrderBoardVO cobvo) {
		return customOrderBoardDao.customOrderListCnt(cobvo);
	}
	
	// ������ Ŀ���� ��ǰ �ֹ� �Խ��� �� �󼼺��� ����
	@Override
	public CustomOrderBoardVO customOrderDetail(CustomOrderBoardVO cobvo) {
		CustomOrderBoardVO coDetail = null;

		coDetail = customOrderBoardDao.customOrderDetail(cobvo);
		if (coDetail != null) {
			coDetail.setC_content(coDetail.getC_content().toString().replaceAll("\n", "<br />"));
		}

		return coDetail;
	}

	// ������ Ŀ���� ��ǰ �ֹ� �Խ��� ���� ������Ʈ
	@Override
	public int customOrderPriceupdate(CustomOrderBoardVO cobvo) {
		int coDetail = customOrderBoardDao.customOrderPriceupdate(cobvo);
		return coDetail;
	}

}
