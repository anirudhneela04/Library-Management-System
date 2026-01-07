package librarymanagementsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String LogEmail = req.getParameter("logemail");
		String LogPassword = req.getParameter("logpw");
		
		String q = "select * from user_data where email=? and password=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem?user=root & password=root");
			PreparedStatement ps =con.prepareStatement(q);
			ps.setString(1, LogEmail);
			ps.setString(2, LogPassword);
			ResultSet r = ps.executeQuery();
			if(r.next()) {
				PrintWriter pw = resp.getWriter();
				pw.println("<html><head>"
						+ "<title>Library Portal</title>"
						+ "<link rel='stylesheet' href='./styles.css'>"
						+ "<link href='https://fonts.googleapis.com/css2?family=Poppins&display=swap' rel='stylesheet'>"
						+ "<style>"
						+ "nav{"
						+ "border-bottom: 2px grey;"
						+ "align-items: end;"
						+ "}"
						+ "</style>"
						+ "</head>"
						+ "<body>"
						+ "<nav>"
						+ "<form action='userProfile' method='post'>"
						+ "<button>Profile</button>"
						+ "</form>"
						+ "</nav>"
						+ "<main>"
						+ "<h1>Welcome to Library Management System</h1>"
						+ "<div class=section>"
						+ "<h2>Go to Book Gallery</h2><br>"
						+ "<form action='BookGallery' method='post'>"
						+ "<button>Book Gallery</button>"
						+ "</form>"
						+ "</div>"
						+ "<div class=section>"
						+ "<h2>To see Borrowed Books</h2><br>"
						+ "<form action='Booksborrowed' method='post'>"
						+ "<button>Borrowed Books</button>"
						+ "</form>"
						+ "</div>"
						+ "</main>"
						+ "</body></html>"
						);
				
				HttpSession ses = req.getSession();
				ses.setAttribute("UserEmail", LogEmail);
			}else {
				PrintWriter pw = resp.getWriter();
				pw.println("<html><head><style>"
						+ "h3{color:red;}"
						+ "</style></head>"
						+ "<body>"
						+ "<h3>Invalid username or password</h3>"
						+ "</body></html>");
				RequestDispatcher rd= req.getRequestDispatcher("userLogin.html");
				rd.include(req, resp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
