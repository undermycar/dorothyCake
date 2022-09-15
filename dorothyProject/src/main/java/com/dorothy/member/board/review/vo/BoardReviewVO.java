package com.dorothy.member.board.review.vo;

import org.springframework.web.multipart.MultipartFile;

import com.dorothy.common.vo.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BoardReviewVO extends CommonVO {
	private int r_num = 0; // ����Խ��� �۹�ȣ
	private String m_id = "";	// ����Խ��� �� �ۼ��� ���̵�
	private String r_title = ""; // ����Խ��� ������
	private String r_content = ""; // ����Խ��� �۳���
	private String r_date = ""; // ����Խ��� �� �ۼ���
	private int r_readcnt = 0; // ����Խ��� �� ��ȸ��
	private int r_replycnt = 0; // ����Խ��� ���� ��� ����
	
	private MultipartFile file; // ���� ���ε带 ���� �ʵ�
	private String r_file="";  // ������ ������ �̹��� ���� ��
	private String r_thumbnail = ""; // ���� ������ ������ ����� �̹��� ���ϸ�
	

}
