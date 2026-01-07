package librarymanagementsystem;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRegisterServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String RegEmail = req.getParameter("regemail");
		String RegPassword = req.getParameter("regpw");
		String RegName = req.getParameter("regname");
		String RegMobile = req.getParameter("regmobile");
		String RegLocation = req.getParameter("reglocation");
		
		String q="insert into user_data values(?,?,?,?,?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem?user=root & password=root");
			PreparedStatement ps= con.prepareStatement(q);
			ps.setString(1, RegEmail);
			ps.setString(2, RegPassword);
			ps.setString(3, RegName);
			ps.setString(4, RegMobile);
			ps.setString(5, RegLocation);
			ps.executeUpdate();
			
			RequestDispatcher r = req.getRequestDispatcher("userLogin.html");
			r.forward(req, resp);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
