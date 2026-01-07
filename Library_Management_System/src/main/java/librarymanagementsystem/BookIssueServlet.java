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

@WebServlet("/bookissue")
public class BookIssueServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("regemail");
		String bookid=req.getParameter("bookid");
		String issuedate=req.getParameter("issuedate");
		String returndate=req.getParameter("returndate");
		
		String q = "insert into borrowed_books values(?,?,?,?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem?user=root & password=root");
			PreparedStatement ps =con.prepareStatement(q);
			ps.setString(1, email);
			ps.setString(2, bookid);
			ps.setString(3, issuedate);
			ps.setString(4, returndate);
			ps.executeUpdate();
			
			PrintWriter pw = resp.getWriter();
			pw.println("<html><head>"
					+ "<title>Book Issued</title>"
					+ "<link rel=stylesheet href=./styles.css>"
					+ "<link href='https://fonts.googleapis.com/css2?family=Poppins&display=swap' rel='stylesheet'>"
					+ "</head>"
					+ "<body>"
					+ "<h1>Successfully Book Issued</h1>"
					+ "<div class=section>"
					+ "<h2>To See Book Gallery</h2><br>"
					+ "<form action='bookGallery' method='post'>"
					+ "<button> <- Back to Book Gallery</button>"
					+ "</form>"
					+ "</div>"
					+ "<div class=section>"
					+ "<h2>To see Borrowed Books</h2><br>"
					+ "<form action='Bookborrowed' method='post'>"
					+ "<button>Borrowed Books</button>"
					+ "</form>"
					+ "</div>"
					+ "</body></html>"
					);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
