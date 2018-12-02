package ProjectPackage;
import ProjectPackage.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/leave_rajpura_main"})
public class leave_rajpura_main extends HttpServlet {
     @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try
         {
             Connection conn = DBConnection.getConnection();
             String que="select * from leave_rajpura where status='Pending';";
             PreparedStatement stmt = conn.prepareStatement(que);
              ResultSet resultSet = stmt.executeQuery();
              response.setContentType("text/html");
              PrintWriter out = response.getWriter();
              ArrayList<leave_register_bean> Arr = new ArrayList<leave_register_bean>(); 
             while(resultSet.next())
             {
                Arr.add(new leave_register_bean(resultSet.getString("status"),resultSet.getString("email"),resultSet.getString("name"),resultSet.getString("course"),resultSet.getString("reason"),resultSet.getString("date_from"),resultSet.getString("date_to")));
             }
              request.setAttribute("TRY6", Arr);
              request.getRequestDispatcher("/rajpura_leave_applications.jsp").include(request, response);
          
         }
         catch (IOException | ClassNotFoundException | SQLException | ServletException ex) {
             System.out.print(ex);
         }
    }

}

