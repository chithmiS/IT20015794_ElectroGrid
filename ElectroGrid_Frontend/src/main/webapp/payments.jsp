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
	<div class="container"><div class="row"><div class="col-6"> 
	<h1>Payment Management</h1>
	
		<form id="formPayment" name="formPayment" method="post" action="payments.jsp">
			 Account Number: 
	 			<input id="AccNumber" name="AccNumber" type="text" class="form-control form-control-sm">
			 	<br>
			 Total Amount: 
	 			<input id="totalAmount" name="totalAmount" type="text" class="form-control form-control-sm">
	 			<br>
	 		 Payment Date: 
				<input id="payDate" name="payDate" type="date"  class="form-control form-control-sm">
	 			<br>
	 		 Card Type: 
	 			<input id="cardType" name="cardType" type="text" class="form-control form-control-sm">
	 			<br>
	 		 Card Number: 
	 			<input id="cardNumber" name="cardNumber" type="text" class="form-control form-control-sm">
	 			<br>
	 		 CVN: 
	 			<input id="cvn" name="cvn" type="text" class="form-control form-control-sm">
	 			<br>
	 			<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
	 			<input type="hidden" id="hidpayIDSave" name="hidpayIDSave" value="">
		</form>
		<br><br>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		
		<br>
		
		<div id="divPaymentsGrid">
		 <%
			 Payment paymentObj = new Payment(); 
			 out.print(paymentObj.readPayments()); 
		 %>
		</div>
		</div> </div> </div> 

</body>
</html>