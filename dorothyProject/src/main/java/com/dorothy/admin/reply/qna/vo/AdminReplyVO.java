package com.dorothy.admin.reply.qna.vo;

import lombok.Data;

@Data
public class AdminReplyVO {
	private int rq_num = 0;			// �Խ��� ��� ��ȣ
	private int q_num = 0;			// �Խ��� �� ��ȣ
	private String ad_id = "";		// �Խ��� ��� ������ ���̵�
	private String rq_content = ""; // �Խ��� ��� ����
	private String rq_date = "";	// �Խ��� ��� �ۼ���
}



