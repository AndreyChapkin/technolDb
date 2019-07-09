<%@ page language="java" contentType="text/html; charset=cp1251"
 pageEncoding="cp1251"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<head>
<style>
.prod_table{
    border-collapse: collapse; 
    frame:void;
   }
.prod_col{
   	border: 2px double #008;
    padding: 3px; 
   }  
.prod_tr {
    padding: 2px; 
    border: 2px solid #008; 
    color: #008; 
   }
  </style>
</head>
<body>
  <p><b>Изделие</b></p>

    <table border="1" cellpadding="5" cellspacing="1" class="prod_table">
    <col span="7" class="prod_col">
       <tr bgcolor="#C6F09F" class="prod_tr">
          <th></th>
          <th>Название</th>
          <th>Обозначение</th>
          <th>Название материала</th>
          <th>Марка материала</th>
          <th>Масса</th>
          <th>Объём партии</th>
       </tr>
          <tr align="center">
             <td>${prod_i}</td>
             <td>${product.name}</td>
             <td>${product.signName}</td>
             <td>${product.material}</td>
             <td>${product.materialBrand}</td>
             <td>${product.weight}</td>
             <td>${product.batchSize}</td>
          </tr>
    </table>   
</body>  