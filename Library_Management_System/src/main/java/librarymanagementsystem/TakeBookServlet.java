package librarymanagementsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/takebook")
public class TakeBookServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession();
		String usremail = (String) ses.getAttribute("UserEmail");
		String bookid = req.getParameter("bid");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem?user=root && password=root");
			    PreparedStatement ps = con.prepareStatement("update books_data set quantity=quantity-1 where book_id=?");
			    ps.setString(1, bookid);
			    ps.executeUpdate();
			    PrintWriter pw = resp.getWriter();
			    pw.println("<html><head>"
			    		+ "<title> Book Issue </title>"
			    		+ "<link rel='stylesheet' href='./styles.css'>"
			    		+ "<link href='https://fonts.googleapis.com/css2?family=Poppins&display=swap' rel='stylesheet'>"
			    		+ "</head>"
			    		+ "<body>"
			    		+ "<form action='bookissue' method='post'>"
			    		+ "<input type='hidden' name='regemail'  value="+usremail+" required><br>"
			    		+ "<input type='hidden' name='bookid' value="+bookid+" required><br>"
			    		+ "<lable>Issue Date </label>"
			    		+ "<input type='date' name='issuedate' placeholder='Issued_Date' required><br><br>"
			    		+ "<lable>Return Date </label>"
			    		+ "<input type='date' name='returndate' placeholder='Return_Date' required><br><br>"
			    		+ "<button>Issue Book</button>"
			    		+ "</form>"
			    		+ "</body></html>"
			    		);
		} catch (ClassNotFoundException | SQLException e) {
		
			e.printStackTrace();
		}
	}
}
