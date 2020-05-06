$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event) {

	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateDoctorForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "paymentAPI",
		type : type,
		data : $("#formdoctor").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onDoctorSave(response.responseText, status);
		}
	});
});

//----------------- Insert ---------------------

function onDoctorSave(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();

			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidItemIDSave").val("");
	$("#formdoctor")[0].reset();
}


//--------------- Update ----------------

$(document).on("click",".btnUpdate",function(event) {
	$("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val());
	$("#docFname").val($(this).closest("tr").find('td:eq(0)').text());
	$("#docLname").val($(this).closest("tr").find('td:eq(1)').text());
	$("#nic").val($(this).closest("tr").find('td:eq(2)').text());
	$("#specialization").val($(this).closest("tr").find('td:eq(3)').text());
	$("#docCharge").val($(this).closest("tr").find('td:eq(4)').text());
	$("#gender").val($(this).closest("tr").find('td:eq(5)').text());
	$("#contact").val($(this).closest("tr").find('td:eq(6)').text());
	$("#email").val($(this).closest("tr").find('td:eq(7)').text());
    $("#address").val($(this).closest("tr").find('td:eq(8)').text());
	$("#hospital").val($(this).closest("tr").find('td:eq(9)').text());
	$("#password").val($(this).closest("tr").find('td:eq(10)').text());
	 
});

//------------- Remove ----------------

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "paymentAPI",
		type : "DELETE",
		data : "p_id=" + $(this).data("p_id"),
		dataType : "text",
		complete : function(response, status) {
			onDoctorDelete(response.responseText, status);
		}
	});
});

function onDoctorDelete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();

			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

//-------------- Client Model -------------

//-------------- Form Validation -----------------

function validateDoctorForm() {

	// FIRST NAME
	if ($("#docFname").val().trim() == "") {
		return "Please Insert the First Name";
	}
	
	// LAST NAME
	if ($("#docLname").val().trim() == "") {
		return "Please Insert the Last Name";
	}
	
	// NIC
	if ($("#nic").val().trim() == "") {
		return "Please Insert the NIC Number";
	}
	
	// SPECIALIZATION
	if ($("#specialization").val().trim() == "") {
		return "Please Insert the Specialization";
	}

	// DOCTOR CHARGE
	if ($("#docCharge").val().trim() == "") {
		return "Please Insert the Doctor Charge";
	}

	// is numerical value
	var docCharge = $("#docCharge").val().trim();
	if (!$.isNumeric(docCharge)) {
		return "Insert a Numerical value for Doctor Charge";
	}
	
	// GENDER
	if ($("#gender").val().trim() == "") {
		return "Please Insert the Gender";
	}
	
	// CONTACT
	if ($("#contact").val().trim() == "") {
		return "Please Insert the Contact Number";
	}

	// is numerical value
	var contact = $("#contact").val().trim();
	if (!$.isNumeric(contact)) {
		return "Insert a Numerical value for Contact";
	}
	
	// EMAIL
	if ($("#email").val().trim() == "") {
		return "Please Insert the Email";
	}
	
	// ADDRESS
	if ($("#address").val().trim() == "") {
		return "Please Insert the Address";
	}

	// HOSPITAL
	if ($("#hospital").val().trim() == "") {
		return "Please Insert the Hospital";
	}
	
	// PASSWORD
	if ($("#password").val().trim() == "") {
		return "Please Insert the Password";
	}
	

	return true;
}