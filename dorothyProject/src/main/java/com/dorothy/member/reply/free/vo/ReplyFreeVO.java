package com.dorothy.member.reply.free.vo;

import lombok.Data;

@Data
public class ReplyFreeVO {
	private int rf_num = 0;			// �����Խ��� ��� ��ȣ
	private int f_num = 0;			// �ڿ�Խ��� �� ��ȣ
	private String m_id = "";		// �����Խ��� ��� �ۼ��� ���̵�
	private String rf_content = ""; // �����Խ��� ��� ����
	private String rf_date = "";	// �����Խ��� ��� �ۼ���
}
