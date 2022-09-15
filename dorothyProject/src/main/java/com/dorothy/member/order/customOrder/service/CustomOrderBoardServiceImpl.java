// ���� Ŭ����
package com.dorothy.member.order.customOrder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dorothy.common.file.FileUploadUtil;
import com.dorothy.admin.reply.customOrder.dao.CustomOrderReplyDao;

import com.dorothy.member.order.customOrder.dao.CustomOrderBoardDao;
import com.dorothy.member.order.customOrder.vo.CustomOrderBoardVO;

import lombok.Setter;

@Service
public class CustomOrderBoardServiceImpl implements CustomOrderBoardService {
	
	@Setter(onMethod_ = @Autowired)
	private CustomOrderBoardDao customOrderBoardDao;
	
	@Setter(onMethod_ = @Autowired)
	private CustomOrderReplyDao customOrderReplyDao;

	// Ŀ���� ��ǰ �ֹ� �Խ��� �� ��� ����
	@Override
	public List<CustomOrderBoardVO> customOrderList(CustomOrderBoardVO cobvo) {
		List<CustomOrderBoardVO> list = null;
		list = customOrderBoardDao.customOrderList(cobvo);
		return list;
	}
	
	// ��ü ���ڵ� �� ����
	@Override
	public int customOrderListCnt(CustomOrderBoardVO cobvo) {
		return customOrderBoardDao.customOrderListCnt(cobvo);
	}
	
	// Ŀ���� ��ǰ �ֹ� �Խ��� �� �󼼺��� ����
	@Override
	public CustomOrderBoardVO customOrderDetail(CustomOrderBoardVO cobvo) {
		CustomOrderBoardVO coDetail = null;

		coDetail = customOrderBoardDao.customOrderDetail(cobvo);
		if (coDetail != null) {
			coDetail.setC_content(coDetail.getC_content().toString().replaceAll("\n", "<br />"));
		}

		return coDetail;
	}

	// Ŀ���� ��ǰ �ֹ� �Խ��� �۾��� ����
	@Override
	public int customOrderInsert(CustomOrderBoardVO cobvo) throws Exception {
		int result = 0;

		if (cobvo.getMfile().getSize() > 0) {
			String fileName = FileUploadUtil.fileUpload(cobvo.getMfile(), "customOrder");
			System.out.println("fileName" + fileName);
			cobvo.setC_file(fileName);
		}

		result = customOrderBoardDao.customOrderInsert(cobvo);
		return result;
	}

	// Ŀ���� ��ǰ �ֹ� �Խ��� �� ���� ����
	@Override
	public int customOrderDelete(CustomOrderBoardVO cobvo) throws Exception {
		int result = 0;
		
		if(!cobvo.getC_file().isEmpty()) {
			FileUploadUtil.fileDelete(cobvo.getC_file());
		}
		
		if(cobvo.getC_replycnt() > 0) {
			customOrderReplyDao.replyAllDelete(cobvo.getC_num());
		}
		
		result = customOrderBoardDao.customOrderDelete(cobvo);
		return result;
	}
	
	// Ŀ���� ��ǰ �ֹ� �Խ��� �� ���� ���� ���� ������ ����
	@Override
	public CustomOrderBoardVO customOrderUpdateForm(CustomOrderBoardVO cobvo) {
		CustomOrderBoardVO updateData = null;
		updateData = customOrderBoardDao.customOrderDetail(cobvo);
		return updateData;
	}

	// Ŀ���� ��ǰ �ֹ� �Խ��� �� ���� ����
	@Override
	public int customOrderUpdate(CustomOrderBoardVO cobvo) throws Exception {
		int result = 0;

		if (!cobvo.getMfile().isEmpty()) {
			if (!cobvo.getC_file().isEmpty()) {
				FileUploadUtil.fileDelete(cobvo.getC_file());
			}

			String fileName = FileUploadUtil.fileUpload(cobvo.getMfile(), "board/customOrder");
			cobvo.setC_file(fileName);
		}
		result = customOrderBoardDao.customOrderUpdate(cobvo);
		return result;
	}
	
	
	

	
	

	

	
	
	

}
