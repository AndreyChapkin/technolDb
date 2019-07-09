<%@ page language="java" contentType="text/html; charset=cp1251"
 pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>������ ���. ��������</title>
    <style>
	.oper_table{
    	border-collapse: collapse; 
   		frame:void;
  	 }
	.oper_col{
   		border: 2px double #008; 
   		padding: 3px; 
  	 }  
	.oper_tr {
    padding: 2px;
    border: 2px solid #008; 
    color: #008; 
  	}
  	.oper {
    display: inline-block; 
    padding: 1px 5px; 
    text-decoration: none;
    cursor: pointer; 
    background: #BDF5F9; 
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
 
    <h3>������ ��������������� ��������</h3>
    <%
    int oper_i=0;
    %>
    <table border="1" cellpadding="5" cellspacing="1" class="oper_table">
       <col span="12" class="oper_col">
       <tr bgcolor="#BDF5F9" class="oper_tr">
          <th>  </th>
          <th>�������� ��������</th>
          <th>����� ����</th>
          <th>����� �������</th>
          <th>����� ��������</th>
          <th>������������</th>
          <th>����������</th>
          <th>���-�� �����. �������</th>
          <th>�<sub>�</sub></th>
          <th>�<sub>�</sub></th>
          <th>�<sub>��</sub></th>
          <th>�����</th>
       </tr>
       <c:forEach items="${techOperationList}" var="techOperation" >
          <tr align="center" <%if ((oper_i+1)%2==0){%>bgcolor="#E2EFF5"<%}%>>
             <td>
             <%
             oper_i++;
             request.setAttribute("oper_i",oper_i);
             %> ${oper_i}
             </td>
             <td>${techOperation.operName}</td>
             <td>${techOperation.workshopNum}</td>
             <td>${techOperation.areaNum}</td>
             <td>${techOperation.operNum}</td>
             <td>${techOperation.equipment}</td>
             <td>${techOperation.cooling}</td>
             <td>${techOperation.numOfDetails}</td>
             <td>${techOperation.basicTime}</td>
             <td>${techOperation.subsidTime}</td>
             <td>${techOperation.pieceTime}</td>
             <td>
                <a href="showSketch?operId=${techOperation.operId}">�����</a>
             </td>
             <td bgcolor="#FED8C9">
                <a href="techTransitList?prodId=${product.id}&prod_i=${prod_i}&tprId=${techProcess.tprId}&tpr_i=${tpr_i}&operId=${techOperation.operId}&oper_i=${oper_i}">��������</a>
             </td>
             <td bgcolor="#F0FE9F">
                <a href="techControlList?prodId=${product.id}&prod_i=${prod_i}&tprId=${techProcess.tprId}&tpr_i=${tpr_i}&operId=${techOperation.operId}&oper_i=${oper_i}">���. ��������</a>
             </td>
             <td bgcolor="#C9FEEA">
                <a href="deviceList?prodId=${product.id}&prod_i=${prod_i}&tprId=${techProcess.tprId}&tpr_i=${tpr_i}&operId=${techOperation.operId}&oper_i=${oper_i}">���������.</a>
             </td>
             <td>
                <a href="editTechOperation?prodId=${techProcess.prodId}&prod_i=${prod_i}&tprId=${techOperation.tprId}&tpr_i=${tpr_i}&operId=${techOperation.operId}&oper_i=${oper_i}" class="oper">���.</a>
             </td>
             <td>
                <a href="deleteTechOperation?operId=${techOperation.operId}&oper_i=${oper_i}&prodId=${techProcess.prodId}
                &prod_i=${prod_i}&tprId=${techOperation.tprId}&tpr_i=${tpr_i}" class="oper">�������</a>
             </td>
          </tr>
       </c:forEach>
    </table>   
 	<p>
    <a href="createTechOperation?prodId=${product.id}&prod_i=${prod_i}&tprId=${techProcess.tprId}&tpr_i=${tpr_i}" class="oper">����� ��������������� ��������</a>
    <h3>     </h3>
    <a href="${pageContext.request.contextPath}/techProcessList?prodId=${product.id}&prod_i=${prod_i}">�����</a>
 
 </body>
</html>