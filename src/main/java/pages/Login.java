package pages;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import db.Database;
import models.User;

@WebServlet({ "/Login", "/Login/" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = Database.fetchUser(username, password, pw, session);

		if (session.getAttribute("username") == null || user == null) {
			pw.println("<script>alert('Invalid Credentials!');</script>");
		}
		response.sendRedirect("dashboard.jsp");
		pw.close();
	}
}