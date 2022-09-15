package com.dorothy.member.board.free.vo;

import org.springframework.web.multipart.MultipartFile;

import com.dorothy.common.vo.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BoardFreeVO extends CommonVO {
	private int f_num = 0;		// �����Խ��� �� ��ȣ
	private String m_id = "";	// �����Խ��� �� �ۼ��� ���̵�
	private String f_title = ""; // �����Խ��� �� ����
	private String f_content = ""; // �����Խ��� �� ����
	private String f_date = "";  // �����Խ��� �� �ۼ���
	private int f_readcnt = 0;  // �����Խ��� �� ��ȸ��
	private int f_replycnt = 0; // �����Խ��� ���� ��� ����
	
	private MultipartFile file; // ���� ���ε带 ���� �ʵ�
	private String f_file="";  // ������ ������ �̹��� ���� ��
}
