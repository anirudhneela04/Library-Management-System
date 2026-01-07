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

public class AddBooksServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String book_id = req.getParameter("book_id");
		String book_title = req.getParameter("book_title");
		String author = req.getParameter("author");
		int quantity = Integer.parseInt( req.getParameter("qty") );
		
		String q="insert into books_data values(?,?,?,?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem?user=root & password=root");
			PreparedStatement ps= con.prepareStatement(q);
			ps.setString(1, book_id);
			ps.setString(2, book_title);
			ps.setString(3, author);
			ps.setInt(4, quantity);
			ps.executeUpdate();
			
			PrintWriter pw = resp.getWriter();
			pw.println("<html><head>"
					+ "<title>Add Book</title>"
					+ "<link rel=stylesheet href=./styles.css>"
					+ "<link href='https://fonts.googleapis.com/css2?family=Poppins&display=swap' rel='stylesheet'>"
					+ "</head>"
					+ "<body>"
					+ "<h1>Successfully Book details <br> added into database</h1>"
					+ "<div class=section>"
					+ "<h2>To See Book Details</h2><br>"
					+ "<form action='bookDetails' method='post'>"
					+ "<button> <- Back to Book Details</button>"
					+ "</form>"
					+ "</div>"
					+ "</body></html>"
					);
		}catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
