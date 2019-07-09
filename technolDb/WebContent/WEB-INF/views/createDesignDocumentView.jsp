<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Новая КД</title>
   </head>
   <body>     
      <h3>Добавить новую конструкторскую документацию</h3>
       
      <form method="POST" action="${pageContext.request.contextPath}/createDesignDocument" enctype="multipart/form-data">
         <input type="hidden" name="prodId" value="${prodId}" />
         <input type="hidden" name="prod_i" value="${prod_i}" />
         
         <table border="0">
            <tr>
               <td>Вид документа</td>
               <td><input type="text" name="docType"  /></td>
            </tr>
            <tr>
               <td>Обозначение документа</td>
               <td><input type="text" name="docSign" /></td>
            </tr>
            <tr>
            <td>Файл документа</td>
       	    <td><input type="file" name="document" /></td>
        	<tr />
            <tr>
               <td>Разработал</td>
               <td><input type="text" name="docDeveloper" /></td>
            </tr>
            <tr>
               <td>Проверил</td>
               <td><input type="text" name="docSupervisor" /></td>
            </tr>
           <tr>
               <td>Утвердил</td>
               <td><input type="text" name="docApprover" /></td>
            </tr>
            <tr>
               <td>Наименование предприятия</td>
               <td><input type="text" name="companyName" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Добавить" /> &nbsp;
                   <a href="designDocumentList?prodId=${prodId}&prod_i=${prod_i}">Назад</a>
               </td>
            </tr>
         </table>
      </form>
   </body>
</html>