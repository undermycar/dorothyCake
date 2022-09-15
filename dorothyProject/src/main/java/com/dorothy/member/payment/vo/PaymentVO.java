package com.dorothy.member.payment.vo;

import com.dorothy.common.vo.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PaymentVO extends CommonVO {
	private String p_merchant_uid;		  // �ֹ���ȣ
	private String m_id;				  // �ֹ��ھ��̵�
	private String p_name;				  // �ֹ�����
	private int p_price;				  // ���� �ݾ�
	private String p_pg = "nice";		  // ������
	private String p_pay_method = "card"; // �������
	private String p_state;				  // �������� 
	private String p_date;				  // ������¥
}
