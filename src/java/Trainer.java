import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Trainer extends HttpServlet 
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
       response.setContentType("text/html");
       PrintWriter pw=response.getWriter();
       RequestDispatcher rd=request.getRequestDispatcher("Dashboardcus.html");
       rd.include(request,response);
       try
       {
           Class.forName("com.mysql.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3307/fitness","root","");
           PreparedStatement pst=con.prepareStatement("select * from employee");
           ResultSet rs=pst.executeQuery();
           pw.println("<h3 style='font-size:30px;color:darkgreen;text-align:center'>Trainers Details</h3>");
           pw.println("<center><table border=2px style='color:darkgreen;font-size:30px;border-collapse:collapse;padding:15px'><tr><th style='padding:15px'>Name</th><th style='padding:15px'>Email</th><th style='padding:15px'>Phone number</th></tr>");
           while(rs.next())
           {
               String Name=rs.getString("Name");
               String Email=rs.getString("Email");
               String Phone=rs.getString("Phone");
               pw.println("<tr><td style='padding:15px'>"+Name+"</td><td style='padding:15px'>"+Email+"</td><td style='padding:15px'>"+Phone+"</td></tr>");
           }
           pw.println("</table></center>");
           con.close();
       }
       catch(Exception e)
       {
           pw.println(e.getMessage());
       }
    }
}
