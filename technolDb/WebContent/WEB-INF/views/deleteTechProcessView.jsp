<%@ page language="java" contentType="text/html; charset=cp1251"
 pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>�������� ���. ��������</title>
 </head>
 <body>
 
 <jsp:include page="oneTechProcessView.jsp"></jsp:include>
 
   <p style="color: red;"> �� ������������� ������ ������� �� ���� ������ ������ �� ���� ���������������
    �������� � �� ��������� � ���?  </p>
    
    <form method="POST" action="${pageContext.request.contextPath}/deleteTechProcess">
    <input type="hidden" name="prodId" value="${prodId}" />
    <input type="hidden" name="prod_i" value="${prod_i}" />
    <input type="hidden" name="tprId" value="${techProcess.tprId}" />
    
    <table border="0" >
       <tr>
       		<td colspan="2">                   
                   <input type="submit" value="�������" /> &nbsp;
                   <a href="techProcessList?prodId=${prodId}&prod_i=${prod_i}">������</a>
               </td>
          </tr>
    </table> 
     </form>  
 </body>
</html>