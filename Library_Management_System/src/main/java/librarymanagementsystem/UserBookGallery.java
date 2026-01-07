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

public class UserBookGallery extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem?user=root && password=root");
			    PreparedStatement ps = con.prepareStatement("select * from books_data");
			    ResultSet r = ps.executeQuery();
			    PrintWriter pw = resp.getWriter();
			    
			    pw.println("<html><head>"
			    		+ "<title> Book Gallery </title>"
			    		+ "<link href='https://fonts.googleapis.com/css2?family=Poppins&display=swap' rel='stylesheet'>"
			    		+ "<style>"
			            + "body { font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; }"
			            + "table { width: 100%; border-collapse: collapse; background-color: #fff; box-shadow: 0 0 10px rgba(0,0,0,0.1); }"
			            + "th, td { padding: 12px 15px; border: 1px solid #ccc; text-align: center; }"
			            + "th { background-color: #3498db; color: white; }"
			            + "tr:nth-child(even) { background-color: #f2f2f2; }"
			            + "button { background-color: #27ae60; color: white; border: none; padding: 8px 12px; cursor: pointer; border-radius: 4px; }"
			            + "button:hover { background-color: #219150; }"
			            + "form { margin: 0; }"
			            + "</style></head>"
			            + "<body>"
			            + "<table>"
			            + "<thead>"
			            + "<tr>"
			            + "<th>Book ID</th>"
			            + "<th>Book Title</th>"
			            + "<th>Author</th>"
			            + "<th>Action</th>"
			            + "</tr>"
			            + "</thead>"
			            + "<tbody>");

			    while(r.next()) {
			        String bookid = r.getString(1);
			        String booktitle = r.getString(2);
			        String author = r.getString(3);
			        int quantity = r.getInt(4);
			        if(quantity>0) {
			        	pw.println("<tr>"
				                + "<td>" + bookid + "</td>"
				                + "<td>" + booktitle + "</td>"
				                + "<td>" + author + "</td>"
				                + "<td>"
				                + "<form action='takebook' method='post'>"
				                + "<input type='hidden' name='bid' value="+bookid+">"
				                + "<button>Take Book</button>"
				                + "</form>"
				                + "</td>"
				                + "</tr>");
			        }
			    }

			    pw.println("</tbody></table></body></html>");
			    
			    
		} catch (ClassNotFoundException | SQLException e) {
		
			e.printStackTrace();
		}
	}

}
