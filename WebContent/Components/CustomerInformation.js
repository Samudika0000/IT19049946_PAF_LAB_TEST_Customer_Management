$(document).ready(function()
{ 

 $("#alertSuccess").hide(); 
 $("#alertError").hide(); 
}); 


function onCustomerInformationSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divCustomerInformationGrid").html(resultSet.data); 
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
$("#hideCustomerInformationIDSave").val(""); 
$("#formCustomerInformation")[0].reset(); 
}


$(document).on("click", "#btnSave", function(event)
{ 
	
	// Clear alerts---------------------
	 $("#alertSuccess").text(""); 
	 $("#alertSuccess").hide(); 
	 $("#alertError").text(""); 
	 $("#alertError").hide(); 
	 
	 
	// Form validation-------------------
	var status = validateCustomerForm(); 
	if (status != true) 
	 { 
		alert("invalid");
	 $("#alertError").text(status); 
	 $("#alertError").show(); 
	 return; 
	 } 


	// If valid------------------------
	var type = ($("#hideCustomerInformationIDSave").val() == "") ? "POST" : "PUT"; 
	 $.ajax( 
	 { 
	 url : "CustomerInformationAPI", 
	 type : type, 
	 data : $("#formCustomerInformation").serialize(), 
	 dataType : "text", 
	 complete : function(response, status) 
	 { 
	 onCustomerInformationSaveComplete(response.responseText, status); 
	 } 
	 }); 
	});

	


	// UPDATE==========================================
	$(document).on("click", ".btnUpdate", function(event)
			{ 
			$("#hideCustomerInformationIDSave").val($(this).data("cusid")); 
			 $("#Name").val($(this).closest("tr").find('td:eq(0)').text()); 
			 $("#Email").val($(this).closest("tr").find('td:eq(1)').text()); 
			 $("#Address").val($(this).closest("tr").find('td:eq(2)').text()); 
			 $("#NIC").val($(this).closest("tr").find('td:eq(3)').text()); 
			 $("#AccountNumber").val($(this).closest("tr").find('td:eq(4)').text()); 
			 $("#ContactNumber").val($(this).closest("tr").find('td:eq(5)').text()); 
			 
			});




	$(document).on("click", ".btnRemove", function(event)
			{ 
			 $.ajax( 
			 { 
			 url : "CustomerInformationAPI", 
			 type : "DELETE", 
			 data : "CusID=" + $(this).data("cusid"),
			 dataType : "text", 
			 complete : function(response, status) 
			 { 
				 onCustomerInformationDeleteComplete(response.responseText, status); 
			 } 
			 }); 
			});
			
	function onCustomerInformationDeleteComplete(response, status)
	{ 
	if (status == "success") 
	 { 
	 var resultSet = JSON.parse(response); 
	 if (resultSet.status.trim() == "success") 
	 { 
	 $("#alertSuccess").text("Successfully deleted."); 
	 $("#alertSuccess").show(); 
	 $("#divCustomerInformationGrid").html(resultSet.data); 
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
	function validateCustomerForm()
	{
		// user name
		if ($("#Name").val().trim() == "")
		{
		return "Insert Customer Name.";
		}
		// Email
		if ($("#Email").val().trim() == "")
		{
		return "Insert Customer Email.";
		
	    }
		//Address
		if ($("#Address").val().trim() == "")
		{
		return "Insert Customer Address.";
		}
		// NIC
		if ($("#NIC").val().trim() == "")
		{
		return "Insert Customer .";
	    }
		//Account Number
		if ($("#AccountNumber").val().trim() == "")
		{
		return "Insert Customer Account Number  .";
	    }
		// ContactNumber
		if ($("#ContactNumber").val().trim() == "")
		{
		return "Insert Customer Contact Number  .";
	    }
		return true;
	}	 
