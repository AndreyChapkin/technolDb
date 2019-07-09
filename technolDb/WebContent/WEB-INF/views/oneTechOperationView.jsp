<%@ page language="java" contentType="text/html; charset=cp1251"
 pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<head>
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
  </style>
</head>
<body>
 
  <p><b>��������������� ��������</b></p>
 
    <table border="1" cellpadding="5" cellspacing="1" class="oper_table">
       <col span="13" class="oper_col">
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
          <tr align="center">
             <td>${oper_i}</td>
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
          </tr>
    </table>  
</body>  