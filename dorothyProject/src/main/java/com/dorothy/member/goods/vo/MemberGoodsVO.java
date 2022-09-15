package com.dorothy.member.goods.vo;

import org.springframework.web.multipart.MultipartFile;

import com.dorothy.common.vo.CommonVO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MemberGoodsVO extends CommonVO {
	private int g_code;				//��ǰ�ڵ�
	private String g_name;			//��ǰ�̸�
	private int g_type;				//��ǰ���� 0: �����̾�����ũ, 1: ���͸�����ũ
	private int g_price;			//��ǰ����
	private String g_file;			//÷������ - �̹���
	private int g_size;				//��ǰ������ 0: �̴�, 1: 1ȣ ���� + 10000��
	private int g_recommend;		//��õ�±� 0 : ����, 1 : ����
	private int g_new;				//�Ż�ǰ�±� 0 : ����, 1 : ����
	private int g_custom;			//Ŀ�������� 0 : �⼺ǰ, 1 : Ŀ����
	
	private MultipartFile file;
}
