<%@ page language="java" contentType="text/html; charset=cp1251"
 pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>�������� �������</title>
 </head>
 <body>
 
 <jsp:include page="oneProductView.jsp"></jsp:include>
 
   <p style="color: red;"> �� ������������� ������ ������� �� ���� ������
    ������ �� ���� ������� � �� ��������� � ���?  </p>
    
    <form method="POST" action="${pageContext.request.contextPath}/deleteProduct">
    <input type="hidden" name="id" value="${product.id}" />
    
    <table border="0" >
       <tr>
       		<td colspan="2">                   
                   <input type="submit" value="�������" /> &nbsp;
                   <a href="productList">������</a>
               </td>
          </tr>
    </table> 
     </form>  
 </body>
</html>