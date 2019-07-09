<%@ page language="java" contentType="text/html; charset=cp1251"
 pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Список тех. процессов</title>
    <style>
	.tpr_table{
    	border-collapse: collapse; 
   		frame:void;
  	 }
	.tpr_col{
   		border: 2px double #008;
   		padding: 3px; 
  	 }  
	.tpr_tr {
    padding: 2px; 
    border: 2px solid #008; 
    color: #008; 
  	}
  	.tpr {
    display: inline-block; 
    padding: 1px 5px;
    text-decoration: none;
    cursor: pointer; 
    background: #EFCDFE; 
    border-radius: 7px; 
    border: 1px solid #008; 
    font: 11px; 
    color: #008;
   }
  </style>
 </head>
 <body>
 
 <jsp:include page="oneProductView.jsp"></jsp:include>
 
    <h3>Список технологических процессов</h3>
    <%
    int tpr_i=0;
    %>
    <table border="1" cellpadding="5" cellspacing="1" class="tpr_table">
        <col span="10" class="tpr_col">
       <tr bgcolor="#EFCDFE" class="tpr_tr">
          <th>  </th>
          <th>Предприятие</th>
          <th>Разработал</th>
          <th>Проверил</th>
          <th>Утвердил</th>
          <th>Код заготовки</th>
          <th>Вид заготовки</th>
          <th>Профиль заготовки</th>
          <th>Размеры заготовки</th>
          <th>Масса заготовки</th>
       </tr>
       <c:forEach items="${techProcessList}" var="techProcess" >
          <tr align="center" <%if ((tpr_i+1)%2==0){%>bgcolor="#E2EFF5"<%}%>>
             <td>
             <%
             tpr_i++;
             request.setAttribute("tpr_i",tpr_i);
             %> ${tpr_i}
             </td>
             <td>${techProcess.company}</td>
             <td>${techProcess.developer}</td>
             <td>${techProcess.supervisor}</td>
             <td>${techProcess.approver}</td>
             <td>${techProcess.workpieceCode}</td>
             <td>${techProcess.workpieceSort}</td>
             <td>${techProcess.workpieceProfile}</td>
             <td>${techProcess.workpieceSizes}</td>
             <td>${techProcess.workpieceWeight}</td>
             <td bgcolor="#BDF5F9">
                <a href="techOperationList?prodId=${techProcess.prodId}&prod_i=${prod_i}&tprId=${techProcess.tprId}&tpr_i=${tpr_i}">Операции</a>
             </td>
             <td>
                <a href="editTechProcess?prodId=${techProcess.prodId}&prod_i=${prod_i}
                &tprId=${techProcess.tprId}&tpr_i=${tpr_i}" class="tpr">Ред.</a>
             </td>
             <td>
                <a href="deleteTechProcess?prodId=${techProcess.prodId}&prod_i=${prod_i}
                &tprId=${techProcess.tprId}&tpr_i=${tpr_i}" class="tpr">Удалить</a>
             </td>
          </tr>
       </c:forEach>
    </table>   
 	<p>
    <a href="createTechProcess?prodId=${product.id}&prod_i=${prod_i}" class="tpr">Новый технологический процесс</a>
    <h3>     </h3>
    <a href="${pageContext.request.contextPath}/">Назад</a>
 
 </body>
</html>