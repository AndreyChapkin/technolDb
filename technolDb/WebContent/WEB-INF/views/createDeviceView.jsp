<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Новое приспособ.</title>
   </head>
   <body>     
      <h3>Добавить новое приспособление</h3>
       
      <form method="POST" action="${pageContext.request.contextPath}/createDevice">
      	 <input type="hidden" name="prodId" value="${prodId}" />
         <input type="hidden" name="prod_i" value="${prod_i}" />
         <input type="hidden" name="tprId" value="${tprId}" />
         <input type="hidden" name="tpr_i" value="${tpr_i}" />
         <input type="hidden" name="operId" value="${operId}" />
         <input type="hidden" name="oper_i" value="${oper_i}" />
         <table border="0">
            <tr>
               <td>Наименование приспособления</td>
               <td><input type="text" name="devName"  /></td>
            </tr>
            <tr>
               <td>Код приспособления</td>
               <td><input type="text" name="devCode" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Добавить" /> &nbsp;
                   <a href="deviceList?prodId=${prodId}&prod_i=${prod_i}&tprId=${tprId}&tpr_i=${tpr_i}&operId=${operId}&oper_i=${oper_i}">Назад</a>
               </td>
            </tr>
         </table>
      </form>
   </body>
</html>