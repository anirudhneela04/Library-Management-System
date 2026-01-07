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

public class UserProfileEditServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession();
		String EditEmail = (String) ses.getAttribute("UserEmail");
		String EditPass = (String) ses.getAttribute("UserPass");
		ses.setAttribute("UserPass", EditPass);
		ses.setAttribute("UserEmail", EditEmail);
		
		String q = "select * from user_data where email=?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem?user=root & password=root");
			PreparedStatement ps =con.prepareStatement(q);
			ps.setString(1, EditEmail);
			ResultSet r = ps.executeQuery();
			if(r.next()) {
				String EditPassword = r.getString(2);
				String EditName = r.getString(3);
				String EditMobile = r.getString(4);
				String EditLocation = r.getString(5);
				PrintWriter pw = resp.getWriter();
				pw.println("<html><head>"
						+ "<title>Edit Profile</title>"
						+ "<link rel='stylesheet' href='./styles.css'>"
						+ "<link href='https://fonts.googleapis.com/css2?family=Poppins&display=swap' rel='stylesheet'>"
						+ "<style>"
						+ "table{ border:2px solid; background-color: lightblue; height:600px; width:400px; }"
						+ ".f1{ font-size:25px; }"
						+ "caption{ font-size:30px; }"
						+ "#c1{ text-align:center; }"
						+ "</style>"
						+ "</head>"
						+ "<body>"
						+ "<main>"
						+ "<form action='updateUserDetails' method='post'>"
						+ "<table>"
						+ "<caption>User Details:</caption>"
						+ "<tr>"
						+ "<td> <label for='i1' class='f1' >Email :</label> </td>"
						+ "<td><input id='i1' type='text' name='editemail' placeholder='Enter Email' value="+EditEmail+" required  readonly></td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><label for='i2' class='f1' >Password :</label></td>"
						+ "<td><input id='i2' type='password' name='editpw' placeholder='Enter Password' value="+EditPassword+" required ></td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><label for='i3' class='f1' >Name : </label></td>"
						+ "<td><input id='i3' type='text' name='editname' placeholder='Enter Name'  value="+EditName+" required></td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><label for='i4' class='f1' >Mobile :</label></td>"
						+ "<td><input id='i4' type='text' name='editmobile' placeholder='Enter Mobile' value="+EditMobile+"></td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td><label for='i5' class='f1' >Location :</label></td>"
						+ "<td><input id='i5' type='text' name='editlocation' placeholder='Enter Loaction' value="+EditLocation+"></td>"
						+ "</tr>"
						+ "<tr>"
						+ "<td id='c1'>"
						+ "<button>Update</button>"
						+ "</td>"
						+ "</tr>"
						+ "</table>"
						+ "</form>"
						+ "</main>"
						+ "</body></html>"
						);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
