<%@ page language="java" contentType="text/html; charset=cp1251"
 pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Удаление КД</title>
     <style>
	.des_table{
    	border-collapse: collapse;
   		frame:void;
  	 }
	.des_col{
   		border: 2px double #008; 
   		padding: 3px; 
  	 }  
	.des_tr {
    padding: 2px; 
    border: 2px solid #008; 
    color: #008; 
  	}
  </style>
 </head>
 <body>
 
 <h3>Конструкторская документация</h3>
 
    <table border="1" cellpadding="5" cellspacing="1" class="des_table">
        <col span="8" class="des_col">
        <tr bgcolor="#FDFCCE" class="des_tr">
          <th>  </th>
          <th>Вид документа</th>
          <th>Обознач. документа</th>
          <th>Документ</th>
          <th>Разработал</th>
          <th>Проверил</th>
          <th>Утвердил</th>
          <th>Наимен. предпр-я</th>
       </tr>
       <tr align="center">
             <td> ${des_i}</td>
             <td>${designDocument.docType}</td>
             <td>${designDocument.docSign}</td>
             <td>
                <a href="showDocument?desId=${designDocument.desId}">Документ</a>
             </td>
             <td>${designDocument.docDeveloper}</td>
             <td>${designDocument.docSupervisor}</td>
             <td>${designDocument.docApprover}</td>
             <td>${designDocument.companyName}</td>
          </tr>
    </table>  
 
   <p style="color: red;"> Вы действительно хотите удалить из базы данных запись об этой
   конструкторской документации и всё связанное с ней?  </p>
    
    <form method="POST" action="${pageContext.request.contextPath}/deleteDesignDocument">
    <input type="hidden" name="prodId" value="${prodId}" />
    <input type="hidden" name="prod_i" value="${prod_i}" />
    <input type="hidden" name="desId" value="${designDocument.desId}" />
    
    <table border="0">
       <tr>
       		<td colspan="2">                   
                   <input type="submit" value="Удалить" /> &nbsp;
                   <a href="designDocumentList?prodId=${prodId}&prod_i=${prod_i}">Отмена</a>
               </td>
          </tr>
    </table> 
     </form>  
 </body>
</html>