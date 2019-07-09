<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Новая тех.операция</title>
   </head>
   <body>     
      <h3>Добавить новую технологическую операцию</h3>
       
      <form method="POST" action="${pageContext.request.contextPath}/createTechOperation" enctype="multipart/form-data">
         <input type="hidden" name="prodId" value="${prodId}" />
         <input type="hidden" name="prod_i" value="${prod_i}" />
         <input type="hidden" name="tprId" value="${tprId}" />
         <input type="hidden" name="tpr_i" value="${tpr_i}" />
         <table border="0">
            <tr>
               <td>Название операции</td>
               <td><input type="text" name="operName"  /></td>
            </tr>
            <tr>
               <td>Номер цеха</td>
               <td><input type="text" name="workshopNum" /></td>
            </tr>
            <tr>
               <td>Номер участка</td>
               <td><input type="text" name="areaNum" /></td>
            </tr>
            <tr>
               <td>Номер операции</td>
               <td><input type="text" name="operNum" /></td>
            </tr>
            <tr>
               <td>Оборудование</td>
               <td><input type="text" name="equipment" /></td>
            </tr>
           <tr>
               <td>Охлаждение</td>
               <td><input type="text" name="cooling" /></td>
            </tr>
            <tr>
               <td>Количество обрабатываемых деталей</td>
               <td><input type="text" name="numOfDetails" /></td>
            </tr>
            <tr>
               <td>Основное время</td>
               <td><input type="text" name="basicTime" /></td>
            </tr>
            <tr>
               <td>Вспомогательное время</td>
               <td><input type="text" name="subsidTime" /></td>
            </tr>
            <tr>
               <td>Штучное время</td>
               <td><input type="text" name="pieceTime" /></td>
            </tr>     
            <tr>
            <td>Файл эскиза</td>
       	    <td><input type="file" name="sketch" /></td>
        	<tr />
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Добавить" /> &nbsp;
                   <a href="techOperationList?prodId=${prodId}&prod_i=${prod_i}&tprId=${tprId}&tpr_i=${tpr_i}">Назад</a>
               </td>
            </tr>
         </table>
      </form>
   </body>
</html>