package com.dorothy.member.reply.qna.controller;

import java.util.List;


import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dorothy.admin.reply.qna.vo.AdminReplyVO;
import com.dorothy.member.reply.qna.service.ReplyService;


import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/***************************************************************************************
 * ���� : REST(Representational State Transfer)�� ���� 
 *      �ϳ��� URI�� �ϳ��� ������ ���ҽ��� ��ǥ�ϵ��� ����ȴٴ� �����̴�. 
 *      �� REST����� Ư�� URL�� �ݵ�� �׿� �����ϴ� ������ ��ü��� ���� �ǹ��ϴ� ����̴�. 
 *      /member/join => GET(ȸ������ȭ��) /member/join => POST
 *      ���� ��� 'board/125'�� �Խù� �߿��� 125���̶�� ������ �ǹ̸� �������� �����ϰ�, 
 *      �̿� ���� ó���� GET, POST ��İ� ���� �߰����� ������ ���ؼ� �����Ѵ�.
 *      http://localhost:8080/board/boardDetail?b_num=4
 *      http://localhost:8080/board/4	=> GET
 *      
 *      http://localhost:8080/board/boardDetail?b_num=4
 *      http://localhost:8080/board/4	=> DELETE
 *      
 *      �ְ�޴� �ڿ�(Resource)�� �̸��� �����ϰ� URI�� ����� HTTP �޼���(GET, POST, PUT, DELETE)��
 *      ���� �ش� �ڿ��� ���¸� �ְ� �޴� ���� �ǹ�.
 ***************************************************************************************/


/***************************************************************************************
 * ���� : @RestController (@Controller + @ResponesBody)
 * Controller�� REST ����� ó���ϱ� ���� ������ ���. (������ Ư���� JSP�� ���� �並 ����� 
 * ���� ���� ������ �ƴ� REST ����� ������ ó���� ���ؼ� ����ϴ�(������ ��ü�� ��ȯ) ������̼��̴�.
 * @ResponesBody: �Ϲ����� JSP�� ���� ��� ���޵Ǵ� �� �ƴ϶� ������ ��ü�� �����ϱ� ���� �뵵�̴�.
 * @PathVariable: URL ��ο� �ִ� ���� �Ķ���ͷ� �����Ϸ��� �� �� ����Ѵ�.
 * name=value&name1=value1
 * @RequestBody: JSON �����͸� ���ϴ� Ÿ������ ���ε� ó���Ѵ�. {name:value}
 ***************************************************************************************/

@RestController
@RequestMapping(value="/replies/qna")
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService replyservice;
	
	/***************************************************************************************
	 * ��� �۸�� �����ϱ�
	 * @return List<ReplyVO>
	 * ���� : @PathVariable�� URI�� ��ο��� ���ϴ� �����͸� �����ϴ� �뵵�� ������̼�.
	 * ���� ���� Ÿ���� xml�̳� json���� ��ȯ�� ���� produces �Ӽ��� ����Ѵ�.
	 * produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
	 * ���� ��û URL : http://localhost:8080/replies/all/�Խ��Ǳ۹�ȣ
	 * ���� ��û URL : http://localhost:8080/replies/replyList?b_num=�Խ��Ǳ۹�ȣ
	 * ResponseEntity�� �����ڰ� ���� ��� �����Ϳ� HTTP ���� �ڵ带 ���� ������ �� �ִ� Ŭ����.
	 **************************************************************************************
	@GetMapping(value = "/all/{b_num}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyVO>> replyList(@PathVariable("b_num") Integer b_num) {
		log.info("list ȣ�� ����");
		
		ResponseEntity<List<ReplyVO>> entity = null;
		entity = new ResponseEntity<>(replyservice.replyList(b_num), HttpStatus.OK);
		return entity;
	}*/
	
	@GetMapping(value = "/all/{q_num}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<AdminReplyVO> replyList(@PathVariable("q_num") Integer q_num) {
		log.info("list ȣ�� ����");
		
		List<AdminReplyVO> entity = null;
		entity = replyservice.replyList(q_num);
		return entity;
	}
	
		
	/***************************************************************************************
	 * ��� �۾��� �����ϱ�
	 * @return String
	 * ���� : @RequestBody
	 * 		consumes �Ӽ��� �̿��ϸ� ����ڰ� RequestBody�� ��� Ÿ���� ������ �� ������
	 * 		��û �� ����� �ݵ�� application/json�� �����ؾ� �Ѵ�.
	 * 		produces �Ӽ��� �߰��ϰ� dataType�� �����ϸ� �ش� ������ Ÿ�����θ�
	 * 		����ڿ��� �����ϰڴٴ� �ǹ̷� �ؼ��ϸ� �ȴ�.
	 * ���� ��û URL : http://localhost:8080/replies/replyInsert
	 **************************************************************************************
	@PostMapping(value = "/replyInsert", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> replyInsert(@RequestBody ReplyVO rvo){
		log.info("replyInsert ȣ�� ����");
		log.info("ReplyVO : " + rvo);
		int result = 0;
		
		result = replyservice.replyInsert(rvo);
		// result ���� 1�� ��� "SUCCESS" ���ڿ� 200�� �����ڵ带 �����ϰ�, 1�� �ƴ� ��� ������ �����ڵ�(500)�� ����.
		return (result == 1)? new ResponseEntity<String>("SUCCESS", HttpStatus.OK) :
							  new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
	
	@PostMapping(value = "/replyInsert", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public String replyInsert(@RequestBody AdminReplyVO rvo) {
		log.info("replyInsert ȣ�� ����");
		log.info("ReplyVO : " + rvo);
		
		int result = 0;
		result = replyservice.replyInsert(rvo);
		return (result == 1) ? "SUCCESS" : "FAILURE";
	}
	
	
	/***************************************************************************************
	 * ��� ���� �����ϱ�
	 * @return
	 * ���� : REST ��Ŀ��� DELETE �۾��� DELETE ����� �̿��ؼ� ó��.
	 * ���� ��û URL : http://localhost:8080/replies/��۹�ȣ
	 **************************************************************************************/
	/*@RequestMapping(value="/{r_num}", method= RequestMethod.DELETE, produces = {MediaType.TEXT_PLAIN_VALUE})
	@DeleteMapping(value = "/{r_num}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> replyDelete(@PathVariable("r_num") Integer r_num){
		log.info("replyDelete ȣ�� ����");
		log.info("r_num = " + r_num);
		
		int result = replyservice.replyDelete(r_num);
		return result == 1 ?
					new ResponseEntity<String>("SUCCESS", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
	
	@DeleteMapping(value = "/{rq_num}", produces = MediaType.TEXT_PLAIN_VALUE)
	public String replyDelete(@PathVariable("rq_num") int rq_num) {
		log.info("replyDelete ȣ�� ����");
		log.info("rq_num = " + rq_num);
		
		int result = replyservice.replyDelete(rq_num);
		return (result == 1) ? "SUCCESS" : "FAILURE";
	}
	
	
	/***************************************************************************************
	 * ��� ���� �����ϱ�
	 * @return
	 * ���� : REST ��Ŀ��� UPDATE �۾��� PUT, PATCH ����� �̿��ؼ� ó��.
	 * 		 ��ü �����͸� �����ϴ� ��쿡�� PUT�� �̟G�ϰ�,
	 * 		 �Ϻ��� �����͸� �����ϴ� ��쿡�� PATCH�� �̿�.
	 * ���� ��û URL : http://localhost:8080/replies/��۹�ȣ
	 **************************************************************************************/
	/*@RequestMapping(value="/{r_num}", method= {RequestMethod.PUT, RequestMethod.PATCH}, consumes="application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	@PutMapping(value="/{r_num}", consumes="application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> replyUpdate(@PathVariable("r_num") Integer r_num, @RequestBody ReplyVO rvo){
		log.info("replyUpdate ȣ�� ����");
		
		rvo.setR_num(r_num);
		int result = replyservice.replyUpdate(rvo);
		return result == 1 ?
					new ResponseEntity<String>("SUCCESS", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
	
	@PutMapping(value="/{rq_num}", consumes="application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public String replyUpdate(@PathVariable("rq_num") Integer rq_num, @RequestBody AdminReplyVO rvo) {
		log.info("replyUpdate ȣ�� ����");
		
		rvo.setRq_num(rq_num);
		int result = replyservice.replyUpdate(rvo);
		return (result == 1) ? "SUCCESS" : "FAILURE";
	}
}
