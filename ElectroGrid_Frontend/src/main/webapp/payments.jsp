<%@page import="com.Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ElectroGrid-Payment Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/payments.js"></script>
</head>
<body>

	<div class="container"> <div class="col-7">
	<br>
	<div class="jumbotron">
	<h1 class="display-6" align="center">Payment Details</h1>
	<br>
	<br>
		
		<form  id="formPayment" name="formPayment" method="post" action="payments.jsp">
		
			 <b>Account Number: </b>
	 			<input id="AccNumber" name="AccNumber" type="text" placeholder="Enter Account Number" class="form-control form-control-sm">
			 	<br>
			 <b>Total Amount: </b>
	 			<input id="totalAmount" name="totalAmount" type="text" placeholder="Enter Total Amount" class="form-control form-control-sm">
	 			<br>
	 		<b> Payment Date: </b>
				<input id="payDate" name="payDate" type="date"  class="form-control form-control-sm">
	 			<br>
	 		 <b> Card Type: </b>
	 			<input id="cardType" name="cardType" type="text" placeholder="Enter Card Type" class="form-control form-control-sm">
	 			<br>
	 		 <b>Card Number: </b>
	 			<input id="cardNumber" name="cardNumber" type="text" placeholder="Enter Card Number" class="form-control form-control-sm">
	 			<br>
	 		<b> CVN:</b> 
	 			<input id="cvn" name="cvn" type="text" placeholder="Enter CVN" class="form-control form-control-sm">
	 			<br>
	 			<div style="text-align:center">  
	 			<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
	 			<input type="hidden" id="hidpayIDSave" name="hidpayIDSave" value="">
	 			</div>
	 	
		</form>
		
		<br><br>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		</div>
		<br>
		<h1 class="display-6" align="center"> Payment Details </h1>
		<br>
		<div id="divPaymentsGrid">
		 <%
			 Payment paymentObj = new Payment(); 
			 out.print(paymentObj.readPayments()); 
		 %>
		</div>
		</div> </div> 

</body>
</html>