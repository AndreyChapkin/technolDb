<%@ page language="java" contentType="text/html; charset=cp1251"
 pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Список замеч.</title>
     <style>
	.rem_table{
    	border-collapse: collapse;
   		frame:void;
  	 }
	.rem_col{
   		border: 2px double #008; 
   		padding: 3px; 
  	 }  
	.rem_tr {
    padding: 2px; 
    border: 2px solid #008; 
    color: #008; 
  	}
  	.rem {
    display: inline-block; 
    padding: 1px 5px; 
    text-decoration: none; 
    cursor: pointer; 
    background: #FCC9CC; 
    border-radius: 7px; 
    border: 1px solid #008; 
    font: 11px; 
    color: #008; 
   }
  </style>
 </head>
 <body>
 
 <jsp:include page="oneProductView.jsp"></jsp:include>
 
    <h3>Список замечаний</h3>
    <%
    int rem_i=0;
    %>
    <table border="1" cellpadding="5" cellspacing="1" class="rem_table">
       <col span="8" class="rem_col">
       <tr bgcolor="#FCC9CC" class="rem_tr">
          <th>  </th>
          <th>Дата</th>
          <th>Время</th>
          <th>Составил</th>
          <th>Должность</th>
          <th>Наимен. предпр-я</th>
          <th>Комментарий</th>
          <th>Файл замечания</th>
       </tr>
       <c:forEach items="${remarkList}" var="remark" >
          <tr align="center" <%if ((rem_i+1)%2==0){%>bgcolor="#E2EFF5"<%}%>>
             <td>
             <%
             rem_i++;
             request.setAttribute("rem_i",rem_i);
             %> ${rem_i}
             </td>
             <td>${remark.remDate}</td>
             <td>${remark.remTime}</td>
             <td>${remark.writer}</td>
             <td>${remark.post}</td>
             <td>${remark.remCompany}</td>
             <td align="left" style="color: red;">${remark.comment}</td>
             <td>
                <a href="showTextFile?remId=${remark.remId}">Файл</a>
             </td>
             <td>
                <a href="editRemark?prodId=${remark.prodId}&prod_i=${prod_i}
                &remId=${remark.remId}&rem_i=${rem_i}" class="rem">Ред.</a>
             </td>
             <td>
                <a href="deleteRemark?prodId=${remark.prodId}&prod_i=${prod_i}
                &remId=${remark.remId}&rem_i=${rem_i}" class="rem">Удалить</a>
             </td>
          </tr>
       </c:forEach>
    </table>   
 	<p>
    <a href="createRemark?prodId=${product.id}&prod_i=${prod_i}" class="rem">Новое замечание</a>
    <h3>     </h3>
    <a href="${pageContext.request.contextPath}/">Назад</a>
 
 </body>
</html>