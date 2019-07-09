<%@ page language="java" contentType="text/html; charset=cp1251"
 pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Список этапов контроля</title>
     <style>
	.step_table{
    	border-collapse: collapse; 
   		frame:void;
  	 }
	.step_col{
   		border: 2px double #008; 
   		padding: 3px; 
  	 }  
	.step_tr {
    padding: 2px; 
    border: 2px solid #008;
    color: #008; 
  	}
  	.step {
    display: inline-block; 
    padding: 1px 5px; 
    text-decoration: none; 
    cursor: pointer;
    background: #FED6F4; 
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
 <jsp:include page="devicesView.jsp"></jsp:include>
 <jsp:include page="oneTechControlView.jsp"></jsp:include>
 
    <h3>Список этапов контроля</h3>
    <%
    int step_i=0;
    %>
   <table border="1" cellpadding="5" cellspacing="1" class="step_table">
       <col span="6" class="step_col">
       <tr bgcolor="#FED6F4" class="step_tr">
          <th>  </th>
		  <th>Контр. параметр</th>
          <th>Наим. ср-в ТО</th>
          <th>Код ср-в ТО</th>
          <th>Т<sub>о</sub>/Т<sub>в</sub></th>
          <th>Объём и ПК</th>
       </tr>
       <c:forEach items="${controlStepList}" var="controlStep" >
          <tr align="center" <%if ((step_i+1)%2==0){%>bgcolor="#E2EFF5"<%}%>>
             <td>
             <%
             step_i++;
             request.setAttribute("step_i",step_i);
             %> ${step_i}
             </td>
             <td>${controlStep.ctrParam}</td>
             <td>${controlStep.techEqName}</td>
             <td>${controlStep.techEqCode}</td>
             <td>${controlStep.baseSubTime}</td>
             <td>${controlStep.ctrValue}</td>
             <td>
                <a href="editControlStep?prodId=${product.id}&prod_i=${prod_i}&tprId=${techProcess.tprId}&tpr_i=${tpr_i}&operId=${techOperation.operId}&oper_i=${oper_i}&ctrId=${techControl.ctrId}&ctr_i=${ctr_i}&stepId=${controlStep.stepId}&step_i=${step_i}" class="step">Ред.</a>
             </td>
             <td>
                <a href="deleteControlStep?prodId=${product.id}&prod_i=${prod_i}&tprId=${techProcess.tprId}&tpr_i=${tpr_i}&operId=${techOperation.operId}&oper_i=${oper_i}&ctrId=${techControl.ctrId}&ctr_i=${ctr_i}&stepId=${controlStep.stepId}&step_i=${step_i}" class="step">Удалить</a>
             <a name="${step_i}"></a>
             </td>
          </tr>
       </c:forEach>
    </table>   
 	<p>
    <a href="createControlStep?prodId=${product.id}&prod_i=${prod_i}&tprId=${techProcess.tprId}&tpr_i=${tpr_i}&operId=${techOperation.operId}&oper_i=${oper_i}&ctrId=${techControl.ctrId}&ctr_i=${ctr_i}&step_i=${step_i+1}" class="step">Новый этап контроля</a>
    <h3>     </h3>
    <a href="${pageContext.request.contextPath}/techControlList?prodId=${product.id}&prod_i=${prod_i}&tprId=${techProcess.tprId}&tpr_i=${tpr_i}&operId=${techOperation.operId}&oper_i=${oper_i}">Назад</a>
 </body>
</html>