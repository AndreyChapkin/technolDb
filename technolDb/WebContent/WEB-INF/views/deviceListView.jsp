<%@ page language="java" contentType="text/html; charset=cp1251"
 pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Список приспособ.</title>
    <style>
	.dev_table{
    	border-collapse: collapse; 
   		frame:void;
  	 }
	.dev_col{
   		border: 2px double #008; 
   		padding: 3px;
  	 }  
	.dev_tr {
    padding: 2px; 
    border: 2px solid #008; 
    color: #008; 
  	}
  	.dev {
    display: inline-block; 
    padding: 1px 5px; 
    text-decoration: none; 
    cursor: pointer; 
    background: #C9FEEA; 
    border-radius: 7px; 
    border: 1px solid #008; 
    font: 11px; 
    color: #008; 
   }
  </style>
 </head>
 <body>
 
 <jsp:include page="oneProductView.jsp"></jsp:include>
 <jsp:include page="oneTechProcessView.jsp"></jsp:include>
 <jsp:include page="oneTechOperationView.jsp"></jsp:include>
 
    <h3>Список приспособлений</h3>
    <%
    int dev_i=0;
    %>
    <table border="1" cellpadding="5" cellspacing="1" class="dev_table">
       <col span="3" class="dev_col">
       <tr bgcolor="#C9FEEA" class="dev_tr">
          <th>  </th>
		  <th>Наим. приспособления</th>
          <th>Код приспособления</th>
       </tr>
       <c:forEach items="${deviceList}" var="device" >
          <tr align="center" <%if ((dev_i+1)%2==0){%>bgcolor="#E2EFF5"<%}%>>
             <td>
             <%
             dev_i++;
             request.setAttribute("dev_i",dev_i);
             %> ${dev_i}
             </td>
             <td>${device.devName}</td>
             <td>${device.devCode}</td>
             <td>
                <a href="editDevice?prodId=${product.id}&prod_i=${prod_i}&tprId=${techProcess.tprId}&tpr_i=${tpr_i}&operId=${techOperation.operId}&oper_i=${oper_i}&devId=${device.devId}&dev_i=${dev_i}" class="dev">Ред.</a>
             </td>
             <td>
                <a href="deleteDevice?prodId=${product.id}&prod_i=${prod_i}&tprId=${techProcess.tprId}&tpr_i=${tpr_i}&operId=${techOperation.operId}&oper_i=${oper_i}&devId=${device.devId}&dev_i=${dev_i}" class="dev">Удалить</a>
             </td>
          </tr>
       </c:forEach>
    </table>   
 	<p>
    <a href="createDevice?prodId=${product.id}&prod_i=${prod_i}&tprId=${techProcess.tprId}&tpr_i=${tpr_i}&operId=${techOperation.operId}&oper_i=${oper_i}" class="dev">Новое приспособление</a>
    <h3>     </h3>
    <a href="${pageContext.request.contextPath}/techOperationList?prodId=${product.id}&prod_i=${prod_i}&tprId=${techProcess.tprId}&tpr_i=${tpr_i}">Назад</a>
 </body>
</html>