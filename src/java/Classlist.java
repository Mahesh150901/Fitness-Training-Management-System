import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Classlist extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
      response.setContentType("text/html");
      PrintWriter pw=response.getWriter();
      RequestDispatcher rdf=request.getRequestDispatcher("Dashboardtr.html");
      rdf.include(request,response);
      try
      {
          Class.forName("com.mysql.jdbc.Driver");
          Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/fitness","root","");
          PreparedStatement pst=con.prepareStatement("select * from class");
          ResultSet rs=pst.executeQuery();
          pw.println("<h3 style='text-align:center;color:red;font-size:60px;'>List of Scheduled classes</h3>");
          pw.println("<center><table border=2px style='text-align:center;color:blue;font-size:30px;border-collapse:collapse;'><tr><th style='padding:15px;'><b>Name of the class</b></th><th style='padding:15px;'><b>Class Link</b></th><th style='padding:15px;'><b>Date</b></th><th style='padding:15px;'><b>Time</b></th></tr>");
          while(rs.next())
          {
              String Name=rs.getString("Name");
              String Link=rs.getString("Link");
              String Date=rs.getString("Date");
              String Time=rs.getString("Time");
              pw.println("<tr><td style='padding:15px;'>"+Name+"</td><td style='padding:15px;'>"+Link+"</td><td style='padding:15px;'>"+Date+"</td><td style='padding:15px;'>"+Time+"</td></tr>");
          }
          pw.println("</table></center>");
          con.close();
      }
      catch(Exception e)
      {
          System.out.println(e.getMessage());
      }
    }
}
