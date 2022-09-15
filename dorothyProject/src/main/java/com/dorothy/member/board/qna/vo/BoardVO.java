package com.dorothy.member.board.qna.vo;

import org.springframework.web.multipart.MultipartFile;

import com.dorothy.common.vo.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/* equals�� hashCode �޼ҵ� �ڵ� ���� �� �θ� Ŭ������ �ʵ���� �������� �� ������ ���ؼ� ���� ��
 * callSuper = true�� �����ϸ� �θ� Ŭ���� �ʵ� ���鵵 �������� üũ�ϸ�,
 * callSuper = false�� ����(�⺻��)�ϸ� �ڽ� Ŭ������ �ʵ� ���鸸 ����Ѵ�. */
@Data
@EqualsAndHashCode(callSuper = false)
public class BoardVO extends CommonVO {
	private int q_num = 0;
	private String m_id = "";  // �г��� ��ü?
	private String q_title = "";
	private String q_content = "";
	private String q_date = "";
	private String q_category = "";
	private String q_status = "";
	/* private String q_pwd = ""; */
	private int q_readcnt = 0;
	private int q_replycnt = 0; // qna ���� ��� ����
	 
	private MultipartFile file; // ���� ���ε带 ���� �ʵ�
	//private String q_thumb = ""; // ���� ������ ������ ����� �̹��� ���ϸ�
	private String q_file = ""; // ���� ������ ������ ���ϸ�
}
