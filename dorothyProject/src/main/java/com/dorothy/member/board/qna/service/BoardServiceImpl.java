package com.dorothy.member.board.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dorothy.admin.reply.qna.dao.AdminReplyDao;
import com.dorothy.common.file.FileUploadUtil;
import com.dorothy.member.board.qna.dao.BoardDao;
import com.dorothy.member.board.qna.vo.BoardVO;
import com.dorothy.member.login.vo.MemberVO;

import lombok.Setter;



@Service
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_ = @Autowired)
	private BoardDao boardDao;
	
	@Setter(onMethod_ = @Autowired)
	private AdminReplyDao replyDao;
	
	// �� ��� ����
	@Override
	public List<BoardVO> boardList(BoardVO bvo) {
		List<BoardVO> list = null;
		list = boardDao.boardList(bvo);
		return list;
	}
	
	@Override
	public List<BoardVO> boardMyList(BoardVO bvo) {
		List<BoardVO> list = null;
		/* list = boardDao.boardMyList(bvo); */
		return list;
	}
	
	// ��ü ���ڵ� �� ����
	@Override
	public int boardListCnt(BoardVO bvo) {
		return boardDao.boardListCnt(bvo);
	}
	
	

	// �� �Է� ����
	@Override
	public int boardInsert(BoardVO bvo) throws Exception {
		int result = 0;
		
		/* ���ܸ� �߻���ų �ڵ� �ۼ�
		bvo.setB_num(0);
		if(bvo.getB_num() == 0) {
			throw new IllegalArgumentException("0�� ���� ����� �� �����ϴ�.");
		} */
		
		if(bvo.getFile().getSize() > 0) {
			String fileName = FileUploadUtil.fileUpload(bvo.getFile(), "qna");
			bvo.setQ_file(fileName);
			
			
		}
		result = boardDao.boardInsert(bvo);
		return result;
	}
	
	// �� �� ����
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

	// ��й�ȣ Ȯ�� ����
	@Override
	public int pwdConfirm(BoardVO bvo) {
		int result = 0;
		result = boardDao.pwdConfirm(bvo);
		return result;
	}
	
	// �� ���� ����
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
	// �����Խ��� �� ���� ���� ���� ������ ����
	@Override
	public BoardVO updateForm(BoardVO bvo) {
		BoardVO updateData = null;
		updateData = boardDao.boardDetail(bvo);
		return updateData;
	}
	
	// �� ���� ����
	@Override
	public int boardUpdate(BoardVO bvo) throws Exception {
		int result = 0;
		
		if(!bvo.getFile().isEmpty()) { // ���Ӱ� ���ε��� ������ �����ϸ�
			if(!bvo.getQ_file().isEmpty()) { // ���� ������ �����ϸ�
				FileUploadUtil.fileDelete(bvo.getQ_file());
				//FileUploadUtil.fileDelete(bvo.getQ_thumb());
			}
			
			String fileName = FileUploadUtil.fileUpload(bvo.getFile(), "qna");
			bvo.setQ_file(fileName);
			
			/*
			 * String thumbName = FileUploadUtil.makeThumbnail(fileName);
			 * bvo.setQ_thumb(thumbName);
			 */
		}
		result = boardDao.boardUpdate(bvo);
		return result;
	}

	@Override
	public List<BoardVO> myList(MemberVO mvo) {
		List<BoardVO> list = null;
		list = boardDao.myList(mvo);
		return list;
	}

	/*
	 * @Override public int replyCnt(int q_num) { // TODO Auto-generated method stub
	 * return 0; }
	 */

	/*
	 * �ش� �Խù��� ��� ���� ���� Ȯ�� ����� �����ϸ� ��ۼ��� ��ȯ�ϰ� �������� ������ 0�� ��ȯ
	 */
	/*
	 * @Override public int replyCnt(int q_num) { int result = 0; result =
	 * replyDao.replycnt(q_num); return result; }
	 */
/*	@Override
	public List<BoardVO> myBoardList(BoardVO bvo) {
		List<BoardVO> list = null;
		list = boardDao.boardList(bvo);
		return list;
	*/
/* } */
	
}
