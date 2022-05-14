$(document).ready(function()
{
	$("#alertSuccess").hide();
	$("#alertError").hide();
});
				
$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 

	// Form validation-------------------
	var status = validatePaymentForm(); 
	if (status != true) 
	 { 
	 $("#alertError").text(status); 
	 $("#alertError").show(); 
	 return; 
	 }

	// If valid------------------------
	var type = ($("#hidpayIDSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
	 { 
	 url : "PaymentsAPI", 
	 type : type, 
	 data : $("#formPayment").serialize(), 
	 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 onPaymentSaveComplete(response.responseText, status); 
	 } 
	 }); 
	});

function onPaymentSaveComplete(response, status)
{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully saved."); 
	 $("#alertSuccess").show(); 
	 $("#divPaymentsGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while saving."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while saving.."); 
	 $("#alertError").show(); 
	 }
	$("#hidpayIDSave").val(""); 
	$("#formPayment")[0].reset(); 
}


	// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hidpayIDSave").val($(this).data("payid")); 
		 $("#AccNumber").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#totalAmount").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#payDate").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#cardType").val($(this).closest("tr").find('td:eq(4)').text()); 
		 $("#cardNumber").val($(this).closest("tr").find('td:eq(5)').text()); 
		 $("#cvn").val($(this).closest("tr").find('td:eq(6)').text()); 
		
		});




$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "PaymentsAPI", 
		 type : "DELETE", 
		 data : "payID=" + $(this).data("payid"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onPaymentDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});
		
function onPaymentDeleteComplete(response, status)
{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully deleted."); 
	 $("#alertSuccess").show(); 
	 $("#divPaymentsGrid").html(resultSet.data); 
	 } else if (resultSet.status.trim() == "error") 
	 { 
	 $("#alertError").text(resultSet.data); 
	 $("#alertError").show(); 
	 } 
	 } else if (status == "error") 
	 { 
	 $("#alertError").text("Error while deleting."); 
	 $("#alertError").show(); 
	 } else
	 { 
	 $("#alertError").text("Unknown error while deleting.."); 
	 $("#alertError").show(); 
	 } 
}


	// CLIENT-MODEL================================================================
function validatePaymentForm()
{
	// Account number
	if ($("#AccNumber").val().trim() == "")
	{
	return "Insert Account Number.";
	}
	// Total Amount
	if ($("#totalAmount").val().trim() == "")
	{
	return "Insert Total Amount.";
	}
	
	// is numerical value
			var tmpPrice = $("#totalAmount").val().trim();
			if (!$.isNumeric(tmpPrice))
		{
		return "Insert a numerical value for Total Amount.";
		}
		
	// convert to decimal price
	$("#totalAmount").val(parseFloat(tmpPrice).toFixed(2));
	
	
	// Payment date
	if ($("#payDate").val().trim() == ""){
		
		return "Insert Payment Date.";
	}
	
	// Card Type
	if ($("#cardType").val().trim() == ""){
		
		return "Insert Card Type.";
	}
	
	// Card Number
	if ($("#cardNumber").val().trim() == ""){
		
		return "Insert Card Number.";
	}
	
	// cvn
	if ($("#cvn").val().trim() == ""){
		
		return "Insert CVN.";
	}
		return true;
	
}

	
	
	