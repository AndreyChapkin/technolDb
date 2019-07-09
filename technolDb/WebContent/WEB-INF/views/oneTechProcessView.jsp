<%@ page language="java" contentType="text/html; charset=cp1251"
 pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<head>
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
  </style>
</head>
<body>
  <p><b>Технологический процесс</b></p>
 
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
          <tr align="center">
             <td>${tpr_i}</td>
             <td>${techProcess.company}</td>
             <td>${techProcess.developer}</td>
             <td>${techProcess.supervisor}</td>
             <td>${techProcess.approver}</td>
             <td>${techProcess.workpieceCode}</td>
             <td>${techProcess.workpieceSort}</td>
             <td>${techProcess.workpieceProfile}</td>
             <td>${techProcess.workpieceSizes}</td>
             <td>${techProcess.workpieceWeight}</td>
          </tr>
    </table>   
</body>  