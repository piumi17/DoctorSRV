package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Doctor;

/**
 * Servlet implementation class paymentAPI
 */
@WebServlet("/paymentAPI")
public class DoctorsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Doctor newPayment = new Doctor();

	
	public DoctorsAPI() {
		super();
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String output = newPayment.insertDoctor(request.getParameter("docFname"),
				request.getParameter("docLname"), 
				request.getParameter("nic"), 
				request.getParameter("specialization"),
				request.getParameter("docCharge"),
				request.getParameter("gender"),
				request.getParameter("contact"),
				request.getParameter("email"),
				request.getParameter("address"),
				request.getParameter("hospital"),
				request.getParameter("password"));

		response.getWriter().write(output);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		 
		 String output = newPayment.updateDoctor(paras.get("hidItemIDSave").toString(),
				 paras.get("docFname").toString(), 
				 paras.get("docLname").toString(),
				 paras.get("nic").toString(),
				 paras.get("specialization").toString(),
				 paras.get("docCharge").toString(), 
				 paras.get("gender").toString(),
				 paras.get("contact").toString(),
				 paras.get("email").toString(), 
				 paras.get("address").toString(),
				 paras.get("hospital").toString(),
				 paras.get("password").toString()); 
		 
		 response.getWriter().write(output); 
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map paras = getParasMap(request); 
		 
		 String output = newPayment.deleteDoctor(paras.get("p_id").toString()); 
		 
		 response.getWriter().write(output); 
	}
	
private static Map getParasMap(HttpServletRequest request){
		
		Map<String, String> map = new HashMap<String, String>();
		
		try{
		
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		
			String queryString = scanner.hasNext() ?
		
			scanner.useDelimiter("\\A").next() : "";
		
			scanner.close();
		
			String[] params = queryString.split("&");
		
			for (String param : params){
		
			
				String[] p = param.split("=");
			
				map.put(p[0], p[1]);
			
			}
		}catch (Exception e){}
		return map;
	}


}
