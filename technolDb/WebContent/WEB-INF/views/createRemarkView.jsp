<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Новое замеч.</title>
   </head>
   <body>     
      <h3>Добавить новое замечание</h3>
       
      <form method="POST" action="${pageContext.request.contextPath}/createRemark" enctype="multipart/form-data">
         <input type="hidden" name="prodId" value="${prodId}" />
         <input type="hidden" name="prod_i" value="${prod_i}" />
         
         <table border="0">
            <tr>
               <td>Составил</td>
               <td><input type="text" name="writer"  /></td>
            </tr>
            <tr>
               <td>Должность</td>
               <td><input type="text" name="post" /></td>
            </tr>
			<tr>
               <td>Наименование предприятия</td>
               <td><input type="text" name="remCompany" /></td>
            </tr>
            <tr>
               <td>Комментарий</td>
               <td><input type="text" name="comment" /></td>
            </tr>
            <tr>
            <td>Файл с замечанием</td>
       	    <td><input type="file" name="textFile" /></td>
        	</tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Добавить" /> &nbsp;
                   <a href="remarkList?prodId=${prodId}&prod_i=${prod_i}">Назад</a>
               </td>
            </tr>
         </table>
      </form>
   </body>
</html>