<%@page import="com.CustomerInformation"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/CustomerInformation.js"></script>
</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Customer Details</h1>
				<form id="formCustomerInformation" name="formCustomerInformation">
					Name: <input id="Name" name="Name"
						type="text" class="form-control form-control-sm"> <br>
					Email: <input
					 	id="Email"name="Email" type="text"
						class="form-control form-control-sm"> <br> 
					Address: <input
						id="Address" name="Address" type="text"
						class="form-control form-control-sm"> <br> 
					NIC:<input
						id="NIC" name="NIC" type="text"
						class="form-control form-control-sm"> <br>
					Account Number:<input
						id="AccountNumber" name="AccountNumber" type="text"
						class="form-control form-control-sm"> <br> 	
					Contact Number:<input
						id="ContactNumber" name="ContactNumber" type="text"
						class="form-control form-control-sm"> <br>
						
						 
					<input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hideCustomerInformationIDSave" name="hideCustomerInformationIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divCustomerInformationGrid">
					<%
					CustomerInformation CustomerObj = new CustomerInformation();
					out.print(CustomerObj.readCustomerInformation());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>>