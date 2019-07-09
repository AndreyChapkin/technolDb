<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Новый контр. этап</title>
   </head>
   <body>     
      <h3>Добавить новый этап контроля</h3>
       
      <form method="POST" action="${pageContext.request.contextPath}/createControlStep">
      	 <input type="hidden" name="prodId" value="${prodId}" />
         <input type="hidden" name="prod_i" value="${prod_i}" />
         <input type="hidden" name="tprId" value="${tprId}" />
         <input type="hidden" name="tpr_i" value="${tpr_i}" />
         <input type="hidden" name="operId" value="${operId}" />
         <input type="hidden" name="oper_i" value="${oper_i}" />
         <input type="hidden" name="ctrId" value="${ctrId}" />
         <input type="hidden" name="ctr_i" value="${ctr_i}" />
         <input type="hidden" name="step_i" value="${step_i}" />
         <table border="0">
            <tr>
               <td>Контролируемый параметр</td>
               <td><input type="text" name="ctrParam"  /></td>
            </tr>
            <tr>
               <td>Наименование средств ТО</td>
               <td><input type="text" name="techEqName" /></td>
            </tr>
            <tr>
               <td>Код средств ТО</td>
               <td><input type="text" name="techEqCode" /></td>
            </tr>
            <tr>
               <td>Т<sub>о</sub>/Т<sub>в</sub></td>
               <td><input type="text" name="baseSubTime" /></td>
            </tr>
            <tr>
               <td>Объём и ПК</td>
               <td><input type="text" name="ctrValue" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Добавить" /> &nbsp;
                   <a href="controlStepList?prodId=${prodId}&prod_i=${prod_i}&tprId=${tprId}&tpr_i=${tpr_i}&operId=${operId}&oper_i=${oper_i}&ctrId=${ctrId}&ctr_i=${ctr_i}#${step_i-1}">Назад</a>
               </td>
            </tr>
         </table>
      </form>
   </body>
</html>