<%@ page language="java" contentType="text/html; charset=cp1251"
 pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>�������� �����.</title>
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
  </style>
 </head>
 <body>
 
 <h3>���������</h3>
 
    <table border="1" cellpadding="5" cellspacing="1" class="rem_table">
        <col span="8" class="rem_col">
        <tr bgcolor="#FCC9CC" class="rem_tr">
          <th>  </th>
          <th>����</th>
          <th>�����</th>
          <th>��������</th>
          <th>���������</th>
          <th>������. ������-�</th>
          <th>�����������</th>
          <th>���� ���������</th>
       </tr>
       <tr align="center">
             <td>${rem_i}</td>
             <td>${remark.remDate}</td>
             <td>${remark.remTime}</td>
             <td>${remark.writer}</td>
             <td>${remark.post}</td>
             <td>${remark.remCompany}</td>
             <td align="left" style="color: red;">${remark.comment}</td>
             <td>
                <a href="showTextFile?remId=${remark.remId}">����</a>
             </td>
          </tr>
    </table>  
 
   <p style="color: red;"> �� ������������� ������ ������� �� ���� ������ ������ �� ����
   ��������� � �� ��������� � ���?  </p>
    
    <form method="POST" action="${pageContext.request.contextPath}/deleteRemark">
    <input type="hidden" name="prodId" value="${prodId}" />
    <input type="hidden" name="prod_i" value="${prod_i}" />
    <input type="hidden" name="remId" value="${remark.remId}" />
    
    <table border="0" >
       <tr>
       		<td colspan="2">                   
                   <input type="submit" value="�������" /> &nbsp;
                   <a href="remarkList?prodId=${prodId}&prod_i=${prod_i}">������</a>
               </td>
          </tr>
    </table> 
     </form>  
 </body>
</html>