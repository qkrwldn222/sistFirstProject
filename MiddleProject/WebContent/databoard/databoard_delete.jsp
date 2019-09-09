<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">
.row {
   margin: 0px auto;
   width:900px;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#delBtn').click(function(){
		var pwd=$('#pwd').val();
		if(pwd.trim()=="")
		{
			$('#pwd').focus();
			return;
		}
		var no=$('#no').val();
		$.ajax({
			type:'post',
			url:'databoard_delete_ok.do',
			data:{no:no,pwd:pwd},
			success:function(response)
			{
				var i=response.trim();
				if(i==0)
				{
					alert("비밀번호가 틀립니다!!");
					$('#pwd').val("");
					$('#pwd').focus();
				}
				else
				{
					location.href="../databoard/databoard_list.do";
				}
			}
		});
	});
});
</script>
</head>
<body>
<section class="ftco-section ftco-degree-bg">
  <%-- 삭제하기  --%>
  <div class="container">
    <h2 class="text-center">삭제하기</h2>
    <div class="row">
	      <table class="table text-center">
	        <tr>
	          <th class="text-right" width=30%>비밀번호</th>
	          <th class="text-left" width=70%>
	            <input type=password name=pwd size=15 id="pwd">
	            <input type=hidden name=no value="${no }" id="no">
	          </th>
	        </tr>
	        <tr>
	          <th class="text-center" colspan="2">
	            <input type=button value="삭제" class="btn btn-sm btn-warning" id="delBtn">
	            <input type=button value="취소" class="btn btn-sm btn-danger"
	              onclick="javascript:history.back()"
	            >
	          </th>
	        </tr>
	      </table>
    </div>
  </div>
    </section>
</body>
</html>