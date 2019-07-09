<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Ред. тех. контроль</title>
   </head>
   <body>     
      <h3>Редактировать технический контроль</h3>
       
      <form method="POST" action="${pageContext.request.contextPath}/editTechControl">
      
      	 <input type="hidden" name="prodId" value="${prodId}" />
         <input type="hidden" name="prod_i" value="${prod_i}" />
         <input type="hidden" name="tprId" value="${tprId}" />
         <input type="hidden" name="tpr_i" value="${tpr_i}" />
         <input type="hidden" name="operId" value="${operId}" />
         <input type="hidden" name="oper_i" value="${oper_i}" />
         <input type="hidden" name="ctrId" value="${techControl.ctrId}" />
         
         <table border="0">
        	<tr>
               <td>Номер</td>
               <td style="color:red;">${ctr_i}</td>
            </tr>
            <tr>
               <td>Разработал</td>
               <td><input type="text" name="ctrDeveloper" value="${techControl.ctrDeveloper}" /></td>
            </tr>
            <tr>
               <td>Проверил</td>
               <td><input type="text" name="ctrSupervisor" value="${techControl.ctrSupervisor}" /></td>
            </tr>
            <tr>
               <td>Наименование операции</td>
               <td><input type="text" name="ctrName" value="${techControl.ctrName}" /></td>
            </tr>
            <tr>
               <td>Наименование оборудования</td>
               <td><input type="text" name="ctrEquipment" value="${techControl.ctrEquipment}" /></td>
            </tr>
            <tr>
               <td>Обозначение ИОТ</td>
               <td><input type="text" name="safeInstr" value="${techControl.safeInstr}" /></td>
            </tr>
           <tr>
               <td>Основное время</td>
               <td><input type="text" name="ctrBasicTime" value="${techControl.ctrBasicTime}" /></td>
            </tr>
            <tr>
               <td>Вспомогательное время</td>
               <td><input type="text" name="ctrSubsidTime" value="${techControl.ctrSubsidTime}" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Изменить" /> &nbsp;
                   <a href="techControlList?prodId=${prodId}&prod_i=${prod_i}&tprId=${tprId}&tpr_i=${tpr_i}&operId=${operId}&oper_i=${oper_i}">Назад</a>
               </td>
            </tr>
         </table>
      </form>
   </body>
</html>