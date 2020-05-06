package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Doctor {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctordetails", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertDoctor(String docFname, String docLname, String nic, String specialization, String docCharge, String gender, String contact, String email, String address, String hospital, String password)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for Inserting";
			}
			
			// create a prepared statement
			String query = " insert into doctor(`d_id`,`docFname`,`docLname`,`nic`,`specialization`,`docCharge`,`gender`,`contact`,`email`,`address`,`hospital`, `password`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, docFname);
			preparedStmt.setString(3, docLname);
			preparedStmt.setString(4, nic);
			preparedStmt.setString(5, specialization);
			preparedStmt.setDouble(6, Double.parseDouble(docCharge));
			preparedStmt.setString(7, gender);
			preparedStmt.setInt(8, Integer.parseInt(contact));
			preparedStmt.setString(9, email);
			preparedStmt.setString(10, address);
			preparedStmt.setString(11, hospital);
			preparedStmt.setString(12, password);

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newPayments = readDoctor();
			output = "{\"status\":\"success\", \"data\": \"" + newPayments + "\"}";

		} catch (Exception e) {

			output = "{\"status\":\"error\", \"data\":" + " \"Error while inserting the Doctor\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readDoctor() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for Reading";
			}
			
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>First Name</th><th>Last Name</th><th>NIC</th><th>Specialization</th><th>DocCharge</th><th>Gender</th><th>Contact</th><th>Email</th><th>Address</th><th>Hospital</th><th>Password</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from doctor";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next()) {
				String p_id = Integer.toString(rs.getInt("d_id"));
				String docFname = rs.getString("docFname");
				String docLname = rs.getString("docLname");
				String nic = rs.getString("nic");
				String specialization = rs.getString("specialization");
				String docCharge = Double.toString(rs.getDouble("docCharge"));
				String gender = rs.getString("gender");
				String contact = Integer.toString(rs.getInt("contact"));
				String email = rs.getString("email");
				String address = rs.getString("address");
				String hospital = rs.getString("hospital");
				String password = rs.getString("password");
				

				// Add into the html table

				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + p_id
						+ "'>" + docFname +"</td>";
				output += "<td>" + docLname + "</td>";
				output += "<td>" + nic + "</td>";
				output += "<td>" + specialization + "</td>";
				output += "<td>" + docCharge + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + contact + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + hospital + "</td>";
				output += "<td>" + password + "</td>";
				

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td><td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-p_id='"
						+ p_id + "'></td></tr>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Doctor";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateDoctor(String ID, String docFname, String docLname, String nic, String specialization, String docCharge, String gender, String contact, String email, String address, String hospital, String password)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for Updating";
			}
			// create a prepared statement
			String query = "UPDATE doctor SET docFname=?,docLname=?,nic=?,specialization=?,docCharge=?,gender=?,contact=?,email=?,address=?,hospital=?,password=? WHERE d_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, docFname);
			preparedStmt.setString(2, docLname);
			preparedStmt.setString(3, nic);
			preparedStmt.setString(4, specialization);
			preparedStmt.setDouble(5, Double.parseDouble(docCharge));
			preparedStmt.setString(6, gender);
			preparedStmt.setInt(7, Integer.parseInt(contact));
			preparedStmt.setString(8, email);
			preparedStmt.setString(9, address);
			preparedStmt.setString(10, hospital);
			preparedStmt.setString(11, password);			
			
			preparedStmt.setInt(12, Integer.parseInt(ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			String newPayments = readDoctor();
			output = "{\"status\":\"success\", \"data\": \"" + newPayments + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":  " + " \"Error while updating the Doctor\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteDoctor(String p_id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for Deleting";
			}
			// create a prepared statement
			String query = "delete from doctor where d_id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(p_id));
			// execute the statement
			preparedStmt.execute();
			con.close();

			String newPayments = readDoctor();
			output = "{\"status\":\"success\", \"data\": \"" + newPayments + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Doctor\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
