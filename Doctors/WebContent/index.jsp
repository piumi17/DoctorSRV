<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.Doctor"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Doctor Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-8">
				<h1 class="m-3">Doctor Details</h1>
				<form id="formdoctor">

					<!-- FIRST NAME -->
					FIRST NAME <br>
					<div class="input-group input-group-sm mb-3">
							<input type="text" id="docFname" name="docFname" class="form-control form-control-sm">
					</div>
					
					<!-- LAST NAME -->
					LAST NAME <br>
					<div class="input-group input-group-sm mb-3">
							<input type="text" id="docLname" name="docLname" class="form-control form-control-sm">
					</div>
					
					<!-- NIC -->
					NIC <br>
					<div class="input-group input-group-sm mb-3">
							<input type="text" id="nic" name="nic" class="form-control form-control-sm">
					</div>
					
					<!-- SPECIALIZATION -->
					SPECIALIZATION <br>
					<div class="input-group input-group-sm mb-3">
							<input type="text" id="specialization" name="specialization" class="form-control form-control-sm">
					</div>

					<!-- DOCTOR CHARGE -->
					DOCTOR CHARGE <br>
					<div class="input-group input-group-sm mb-3">
						<input type="text" id="docCharge" name="docCharge" class="form-control form-control-sm">
					</div>
					
					<!-- GENDER -->
					GENDER <br>
					<div class="input-group input-group-sm mb-3">
						<input type="text" id="gender" name="gender" class="form-control form-control-sm">
					</div>
					
					<!-- CONTACT -->
					CONTACT <br>
					<div class="input-group input-group-sm mb-3">
						<input type="text" id="contact" name="contact" class="form-control form-control-sm">
					</div>

					<!-- EMAIL -->
					EMAIL <br>
					<div class="input-group input-group-sm mb-3">
						<input type="text" id="email" name="email" class="form-control form-control-sm">
					</div>

					<!-- ADDRESS -->
					ADDRESS <br>
					<div class="input-group input-group-sm mb-3">
						<input type="text" id="address" name="address" class="form-control form-control-sm">
					</div>
					
					<!-- HOSPITAL -->
					HOSPITAL <br>
					<div class="input-group input-group-sm mb-3">
						<input type="text" id="hospital" name="hospital" class="form-control form-control-sm">
					</div>
					
					<!-- PASSWORD -->
					PASSWORD <br>
					<div class="input-group input-group-sm mb-3">
						<input type="text" id="password" name="password" class="form-control form-control-sm">
					</div>


					<input type="button" id="btnSave" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidItemIDSave" name="hidItemIDSave" value="">
				</form>
				<br>

				<div id="alertSuccess" class="alert alert-success"></div>

				<div id="alertError" class="alert alert-danger"></div>

				<div class='col-12'>
					<div id="divItemsGrid">
						<%
							Doctor payObj = new Doctor();
											out.print(payObj.readDoctor());
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>