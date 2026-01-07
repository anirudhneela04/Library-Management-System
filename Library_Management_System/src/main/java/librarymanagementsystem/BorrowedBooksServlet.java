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

public class BorrowedBooksServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ses = req.getSession();
		String email = (String) ses.getAttribute("UserEmail");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem?user=root && password=root");
			    PreparedStatement ps = con.prepareStatement("select b.book_id, b.book_title, b.author, bb.issued_date, bb.return_date from borrowed_books bb join books_data b ON bb.book_id = b.book_id where bb.email = ? ");
			    ps.setString(1, email);
			    ResultSet rs = ps.executeQuery();
			    PrintWriter pw = resp.getWriter();
			    
			    pw.println("<html><head>"
			    		+ "<title>Borrowed Books</title>"
			    		+ "<link href='https://fonts.googleapis.com/css2?family=Poppins&display=swap' rel='stylesheet'>"
			    		+ "<style>"
			            + "body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }"
			            + "table { width: 100%; border-collapse: collapse; background-color: #fff; box-shadow: 0 0 10px rgba(0,0,0,0.1); }"
			            + "th, td { padding: 12px 15px; border: 1px solid #ccc; text-align: center; }"
			            + "th { background-color: #3498db; color: white; }"
			            + "tr:nth-child(even) { background-color: #f2f2f2; }"
			            + "#btn{ background-color: red;}"
			            + "#btn:hover{ background-color:#d32f2f}"
			            + "button { background-color: #27ae60; color: white; border: none; padding: 8px 12px; cursor: pointer; border-radius: 4px; }"
			            + "button:hover { background-color: #219150; }"
			            + "form { margin: 0; }"
			            + "</style></head>"
			            + "<body>"
			            + "<table>"
			            + "<thead>"
			            + "<tr>"
			            + "<th>Book Title</th>"
			            + "<th>Author</th>"
			            + "<th>Issue Date</th>"
			            + "<th>Return Date</th>"
			            + "<th>Action</th>"
			            + "</tr>"
			            + "</thead>"
			            + "<tbody>");

			    while(rs.next()) {
			    	 String bookid = rs.getString("book_id");
			    	 String title = rs.getString("book_title");
		             String author = rs.getString("author");
		             String issueDate = rs.getString("issued_date");
		             String returnDate = rs.getString("return_date");
			        pw.println("<tr>"
			                + "<td>" + title + "</td>"
			                + "<td>" + author + "</td>"
			                + "<td>" + issueDate + "</td>"
			                + "<td>" + returnDate + "</td>"
			                + "<td>"
			                + "<form action='UserReturnBooks' method='post'>"
			                + "<input type='hidden' name='bid' value="+bookid+">"
			                + "<input type='hidden' name='uemail' value="+email+">"
				    		+ "<button id='btn'>return Book</button>"
				    		+ "</form>"
			                + "</td>"
			                + "</tr>"
			                );
			    }

			    pw.println("</tbody></table>"
			    		+ "<a href='userLogin.html'><button>return to login</button></a>"
			    		+ "</body></html>");
		}catch (ClassNotFoundException | SQLException e) {
		
			e.printStackTrace();
		}
	}
}
