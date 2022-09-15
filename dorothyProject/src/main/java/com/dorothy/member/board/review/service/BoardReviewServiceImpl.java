package com.dorothy.member.board.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dorothy.admin.reply.review.dao.AdminReplyReviewDao;
import com.dorothy.common.file.FileUploadUtil;
import com.dorothy.member.board.review.dao.BoardReviewDao;
import com.dorothy.member.board.review.vo.BoardReviewVO;

import lombok.Setter;

@Service
public class BoardReviewServiceImpl implements BoardReviewService {
	
	@Setter(onMethod_ = @Autowired)
	private BoardReviewDao boardReviewDao;
	
	@Setter(onMethod_ = @Autowired)
	private AdminReplyReviewDao adminReplyReviewDao;
	
	// ����Խ��� �� ��� ����
	@Override
	public List<BoardReviewVO> boardReviewList(BoardReviewVO brvo) {
		List<BoardReviewVO> list = null;
		list = boardReviewDao.boardReviewList(brvo);
		return list;
	}

	// ��ü ���ڵ� �� ����
	@Override
	public int boardReviewListCnt(BoardReviewVO brvo) {
		return boardReviewDao.boardReviewListCnt(brvo);
	}

	// ����Խ��� �� �󼼺��� ����
	@Override
	public BoardReviewVO boardReviewDetail(BoardReviewVO brvo) {
		BoardReviewVO brDetail = null;
		
		brDetail = boardReviewDao.boardReviewDetail(brvo);
		if(brDetail != null) {
			brDetail.setR_content(brDetail.getR_content().toString().replaceAll("\n", "<br />"));
		}
		
		return brDetail;
	}

	// ����Խ��� �۾��� ����
	@Override
	public int boardReviewInsert(BoardReviewVO brvo) throws Exception {
		int result = 0;
		
		if(brvo.getFile().getSize() > 0) {
			String fileName = FileUploadUtil.fileUpload(brvo.getFile(), "review");
			brvo.setR_file(fileName);
			
			String thumbName = FileUploadUtil.makeThumbnail(fileName);
			brvo.setR_thumbnail(thumbName);
		}
		
		result = boardReviewDao.boardReviewInsert(brvo);
		return result;
	}

	// ����Խ��� �� ���� ����
	@Override
	public int boardReviewDelete(BoardReviewVO brvo) throws Exception {
		int result = 0;
		
		if(!brvo.getR_file().isEmpty()) {
			FileUploadUtil.fileDelete(brvo.getR_file());
		}
		
		if(brvo.getR_readcnt() > 0) {
			adminReplyReviewDao.replyAllDelete(brvo.getR_num());
		}
		
		result = boardReviewDao.boardReviewDelete(brvo);
		return result;
	}

	// ����Խ��� �� ���� ���� ���� ������ ����
	@Override
	public BoardReviewVO reviewUpdateForm(BoardReviewVO brvo) {
		BoardReviewVO updateData = null;
		updateData = boardReviewDao.boardReviewDetail(brvo);
		return updateData;
	}

	// �����Խ��� �� ���� ����
	@Override
	public int boardReviewUpdate(BoardReviewVO brvo) throws Exception {
		int result = 0;
		
		if(!brvo.getFile().isEmpty()) {
			if(!brvo.getR_file().isEmpty()) {
				FileUploadUtil.fileDelete(brvo.getR_file());
			}
			
			String fileName = FileUploadUtil.fileUpload(brvo.getFile(), "review");
			brvo.setR_file(fileName);
		}
		result = boardReviewDao.boardReviewUpdate(brvo);
		return result;
	}

	

}
