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

@WebServlet("/AdminDeleteBooks")
public class AdminDeleteBooksServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bookid=req.getParameter("bid");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem?user=root && password=root");
			    PreparedStatement ps = con.prepareStatement("delete from books_data where book_id=?");
			    ps.setString(1, bookid);
			    ps.executeUpdate();
			    PrintWriter pw = resp.getWriter();
			    pw.println("<html><head>"
			    		+ "<title>Book deleted</title>"
			    		+ "<link rel=stylesheet href=./styles.css>"
			    		+ "<link href='https://fonts.googleapis.com/css2?family=Poppins&display=swap' rel='stylesheet'>"
			    		+ "</head>"
			    		+ "<body>"
			    		+ "<div>"
			    		+ "<h1>Successfully Book Deleted</h1>"
			    		+ "<a href='adminLogin.html'><button>return to login</button></a>"
			    		+ "</div>"
			    		+ "</body>"
			    		+ "</html>");
		}catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}

}
