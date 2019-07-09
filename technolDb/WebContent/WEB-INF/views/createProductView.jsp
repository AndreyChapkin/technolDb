<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <title>Новое изделие</title>
   </head>
   <body>     
      <h3>Добавить новое изделие</h3>
       
      <form method="POST" action="${pageContext.request.contextPath}/createProduct">

         <table border="0">
            <tr>
               <td>Название</td>
               <td><input type="text" name="name"  /></td>
            </tr>
            <tr>
               <td>Обозначение</td>
               <td><input type="text" name="signName" /></td>
            </tr>
            <tr>
               <td>Название материала</td>
               <td><input type="text" name="material" /></td>
            </tr>
            <tr>
               <td>Марка материала</td>
               <td><input type="text" name="materialBrand" /></td>
            </tr>
            <tr>
               <td>Масса изделия</td>
               <td><input type="text" name="weight" /></td>
            </tr>
            <tr>
               <td>Объём партии</td>
               <td><input type="text" name="batchSize" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Добавить" /> &nbsp;
                   <a href="productList">Назад</a>
               </td>
            </tr>
         </table>
      </form>
   </body>
</html>