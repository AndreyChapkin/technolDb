<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Новый тех.процесс</title>
   </head>
   <body>     
      <h3>Добавить новый технологический процесс</h3>
       
      <form method="POST" action="${pageContext.request.contextPath}/createTechProcess">
         <input type="hidden" name="prodId" value="${prodId}" />
         <input type="hidden" name="prod_i" value="${prod_i}" />
         <table border="0">
            <tr>
               <td>Предприятие</td>
               <td><input type="text" name="company"  /></td>
            </tr>
            <tr>
               <td>Разработал</td>
               <td><input type="text" name="developer" /></td>
            </tr>
            <tr>
               <td>Проверил</td>
               <td><input type="text" name="supervisor" /></td>
            </tr>
            <tr>
               <td>Утвердил</td>
               <td><input type="text" name="approver" /></td>
            </tr>
            <tr>
               <td>Код заготовки</td>
               <td><input type="text" name="workpieceCode" /></td>
            </tr>
           <tr>
               <td>Вид заготовки</td>
               <td><input type="text" name="workpieceSort" /></td>
            </tr>
            <tr>
               <td>Профиль заготовки</td>
               <td><input type="text" name="workpieceProfile" /></td>
            </tr>
            <tr>
               <td>Размер заготовки</td>
               <td><input type="text" name="workpieceSizes" /></td>
            </tr>
            <tr>
               <td>Масса заготовки</td>
               <td><input type="text" name="workpieceWeight" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Добавить" /> &nbsp;
                   <a href="techProcessList?prodId=${prodId}&prod_i=${prod_i}">Назад</a>
               </td>
            </tr>
         </table>
      </form>
   </body>
</html>