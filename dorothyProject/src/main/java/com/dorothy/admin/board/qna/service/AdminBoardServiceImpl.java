package com.dorothy.admin.board.qna.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dorothy.admin.reply.qna.dao.AdminReplyDao;
import com.dorothy.common.file.FileUploadUtil;
import com.dorothy.member.board.qna.dao.BoardDao;
import com.dorothy.member.board.qna.vo.BoardVO;

import lombok.Setter;
@Service
public class AdminBoardServiceImpl implements AdminBoardService {
	
	@Setter(onMethod_ =@Autowired )
	private BoardDao boardDao;
	
	@Setter(onMethod_ = @Autowired)
	private AdminReplyDao replyDao;

	/*
	 * @Setter(onMethod_ = @Autowired) private AdminReplyDao replyDao;
	 */
	
	// ������ Qna�Խ��� �� ��� ����
	@Override
	public List<BoardVO> boardList(BoardVO bvo) {
		List<BoardVO> aList = null;
		
		aList = boardDao.boardList(bvo); 
		return aList;
		
	}
	// ��ü ���ڵ� �� ����
	@Override
	public int boardListCnt(BoardVO bvo) {
		int countNum = 0;
		countNum = boardDao.boardListCnt(bvo);
		return countNum;
	}
	// ������ QNA�Խ��� �� �󼼺��� ����
	@Override
	public BoardVO boardDetail(BoardVO bvo) {
		BoardVO detail = null;
		
		boardDao.readcntUpdate(bvo);
		
		detail = boardDao.boardDetail(bvo);
		if(detail != null) {
			detail.setQ_content(detail.getQ_content().toString().replaceAll("\n", "<br />"));
		}
		return detail;
	}
	// �������� �� ���� ����
	@Override
	public int boardDelete(BoardVO bvo) throws Exception {
		int result = 0;
		if(!bvo.getQ_file().isEmpty()) {
			FileUploadUtil.fileDelete(bvo.getQ_file());
			/* FileUploadUtil.fileDelete(bvo.getQ_thumb()); */
		}
		if(bvo.getQ_replycnt() > 0) {
			replyDao.replyAllDelete(bvo.getQ_num());
		}
		
		result = boardDao.boardDelete(bvo);
		return result;
		
		/*
		 * if(bvo.getQ_replycnt() > 0) { 
		 * replyDao.replyAllDelete(bvo.getQ_num());
		 * }
		 */
	}



//	@Override
//	public int replyCnt(int q_num) {
//		int result = 0;
//		result = replyDao.replyCnt(q_num);
//		return result;
//	}
	// ������ �����Խ��� �� ��ü ���� ����
	@Override
	public int deleteAll(String[] numArr) throws Exception {
		int result = 0;
		
		for(int i = 0 ; i < numArr.length ; i++) {
			int q_num = Integer.parseInt(numArr[i]);
			BoardVO bvo = boardDao.deleteVO(q_num);
			
			if(!bvo.getQ_file().isEmpty()) {
				FileUploadUtil.fileDelete(bvo.getQ_file());
			}
			
			if(bvo.getQ_replycnt() > 0) {
				replyDao.replyAllDelete(bvo.getQ_num());
			}
			
			result += boardDao.boardDelete(bvo);
		}
		
		return result;
	}
	@Override
	public int replyCnt(int q_num) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

//	@Override
//	public List<BoardVO> boardListExcel(BoardVO bvo) {
//		List<BoardVO> boardList = null;
//		
//		boardList = boardDao.boardListExcel(bvo); 
//		return boardList;
//	}
	
}
