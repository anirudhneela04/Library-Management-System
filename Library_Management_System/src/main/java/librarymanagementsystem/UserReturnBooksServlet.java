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

@WebServlet("/UserReturnBooks")
public class UserReturnBooksServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("uemail");
		String bookid = req.getParameter("bid");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem?user=root && password=root");
			    PreparedStatement ps = con.prepareStatement("delete from borrowed_books where email = ? and book_id= ? ");
			    ps.setString(1, email);
			    ps.setString(2,bookid);
			    int row1 = ps.executeUpdate();
			    if(row1>0) {
			    	PreparedStatement ps2 = con.prepareStatement("UPDATE books_data SET quantity = quantity + 1 WHERE book_id = ?");
		            ps2.setString(1, bookid);
		            ps2.executeUpdate();
			    }
			    PrintWriter pw = resp.getWriter();
			    pw.println("<html><body>"
			    		+ "<link rel='stylesheet' href='./styles.css'>"
			    		+ "<link href='https://fonts.googleapis.com/css2?family=Poppins&display=swap' rel='stylesheet'>"
	            		+"<h3>Book returned successfully.</h3>"
	            		+"<a href='userLogin.html'><button>Back to Home</button></a>"
	            		+"</body></html>");
			    
		}catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}

}
