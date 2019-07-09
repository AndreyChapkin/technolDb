<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Редактировать тех. операцию</title>
   </head>
   <body>
 
      <h3>Редактировать технологическую операцию</h3>
 
         <form method="POST" action="${pageContext.request.contextPath}/editTechOperation" enctype="multipart/form-data">
            
            <input type="hidden" name="prodId" value="${prodId}" />
         	<input type="hidden" name="prod_i" value="${prod_i}" />
         	<input type="hidden" name="tprId" value="${tprId}" />
         	<input type="hidden" name="tpr_i" value="${tpr_i}" />
            <input type="hidden" name="operId" value="${techOperation.operId}" />
            <table border="0">
               <tr>
                  <td>Номер</td>
                   <td style="color:red;">${oper_i}</td>
               </tr>
               <tr>
               <td>Название операции</td>
               <td><input type="text" name="operName" value="${techOperation.operName}" /></td>
            </tr>
            <tr>
               <td>Номер цеха</td>
               <td><input type="text" name="workshopNum" value="${techOperation.workshopNum}" /></td>
            </tr>
            <tr>
               <td>Номер участка</td>
               <td><input type="text" name="areaNum" value="${techOperation.areaNum}" /></td>
            </tr>
            <tr>
               <td>Номер операции</td>
               <td><input type="text" name="operNum" value="${techOperation.operNum}" /></td>
            </tr>
            <tr>
               <td>Оборудование</td>
               <td><input type="text" name="equipment" value="${techOperation.equipment}" /></td>
            </tr>
           <tr>
               <td>Охлаждение</td>
               <td><input type="text" name="cooling" value="${techOperation.cooling}" /></td>
            </tr>
            <tr>
               <td>Количество обрабатываемых деталей</td>
               <td><input type="text" name="numOfDetails" value="${techOperation.numOfDetails}" /></td>
            </tr>
            <tr>
               <td>Основное время</td>
               <td><input type="text" name="basicTime" value="${techOperation.basicTime}" /></td>
            </tr>
            <tr>
               <td>Вспомогательное время</td>
               <td><input type="text" name="subsidTime" value="${techOperation.subsidTime}" /></td>
            </tr>
            <tr>
               <td>Штучное время</td>
               <td><input type="text" name="pieceTime" value="${techOperation.pieceTime}" /></td>
            </tr>     
            <tr>
            <td>Файл эскиза</td>
       	    <td><input type="file" name="sketch" accept="image/jpeg,image/png,application/pdf" /></td>
        	<tr />
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Изменить" /> &nbsp;
                      <a href="techOperationList?prodId=${prodId}&prod_i=${prod_i}&tprId=${tprId}&tpr_i=${tpr_i}">Назад</a>
                  </td>
               </tr>
            </table>
         </form>
   </body>
</html>