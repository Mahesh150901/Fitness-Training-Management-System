import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Dashboardtr extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
      response.setContentType("text/html");
      PrintWriter pw=response.getWriter();
      String Name=request.getParameter("Heading");
      String Link=request.getParameter("Class");
      String Date=request.getParameter("Date");
      String Time=request.getParameter("Time");
      try
      {
          Class.forName("com.mysql.jdbc.Driver");
          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/fitness","root","");
          PreparedStatement pst=con.prepareStatement("insert into class values(?,?,?,?)");
          pst.setString(1,Name);
          pst.setString(2,Link);
          pst.setString(3,Date);
          pst.setString(4,Time);
          int i=pst.executeUpdate();
          con.close();
          if(i>0)
          {
              pw.println("<h3 style='text-align:center;Color:red;font-size:60px'>Successfully scheduled a class</h3>");
          }
          else
          {
              pw.println("<h3 style='text-align:center;Color:red;font-size:60px'>Failed to schedule a class, Please Try again</h3>");
          }
          RequestDispatcher rd=request.getRequestDispatcher("Dashboardtr.html");
          rd.include(request,response);
      }
      catch(Exception e)
      {
          System.out.println(e.getMessage());
      }
    }
}
