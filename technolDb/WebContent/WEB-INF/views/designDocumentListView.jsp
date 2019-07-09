<%@ page language="java" contentType="text/html; charset=cp1251"
 pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Список КД</title>
    <style>
	.des_table{
    	border-collapse: collapse;
   		frame:void;
  	 }
	.des_col{
   		border: 2px double #008; 
   		padding: 3px;
  	 }  
	.des_tr {
    padding: 2px; 
    border: 2px solid #008; 
    color: #008; 
  	}
  	.des {
    display: inline-block; 
    padding: 1px 5px;
    text-decoration: none; 
    cursor: pointer; 
    background: #FDFCCE; 
    border-radius: 7px; 
    border: 1px solid #008; 
    font: 11px; 
    color: #008; 
   }
  </style>
 </head>
 <body>
 
 <jsp:include page="oneProductView.jsp"></jsp:include>
 
    <h3>Список конструкторской документации</h3>
    <%
    int des_i=0;
    %>
    <table border="1" cellpadding="5" cellspacing="1"  class="des_table">
       <col span="8" class="des_col">
       <tr bgcolor="#FDFCCE" class="des_tr">
          <th>  </th>
          <th>Вид документа</th>
          <th>Обознач. документа</th>
          <th>Документ</th>
          <th>Разработал</th>
          <th>Проверил</th>
          <th>Утвердил</th>
          <th>Наимен. предпр-я</th>
       </tr>
       <c:forEach items="${designDocumentList}" var="designDocument" >
          <tr align="center" <%if ((des_i+1)%2==0){%>bgcolor="#E2EFF5"<%}%>>
             <td>
             <%
             des_i++;
             request.setAttribute("des_i",des_i);
             %> ${des_i}
             </td>
             <td>${designDocument.docType}</td>
             <td>${designDocument.docSign}</td>
             <td>
                <a href="showDocument?desId=${designDocument.desId}">Документ</a>
             </td>
             <td>${designDocument.docDeveloper}</td>
             <td>${designDocument.docSupervisor}</td>
             <td>${designDocument.docApprover}</td>
             <td>${designDocument.companyName}</td>
             <td>
                <a href="editDesignDocument?prodId=${designDocument.prodId}&prod_i=${prod_i}
                &desId=${designDocument.desId}&des_i=${des_i}" class="des">Ред.</a>
             </td>
             <td>
                <a href="deleteDesignDocument?prodId=${designDocument.prodId}&prod_i=${prod_i}
                &desId=${designDocument.desId}&des_i=${des_i}" class="des">Удалить</a>
             </td>
          </tr>
       </c:forEach>
    </table>   
 	<p>
    <a href="createDesignDocument?prodId=${product.id}&prod_i=${prod_i}" class="des">Новый конструкторский документ</a>
    <h3>     </h3>
    <a href="${pageContext.request.contextPath}/">Назад</a>
 
 </body>
</html>