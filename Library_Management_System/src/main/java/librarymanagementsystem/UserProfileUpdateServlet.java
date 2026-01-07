package librarymanagementsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserProfileUpdateServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses =req.getSession();
		String pw = (String) ses.getAttribute("UserPass");
		
		String UpdateEmail = req.getParameter("editemail");
		String UpdatePassword = req.getParameter("editpw");
		String UpdateName= req.getParameter("editname");
		String UpdateMobile= req.getParameter("editmobile");
		String UpdateLocation= req.getParameter("editlocation");
		
		String q="update user_data set password=?,name=?,mobile2=?,location=? where email=?";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem?user=root && password=root");
			PreparedStatement p = con.prepareStatement(q);
			p.setString(1,UpdatePassword);
			p.setString(2,UpdateName);
			p.setString(3,UpdateMobile);
			p.setString(4,UpdateLocation);
			p.setString(5,UpdateEmail);
			p.executeUpdate();
			
			
			if(UpdatePassword.equals(pw)) {
				PrintWriter p1 =resp.getWriter();
				p1.println("<html><head>"
						+ "<title>Details Updated</title>"
						+ "<link rel='stylesheet' href='./styles.css'>"
						+ "<link href='https://fonts.googleapis.com/css2?family=Poppins&display=swap' rel='stylesheet'>"
						+ "</head>"
						+ "<body>"
						+ "<h1>Successfully User details <br> added into database</h1>"
						+ "<div class=section>"
						+ "<h2>To See User Details</h2><br>"
						+"<form action='returntoUser' method='post'>"
						+ "<button> -> return to User Details</button>"
						+ "</form>"
						+ "</div>"
						+ "</body>"
						+ "</html>"
						);
			}else {
				PrintWriter p2 =resp.getWriter();
				p2.println( "<html><head>"
						+ "<title>Details Updated</title>"
						+ "<link rel='stylesheet' href='./styles.css'>"
						+ "</head>"
						+ "<body>"
						+ "<h1>Successfully User details <br> added into database</h1>"
						+ "<div class=section>"
						+ "<h2>To See User Details</h2><br>"
						+"<button><a href='userLogin.html'> -> return to User Login</a></button>"
						+ "</div>"		
						+ "</body>"
						+ "</html>"
						);
			}
		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
