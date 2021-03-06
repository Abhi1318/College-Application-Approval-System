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

@WebServlet(urlPatterns = {"/rajpura_teacher_student_list"})
public class rajpura_teacher_student_list extends HttpServlet {
     @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try
         {
             Connection conn = DBConnection.getConnection();
             String que="select * from rajpura_signup where admission_stat='yes';";
             PreparedStatement stmt = conn.prepareStatement(que);
              ResultSet resultSet = stmt.executeQuery();
              response.setContentType("text/html");
              PrintWriter out = response.getWriter();
              ArrayList<ListingBean> Arr = new ArrayList<ListingBean>(); 
             while(resultSet.next())
             {
               
                Arr.add(new ListingBean(resultSet.getString("fname"),resultSet.getString("lname"),resultSet.getString("course")));
             }
              request.setAttribute("TRY4", Arr);
              request.getRequestDispatcher("/rajpura_teacher_dash.jsp").include(request, response);
          
         }
         catch (IOException | ClassNotFoundException | SQLException | ServletException ex) {
             System.out.print(ex);
         }
    }

}

