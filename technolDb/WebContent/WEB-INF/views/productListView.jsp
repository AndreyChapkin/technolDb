<%@ page language="java" contentType="text/html; charset=cp1251"
 pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Список изделий</title>
    <style>
   table { 
    border-collapse: collapse;
    frame:void;
   }
   TD {
    padding: 5px; 
    border: 1px solid #008; 
   }
   TH {
    padding: 2px; 
    border: 2px solid #008; 
    color: #008; 
   }
   col{
   	border: 2px double #008; 
    padding: 3px; 
   }
   .design {
    display: inline-block; 
    padding: 1px 5px; 
    text-decoration: none; 
    cursor: pointer;
    background: #AFEC7D; 
    border-radius: 7px; 
    border: 1px solid #008; 
    font: 11px; 
    color: #008;
   }
  </style>
 </head>
 <body>
    <h3>Список изделий</h3>
 	<%
 	int prod_i=0;
 	%>
    <table >
	      <col span="7">
        <tr bgcolor="#C6F09F">
          <th></th>
          <th>Название</th>
          <th>Обозначение</th>
          <th>Название материала</th>
          <th>Марка материала</th>
          <th>Масса</th>
          <th>Объём партии</th>
       </tr> 
       <c:forEach items="${productList}" var="product" >
          <tr align="center" <%if ((prod_i+1)%2==0){%>bgcolor="#E2EFF5"<%}%>> 
             <td><%
             prod_i++;
             request.setAttribute("prod_i",prod_i);
             %> ${prod_i}</td>
             <td>${product.name}</td>
             <td>${product.signName}</td>
             <td>${product.material}</td>
             <td>${product.materialBrand}</td>
             <td>${product.weight}</td>
             <td>${product.batchSize}</td>
             <td bgcolor="#FDFCCE">
                <a href="designDocumentList?prodId=${product.id}&prod_i=${prod_i}" color="red">Констр. док.</a>
             </td>
             <td bgcolor="#EFCDFE">
                <a href="techProcessList?prodId=${product.id}&prod_i=${prod_i}">Тех. процесс</a>
             </td>
             <td bgcolor="#FCC9CC">
                <a href="remarkList?prodId=${product.id}&prod_i=${prod_i}">Замечания</a>
             </td>
             <td>
                <a href="editProduct?id=${product.id}" class="design">Ред.</a>
             </td>
             <td>
                <a href="deleteProduct?id=${product.id}&prod_i=${prod_i}" class="design">Удалить</a>
             </td>
          </tr>
       </c:forEach> 
    </table> 
 	<p>
    <a href="createProduct" class="design" >Новое изделие</a>
 
 </body>
</html>