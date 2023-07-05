package util;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SubmitFeedback")
public class SubmitFeedback extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SubmitFeedback() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String quizid = request.getParameter("quizid");
		String username = request.getParameter("username");
		int rating = Integer.parseInt(request.getParameter("rating"));
		
		db.Database.submitFeedback(quizid, username, rating);
		
		response.sendRedirect("dashboard.jsp");
	}

}
