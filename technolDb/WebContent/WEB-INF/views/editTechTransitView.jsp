<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Редактировать тех. переход</title>
   </head>
   <body>
 
      <h3>Редактировать технологический переход</h3>
 
         <form method="POST" action="${pageContext.request.contextPath}/editTechTransit">
            
            <input type="hidden" name="prodId" value="${prodId}" />
         	<input type="hidden" name="prod_i" value="${prod_i}" />
         	<input type="hidden" name="tprId" value="${tprId}" />
         	<input type="hidden" name="tpr_i" value="${tpr_i}" />
            <input type="hidden" name="operId" value="${operId}"/>
            <input type="hidden" name="oper_i" value="${oper_i}"/>
            <input type="hidden" name="tranId" value="${techTransit.tranId}" />
            
            <table border="0">
               <tr>
                  <td>Номер</td>
                   <td style="color:red;">${tran_i}</td>
               </tr>
			<tr>
               <td>Номер перехода</td>
               <td><input type="text" name="transitNum" value="${techTransit.transitNum}" /></td>
            </tr>
            <tr>
               <td>Содержание перехода</td>
               <td><input type="text" name="transitCont" value="${techTransit.transitCont}" /></td>
            </tr>
            <tr>
               <td>Вспомогательный инструмент</td>
               <td><input type="text" name="subsidTool" value="${techTransit.subsidTool}" /></td>
            </tr>
            <tr>
               <td>Режущий инструмент</td>
               <td><input type="text" name="cutTool" value="${techTransit.cutTool}" /></td>
            </tr>
            <tr>
               <td>Измерительный инструмент</td>
               <td><input type="text" name="measTool" value="${techTransit.measTool}" /></td>
            </tr>
           <tr>
               <td>Обраб. диаметр (D) или ширина (B), мм</td>
               <td><input type="text" name="width" value="${techTransit.width}" /></td>
            </tr>
            <tr>
               <td>Обрабатываемая длина (L), мм</td>
               <td><input type="text" name="length" value="${techTransit.length}" /></td>
            </tr>
            <tr>
               <td>Глубина резания (t), мм</td>
               <td><input type="text" name="depth" value="${techTransit.depth}" /></td>
            </tr>
            <tr>
               <td>Число рабочих ходов (i)</td>
               <td><input type="text" name="numOfSteps" value="${techTransit.numOfSteps}" /></td>
            </tr>
            <tr>
               <td>Подача (S), мм/об</td>
               <td><input type="text" name="feed" value="${techTransit.feed}" /></td>
            </tr>     
			<tr>
               <td>Скорость вращения шпинделя (n), об/мин</td>
               <td><input type="text" name="rotSpeed" value="${techTransit.rotSpeed}" /></td>
            </tr> 
            <tr>
               <td>Скорость резания (V), мм/мин</td>
               <td><input type="text" name="cutSpeed" value="${techTransit.cutSpeed}" /></td>
            </tr> 
               <tr>
                  <td colspan = "2">
                      <input type="submit" value="Изменить" /> &nbsp;
                      <a href="techTransitList?prodId=${prodId}&prod_i=${prod_i}&tprId=${tprId}&tpr_i=${tpr_i}&operId=${operId}&oper_i=${oper_i}">Назад</a>
                  </td>
               </tr>
            </table>
         </form>
   </body>
</html>