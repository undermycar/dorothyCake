<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jspf" %>
		
		<style type="text/css">
			textarea { resize: none; }
		</style>
		
		<script type="text/javascript">
			$(function(){
				/* 수정 버튼 클릭 시 글 수정 */
				$("#noticeUpdateBtn").click(function(){
					// 입력값 유효성 체크
					if(!chkData("#n_title", "제목을")) return;
					else if(!chkData("#n_content", "내용을")) return;
					else {
						if($("#file").val() != ""){
							if(!chkFile("#file")) return;
						}
						
						$("#noticeUpdate").attr({
							"method":"post",
							"enctype":"multipart/form-data",
							"action":"/admin/board/notice/noticeUpdate"
						});
						
						$("#noticeUpdate").submit();
					}
				});
				
// 				$("#freeUpdateBtn").click(function(){
// 					$.ajax({
// 						url: "/board/free/boardFreeUpdate",
// 						type: "post",
// 						enctype: "multipart/form-data",
// 						data: $("#freeUpdate").serialize(),
// 						dataType: "text",
// 						beforeSend: function(){
// 							if(!chkData("#f_title", "제목을")) return;
// 							else if(!chkData("#f_content", "내용을")) return;
// 		 					else {
// 		 						if($("#file").val() != ""){
// 		 							if(!chkFile("#file")) return;
// 		 						}
// 		 					}
// 						},
// 						error: function(){
// 							alert("문제가 발생했습니다. 잠시 후에 다시 시도해 주세요.");
// 						},
// 						success: function(resultData){
// 							alert("수정이 완료되었습니다.");
// 							location.href="/board/free/boardFreeDetail";
// 						}
// 					});
// 				});
				
				/* 취소 버튼 클릭 시 폼 리셋 */
				$("#noticeCancelBtn").click(function(){
					$("#noticeUpdate").each(function(){
						this.reset();
					});
				});
				
				/* 목록 버튼 클릭 시 글 목록으로 이동*/
				$("#boardNoticeListBtn").click(function(){
					if(confirm("목록으로 이동하시겠습니까?")){
						location.href="/admin/board/notice/boardNoticeList";
					}
				});
			}); // $ 함수 종료
		</script>
	</head>
	<body>
		<div class="contentContainer container">
			<div class="contentTB text-center">
				<form id="noticeUpdate" name="noticeUpdate" class="form-horizontal">
					<input type="hidden" id="n_num" name="n_num" value="${updateData.n_num}" />
					<input type="hidden" id="f_file" name="f_file" value="${updateData.n_file}" />
					
					<table class="table table-bordered">
						<tbody>
							<tr>
								<td class="col-md=3">글번호</td>
								<td class="text-left">${updateData.n_num}</td>
								<td class="col-md=3">작성일</td>
								<td class="text-left">${updateData.n_date}</td>
							</tr>
							<tr>	
								<td>작성자</td>
								<td colspan="3" class="text-left">${updateData.ad_id}</td>
							</tr>
							<tr>
								<td>제목</td>
								<td colspan="3" class="text-left">
									<input type="text" id="n_title" name="n_title" value="${updateData.n_title}" class="form-control" />
								</td>
							</tr>		
							<tr>
								<td>내용</td>
								<td colspan="3" class="text-left">
									<textarea rows="8" id="n_content" name="n_content" class="form-control">${updateData.n_content}</textarea>
								</td>
							</tr>
							<tr>
								<td>이미지파일첨부</td>
								<td colspan="3" class="text-left"><input type="file" id="file" name="file" /></td>
							</tr>
						</tbody>
					</table>
					<div class="text-right">
						<input type="button" value="수정" id="noticeUpdateBtn" class="btn btn-success" />
						<input type="button" value="취소" id="noticeCancelBtn" class="btn btn-success" />
						<input type="button" value="목록" id="boardNoticeListBtn" class="btn btn-success" />
					</div>
				</form>
			</div>
		</div>
	</body>
</html>