<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
   <table class="table table-hover">
    <c:forEach var="vo" items="${list }">
     <tr class="recipelists" data-no="${vo.no }" data-ino="${vo.ino }" data-mno="${vo.mno }"
      data-name="${vo.ingredetailname }"  data-unit="${vo.unit }" data-price="${vo.price }">
      <td class="text-left">${vo.ingredetailname })</td>
     </tr>
   </c:forEach>
  </table>
</body>
</html>