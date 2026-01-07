package librarymanagementsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserProfileServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession();
		String ProfileEmail = (String) ses.getAttribute("UserEmail");
		
		String q = "select * from user_data where email=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem?user=root & password=root");
			PreparedStatement ps =con.prepareStatement(q);
			ps.setString(1, ProfileEmail);
			ResultSet r = ps.executeQuery();
			if(r.next()) {
				String ProfilePassword = r.getString(2);
				String ProfileName = r.getString(3);
				String ProfileMobile = r.getString(4);
				String ProfileLocation = r.getString(5);
				PrintWriter pw = resp.getWriter();
				pw.println("<html><head>"
						+ "<title>User Profile</title>"
						+ "<link rel='stylesheet' href='./styles.css'>"
						+ "<link href='https://fonts.googleapis.com/css2?family=Poppins&display=swap' rel='stylesheet'>"
						+ "</head>"
						+ "<body>"
						+ "<main>"
						+ "<br>"
						+ "<h1>Details :</h1>"
						+ "<br><br>"
						+ "<h2>Email : "+ProfileEmail+"</h2>"
						+ "<br>"
						+ "<h2>Password : xxxxxxxx </h2>"
						+ "<br>"
						+ "<h2>Name : "+ProfileName+"</h2>"
						+ "<br>"
						+ "<h2>Mobile : "+ProfileMobile+"</h2>"
						+ "<br>"
						+ "<h2>Location : "+ProfileLocation+"</h2>"
						+ "<br>"
						+ "<form action='UserdetailsEdit' method='post'>"
						+ "<button>Edit</button>"
						+ "</form>"
						+ "<br>"
						+ "<form action='returntoLogin' method='post'>"
						+ "<button> <- return to login page</button>"
						+ "</form>"
						+ "</div>"
						+ "</main>"
						+ "</body></html>"
						);
				ses.setAttribute("UserEmail", ProfileEmail);
				ses.setAttribute("UserPass", ProfilePassword);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
