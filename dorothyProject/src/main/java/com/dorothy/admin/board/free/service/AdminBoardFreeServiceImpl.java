package com.dorothy.admin.board.free.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dorothy.common.file.FileUploadUtil;
import com.dorothy.member.board.free.dao.BoardFreeDao;
import com.dorothy.member.board.free.vo.BoardFreeVO;
import com.dorothy.member.reply.free.dao.ReplyFreeDao;

import lombok.Setter;

@Service
public class AdminBoardFreeServiceImpl implements AdminBoardFreeService {
	
	@Setter(onMethod_ = @Autowired)
	private BoardFreeDao boardFreeDao;
	
	@Setter(onMethod_ = @Autowired)
	private ReplyFreeDao replyFreeDao;
	
	// ������ �����Խ��� �� ��� ����
	@Override
	public List<BoardFreeVO> boardFreeList(BoardFreeVO bfvo) {
		List<BoardFreeVO> adminFreeList = null;
		
		adminFreeList = boardFreeDao.boardFreeList(bfvo);
		return adminFreeList;
	}
	
	// ��ü ���ڵ� �� ����
	@Override
	public int boardFreeListCnt(BoardFreeVO bfvo) {
		return boardFreeDao.boardFreeListCnt(bfvo);
	}

	// ������ �����Խ��� �� �󼼺��� ����
	@Override
	public BoardFreeVO boardFreeDetail(BoardFreeVO bfvo) {
		BoardFreeVO bfDetail = null;
		bfDetail = boardFreeDao.boardFreeDetail(bfvo);
		if(bfDetail != null) {
			bfDetail.setF_content(bfDetail.getF_content().toString().replaceAll("\n", "<br />"));
		}
		
		return bfDetail;
	}
	
	// ������ �����Խ��� �� ��ü ���� ����
	@Override
	public int deleteAll(String[] numArr) throws Exception {
		int result = 0;
		
		for(int i = 0 ; i < numArr.length ; i++) {
			int f_num = Integer.parseInt(numArr[i]);
			BoardFreeVO bfvo = boardFreeDao.deleteVO(f_num);
			
			if(!bfvo.getF_file().isEmpty()) {
				FileUploadUtil.fileDelete(bfvo.getF_file());
			}
			
			if(bfvo.getF_replycnt() > 0) {
				replyFreeDao.replyAllDelete(bfvo.getF_num());
			}
			
			result += boardFreeDao.boardFreeDelete(bfvo);
		}
		
		return result;
	}

	
	// �������� �� ���� ����
	@Override
	public int boardFreeDelete(BoardFreeVO bfvo) throws Exception {
		int result = 0;
		
		if(!bfvo.getF_file().isEmpty()){
			FileUploadUtil.fileDelete(bfvo.getF_file());
		}
		
		if(bfvo.getF_replycnt() > 0) {
			replyFreeDao.replyAllDelete(bfvo.getF_num());
		}
		
		result = boardFreeDao.boardFreeDelete(bfvo);
		return result;
	}

}
